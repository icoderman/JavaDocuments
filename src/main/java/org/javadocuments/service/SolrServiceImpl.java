package org.javadocuments.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.javadocuments.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    HttpSolrServer solrServer;

    @Override
    public SolrDocumentList searchDocuments(Map searchTerms) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        for (Object objKey : searchTerms.keySet()) {
            query.setQuery(objKey.toString()+":"+searchTerms.get(objKey));
        }
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();
        return documentList;
    }

    @Override
    public List<Document> indexAllDocuments(List<Document> docList) {

        for(Document document: docList) {
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
    }
}
