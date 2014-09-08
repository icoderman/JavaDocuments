package org.javadocuments.service;

import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public Document getDocument(int id) {
        return documentDAO.getDocument(id);
    }

    @Override
    public int addDocument(Document document) {
        return documentDAO.addDocument(document);
    }

    @Override
    public boolean updateDocument(Document document) {
        return documentDAO.updateDocument(document);
    }

    @Override
    public boolean deleteDocument(int id) {
        return documentDAO.deleteDocument(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }

    @Override
    public void addBatchDocuments(List<Document> documentList) {

    }
}
