package org.javadocuments.dao;

import java.util.List;
import org.javadocuments.domain.Document;

public interface DocumentDAO {

    public Document getDocument(int id);
    public boolean addDocument(Document document);
    public boolean updateDocument(Document document);
    public boolean deleteDocument(int id);
    public List<Document> getAllDocuments();
    public void addBatchDocuments(final List<Document> documentList);

}
