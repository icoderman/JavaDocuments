package org.javadocuments.domain;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

public class SolrDocument {

    @Field
    private int id;

    @Field
    private String name;

    @Field
    private String author;

    @Field("path_txt")
    private String path;

    @Field("description_txt")
    private String description;

    @Field
    private Date createddate;

    public SolrDocument() {
    }

    public SolrDocument(String name, String author, String path, String description) {
        this.name = name;
        this.author = author;
        this.path = path;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Override
    public String toString() {
        return String.format("Document [id=%s, name=%s, author=%s, path=%s, description=%s, date=%s]", id, name, author, path, description, createddate);
    }
}