package org.javadocuments.domain;

import com.sun.javafx.beans.IDProperty;
import org.apache.solr.client.solrj.beans.Field;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.javadocuments.utils.JsonDateSerializer;
import org.springframework.data.annotation.Id;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

//XmlRootElement
public class Document {

    @Id
    @Field
    private int id;

    @Field
    private String name;

    @Field
    private String author;

    @Field
    private String path;

    @Field
    private String description;

    @Field
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
        return "Document [id="+id+", name="+name+", author="+author+", path="+path+
                ", description="+description+", date="+createdDate+"]";
    }
}
