package org.javadocuments.service;

import org.javadocuments.domain.Document;

import java.util.List;

public interface DocumentService {
    public Document getDocument(int id);
    public int addDocument(Document document);
    public boolean updateDocument(Document document);
    public boolean deleteDocument(int id);
    public void addBatchDocuments(final List<Document> documentList);
    public List<Document> getAllDocuments();
}
