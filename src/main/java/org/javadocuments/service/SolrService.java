package org.javadocuments.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.javadocuments.domain.Document;
import org.javadocuments.domain.SolrDocument;

import java.util.List;
import java.util.Map;

public interface SolrService {
    public List<SolrDocument> searchDocuments(Map searchTerms) throws SolrServerException;
    public List<SolrDocument> simpleSearchDocuments(String searchTerm) throws SolrServerException;
    public boolean indexAllDocuments(List<Document> docList);
    public boolean indexDocument(Document document);
}
