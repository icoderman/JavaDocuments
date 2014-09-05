package org.javadocuments.dao;

import org.javadocuments.domain.Document;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentDAOImpl implements DocumentDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("documents").usingGeneratedKeyColumns("id");
    }

    @Override
    public Document getDocumentById(int id) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM documents WHERE id = ?";
        Document resDoc = new Document();
        try {
            resDoc = (Document) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Document.class));
        } catch (EmptyResultDataAccessException e) {
            // Document not found
            e.printStackTrace();
        }
        return resDoc;
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
        Map newDocument = new HashMap();
        newDocument.put("name", document.getName());
        newDocument.put("author", document.getAuthor());
        newDocument.put("path", document.getPath());
        newDocument.put("description", document.getDescription());
        newDocument.put("createddate", new Timestamp(new java.util.Date().getTime()));

        Number resId = -1;
        try {
            resId = simpleJdbcInsert.executeAndReturnKey(newDocument);
        } catch (Exception e) {
            // Error of adding new document
            e.printStackTrace();
        }
        // convert Number to Int using ((Number) key).intValue()
        return resId.intValue();
    }

    @Override
    public boolean updateDocument(Document document) {
        String sql = "UPDATE documents SET name=?, author=?, path=?, description=? WHERE id=?";
        int numRows = jdbcTemplate.update(sql, document.getName(), document.getAuthor(),
                document.getPath(), document.getDescription(), document.getId());
        return (numRows > 0);
    }

    @Override
    public boolean deleteDocument(int id) {
        String sql = "DELETE FROM documents WHERE id=?";
        int numRows = jdbcTemplate.update(sql, id);
        return (numRows > 0);
    }

    @Override
    public List<Document> getAllDocuments() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM documents";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Document.class));
    }

    @Override
    public void addBatchDocuments(List<Document> documentList) {

    }
}
