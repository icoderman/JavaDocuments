package org.javadocuments.domain;

import java.util.Date;

/**
 * Created by omand on 01.09.2014.
 */
public class Document {

    private int id;
    private String name;
    private String author;
    private String path;
    private String description;
    private Date createdDate;

    public Document() {
    }

    public Document(String name, String author, String path, String description) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Document [id="+id+", name="+name+", author="+author+", path="+path+
                ", description="+description+", date="+createdDate+"]";
    }
}
