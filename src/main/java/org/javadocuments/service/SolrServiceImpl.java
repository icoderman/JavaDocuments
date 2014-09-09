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
            newDoc.setCreatedDate((Date)solrDoc.getFieldValue("createddate"));
            resDocList.add(newDoc);
        }
        return resDocList;
    }

    @Override
    public boolean indexAllDocuments(List<Document> docList) {
        try {
            solrServer.addBeans(docList);
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
            doc.addField("createddate", document.getCreatedDate());

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
