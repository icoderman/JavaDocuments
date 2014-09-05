package org.javadocuments.service;

import org.javadocuments.domain.Document;

import java.util.List;

public interface DocumentService {
    public Document getDocumentById(int id);
    public int addDocument(Document document);
    public boolean updateDocument(Document document);
    public boolean deleteDocument(int id);
    public List<Document> getAllDocuments();
    public void addBatchDocuments(final List<Document> documentList);
}
