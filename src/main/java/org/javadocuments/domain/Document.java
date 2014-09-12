package org.javadocuments.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.javadocuments.utils.JsonDateSerializer;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

//XmlRootElement
public class Document {

    @Id
    private int id;

    @NotNull
    @Size(min=1, max=255)
    private String name;

    @NotNull
    @Size(min=1, max=255)
    private String author;

    @NotNull
    @Size(min=1, max=255)
    private String path;

    @NotNull
    @Size(min=1)
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

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return String.format( "Document [id=%s, name=%s, author=%s, path=%s, description=%s, date=%s]",id, name, author, path, description, createdDate );
    }
}
