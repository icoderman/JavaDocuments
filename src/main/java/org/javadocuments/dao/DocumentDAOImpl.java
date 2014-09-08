package org.javadocuments.dao;

import org.javadocuments.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DocumentDAOImpl implements DocumentDAO {

    public static final String SQL_GET_DOCUMENT_BY_ID = "SELECT * FROM documents WHERE id = ?";
    public static final String SQL_UPDATE_DOCUMENT    = "UPDATE documents SET name=?, author=?, path=?, description=? WHERE id=?";
    public static final String SQL_DELETE_DOCUMENT    = "DELETE FROM documents WHERE id=?";
    public static final String SQL_GET_ALL_DOCUMENTS  = "SELECT * FROM documents";

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("documents").usingGeneratedKeyColumns("id");
    }

    @Override
    public Document getDocument(int id) {
        jdbcTemplate = new JdbcTemplate(dataSource);
           try {
            return (Document) jdbcTemplate.queryForObject(SQL_GET_DOCUMENT_BY_ID, new Object[]{id}, new BeanPropertyRowMapper(Document.class));
        } catch (EmptyResultDataAccessException e) {
            // Document not found
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addDocument(Document document) {
        /* Inserting without SimpleJdbcInsert
        String sql = "insert into documents (name, author, path, description, createdDate) values (?, ?, ?, ?, now())";
        jdbcTemplate = new JdbcTemplate(dataSource);
        int numRows = jdbcTemplate.update(sql,
                document.getName(),
                document.getAuthor(),
                document.getPath(),
                document.getDescription());
        return (numRows>0)?true:false;
        */
        document.setCreatedDate(new Date());
        Number resId = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(document));
        return resId.intValue();
    }

    @Override
    public boolean updateDocument(Document document) {
        int numRows = jdbcTemplate.update(SQL_UPDATE_DOCUMENT, document.getName(), document.getAuthor(),
                document.getPath(), document.getDescription(), document.getId());
        return (numRows > 0);
    }

    @Override
    public boolean deleteDocument(int id) {
         int numRows = jdbcTemplate.update(SQL_DELETE_DOCUMENT, id);
        return (numRows > 0);
    }

    @Override
    public List<Document> getAllDocuments() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(SQL_GET_ALL_DOCUMENTS, new BeanPropertyRowMapper(Document.class));
    }

    @Override
    public void addBatchDocuments(List<Document> documentList) {

    }
}
