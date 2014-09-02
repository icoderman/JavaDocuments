package org.javadocuments.dao;

import org.javadocuments.domain.Document;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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
        return (Document)jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Document.class));
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
        Map newDocument = new HashMap();
        newDocument.put("name", document.getName());
        newDocument.put("author", document.getAuthor());
        newDocument.put("path", document.getPath());
        newDocument.put("description", document.getDescription());
        newDocument.put("createddate", new Timestamp(new java.util.Date().getTime()));
        int numRows = simpleJdbcInsert.execute(newDocument);
        return (numRows>0)?true:false;
    }

    @Override
    public boolean updateDocument(Document document) {
        String sql = "UPDATE documents SET name=?, author=?, path=?, description=? WHERE id=?";
        int numRows = jdbcTemplate.update(sql, document.getName(), document.getAuthor(),
                document.getPath(), document.getDescription(), document.getId());
        return (numRows>0)?true:false;
    }

    @Override
    public boolean deleteDocument(int documentId) {
        String sql = "DELETE FROM documents WHERE id=?";
        int numRows = jdbcTemplate.update(sql, documentId);
        return (numRows>0)?true:false;
    }

    @Override
    public List<Document> getAllDocuments() {
        return null;
    }

    @Override
    public void addBatchDocuments(List<Document> documentList) {

    }
}
