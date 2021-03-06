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
import java.util.Date;
import java.util.List;

@Component
public class DocumentDAOImpl implements DocumentDAO {

    private static final String SQL_GET_DOCUMENT_BY_ID = "SELECT * FROM documents WHERE id = ?";
    private static final String SQL_UPDATE_DOCUMENT    = "UPDATE documents SET name=?, author=?, path=?, description=? WHERE id=?";
    private static final String SQL_DELETE_DOCUMENT    = "DELETE FROM documents WHERE id=?";
    private static final String SQL_GET_ALL_DOCUMENTS  = "SELECT * FROM documents";

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
         jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("documents").usingGeneratedKeyColumns("id");
    }

    @Override
    public Document getDocument(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_DOCUMENT_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Document.class));
        } catch (EmptyResultDataAccessException e) {
            // Document not found
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addDocument(Document document) {
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
        int numRows = simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(document));
        return (numRows > 0);
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
        try {
            return jdbcTemplate.query(SQL_GET_ALL_DOCUMENTS, new BeanPropertyRowMapper<>(Document.class));
        } catch (EmptyResultDataAccessException e) {
            // Document not found
            e.printStackTrace();
        }
        return null;
    }
}
