package org.javadocuments.domain;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Document document = new Document();
        document.setId(resultSet.getInt("id"));
        document.setName(resultSet.getString("name"));
        document.setAuthor(resultSet.getString("author"));
        document.setPath(resultSet.getString("path"));
        document.setDescription(resultSet.getString("description"));
        document.setCreatedDate(resultSet.getDate("createddate"));

        return document;
    }
}
