package org.javadocuments.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.javadocuments.domain.Document;
import org.javadocuments.domain.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {

    private static String GL_SEARCH_FIELD = "text";

    @Autowired
    private HttpSolrServer solrServer;

    /**
     * Search documents by set of the search terms
     *
     * @param   searchTerms          {fieldName: value, ...}
     * @return                       found documents
     * @throws  SolrServerException
     */
    @Override
    public List<SolrDocument> searchDocuments(Map searchTerms) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        for (Object objKey : searchTerms.keySet()) {
            query.setQuery(objKey.toString()+":"+searchTerms.get(objKey));
        }
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();

        List<SolrDocument> resDocList = new ArrayList<>();

        for (org.apache.solr.common.SolrDocument solrDoc: documentList) {
            SolrDocument newDoc = new SolrDocument();
            newDoc.setId((Integer)solrDoc.getFieldValue("id"));
            newDoc.setName((String)solrDoc.getFieldValue("name"));
            newDoc.setAuthor((String)solrDoc.getFieldValue("author"));
            newDoc.setPath((String)solrDoc.getFieldValue("path"));
            newDoc.setDescription((String)solrDoc.getFieldValue("description"));
            newDoc.setCreateddate((Date) solrDoc.getFieldValue("createddate"));
            resDocList.add(newDoc);
        }
        return resDocList;
    }

    /**
     * Google like search by all fields
     *
     * @param  searchTerm           search term (can be part of the word)
     * @return                      found documents
     * @throws SolrServerException
     */
    @Override
    public List<SolrDocument> simpleSearchDocuments(String searchTerm) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(GL_SEARCH_FIELD+":"+searchTerm);

        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();

        List<SolrDocument> resDocList = new ArrayList<>();

        for (org.apache.solr.common.SolrDocument solrDoc: documentList) {
            SolrDocument newDoc = new SolrDocument();
            newDoc.setId((Integer)solrDoc.getFieldValue("id"));
            newDoc.setName((String)solrDoc.getFieldValue("name"));
            newDoc.setAuthor((String)solrDoc.getFieldValue("author"));
            newDoc.setPath((String)solrDoc.getFieldValue("path"));
            newDoc.setDescription((String)solrDoc.getFieldValue("description"));
            newDoc.setCreateddate((Date) solrDoc.getFieldValue("createddate"));
            resDocList.add(newDoc);
        }
        return resDocList;
    }

    /**
     * Adds list of documents to the Solr
     *
     * @param  docList   documents for indexing
     * @return           true if indexing was correct, otherwise false
     */
    @Override
    public boolean indexAllDocuments(List<Document> docList) {
        List<SolrDocument> solrDocList = new ArrayList<>();

        for(Document currDoc: docList) {
            SolrDocument solrDocument = new SolrDocument();
            solrDocument.setId(currDoc.getId());
            solrDocument.setName(currDoc.getName());
            solrDocument.setAuthor(currDoc.getAuthor());
            solrDocument.setPath(currDoc.getPath());
            solrDocument.setDescription(currDoc.getDescription());
            solrDocument.setCreateddate(currDoc.getCreatedDate());
            solrDocList.add(solrDocument);
        }

        try {
            solrServer.addBeans(solrDocList);
            solrServer.commit();
            return true;
        } catch (IOException | SolrServerException e) {
            return false;
        }
    }

    /**
     * Adds one document to the Solr
     *
     * @param   document  document, which should be added to the solr
     * @return            true is document was added to solr, otherwise false
     */
    @Override
    public boolean indexDocument(Document document) {

        SolrDocument solrDocument = new SolrDocument();
        solrDocument.setId(document.getId());
        solrDocument.setName(document.getName());
        solrDocument.setAuthor(document.getAuthor());
        solrDocument.setPath(document.getPath());
        solrDocument.setDescription(document.getDescription());
        solrDocument.setCreateddate(document.getCreatedDate());

        try {
            solrServer.addBean(solrDocument);
            solrServer.commit();
            return true;
        } catch (IOException | SolrServerException e) {
            return false;
        }
    }
}
