package org.javadocuments.controller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.codehaus.jackson.map.util.JSONPObject;
import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.dao.DocumentDAOImpl;
import org.javadocuments.domain.Document;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value="/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SolrService solrService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document getDocument(@PathVariable Integer id) {
       return documentService.getDocumentById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Document addDocument(@RequestBody final Document document){
        int id = documentService.addDocument(document);
        return documentService.getDocumentById(id);
    }

    @RequestMapping(value="search", method = RequestMethod.POST)
    public SolrDocumentList searchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        return solrService.searchDocuments(searchTerms);
    }

    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    public List<Document> indexAllDocuments()  {
        return solrService.indexAllDocuments(documentService.getAllDocuments());
    }
}
