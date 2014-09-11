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

    /**
     * Returns document from database  by id
     *
     * @param  id
     * @return    document by id
     */
    @Override
    @Transactional(readOnly=true)
    public Document getDocument(int id) {
        return documentDAO.getDocument(id);
    }

    /**
     * Adds document to database
     *
     * @param  document document, which should be added to database
     * @return          true if document was added, otherwise false
     */
    @Override
    @Transactional
    public boolean addDocument(Document document) {
        return documentDAO.addDocument(document);
    }

    /**
     * Updates document
     *
     * @param  document  document, which should be updated
     * @return           true if document was updated, otherwise false
     */
    @Override
    @Transactional
    public boolean updateDocument(Document document) {
        return documentDAO.updateDocument(document);
    }

    /**
     * Deletes document
     *
     * @param  id document id
     * @return    true if document was deleted, otherwise false
     */
    @Override
    @Transactional
    public boolean deleteDocument(int id) {
        return documentDAO.deleteDocument(id);
    }

    /**
     * Get list of all documents
     *
     * @return  list of all documents
     */
    @Override
    @Transactional(readOnly=true)
    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }

}
