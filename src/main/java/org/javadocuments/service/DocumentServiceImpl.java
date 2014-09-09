package org.javadocuments.service;

import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    @Transactional
    public Document getDocument(int id) {
        return documentDAO.getDocument(id);
    }

    @Override
    @Transactional
    public boolean addDocument(Document document) {
        return documentDAO.addDocument(document);
    }

    @Override
    @Transactional
    public boolean updateDocument(Document document) {
        return documentDAO.updateDocument(document);
    }

    @Override
    @Transactional
    public boolean deleteDocument(int id) {
        return documentDAO.deleteDocument(id);
    }

    @Override
    @Transactional
    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }

}
