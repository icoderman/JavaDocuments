package org.javadocuments.service;

import org.javadocuments.domain.Document;

import java.util.List;

public interface DocumentService {
    Document getDocument(int id);
    boolean addDocument(Document document);
    boolean updateDocument(Document document);
    boolean deleteDocument(int id);
    List<Document> getAllDocuments();
}
