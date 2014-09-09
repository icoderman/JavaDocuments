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

    @Autowired
    private HttpSolrServer solrServer;

    /**
     * Search documents by set of the search terms in format fieldName: value
     *
     * @param searchTerms
     * @return
     * @throws SolrServerException
     */
    @Override
    public List<SolrDocument> searchDocuments(Map searchTerms) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        for (Object objKey : searchTerms.keySet()) {
            query.setQuery(objKey.toString()+":"+searchTerms.get(objKey));
        }
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();

        List<SolrDocument> resDocList = new ArrayList<SolrDocument>();

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

    @Override
    public List<SolrDocument> simpleSearchDocuments(String searchTerm) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("text:"+searchTerm);

        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();

        List<SolrDocument> resDocList = new ArrayList<SolrDocument>();

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

    @Override
    public boolean indexAllDocuments(List<Document> docList) {
        List<SolrDocument> solrDocList = new ArrayList<SolrDocument>();

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

        /**for(Document document: docList) {
            SolrInputDocument doc = new SolrInputDocument();

            doc.addField("id", document.getId());
            doc.addField("name", document.getName());
            doc.addField("author", document.getAuthor());
            doc.addField("path", document.getPath());
            doc.addField("description", document.getDescription());
            doc.addField("createddate", document.getCreateddate());

            try {
                solrServer.add(doc);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return docList;
         */
    }
}
