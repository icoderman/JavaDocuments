package org.javadocuments.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.javadocuments.domain.Document;

import java.util.List;
import java.util.Map;

public interface SolrService {
    public List<Document> searchDocuments(Map searchTerms) throws SolrServerException;
    public  List<Document> indexAllDocuments(List<Document> docList);
}
