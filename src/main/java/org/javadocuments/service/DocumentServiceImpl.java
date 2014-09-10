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

    /** Get document from database  by id
     *
     * @param id
     * @return  document
     */
    @Override
    @Transactional
    public Document getDocument(int id) {
        return documentDAO.getDocument(id);
    }

    /** Add document to database
     *
     * @param document
     * @return
     */
    @Override
    @Transactional
    public boolean addDocument(Document document) {
        return documentDAO.addDocument(document);
    }

    /** Update document
     *
     * @param document
     * @return
     */
    @Override
    @Transactional
    public boolean updateDocument(Document document) {
        return documentDAO.updateDocument(document);
    }

    /** Delete document
     *
     * @param id    document id
     * @return
     */
    @Override
    @Transactional
    public boolean deleteDocument(int id) {
        return documentDAO.deleteDocument(id);
    }

    /** Get list of all documents
     *
     * @return
     */
    @Override
    @Transactional
    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }

}
