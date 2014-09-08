package org.javadocuments.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.javadocuments.domain.Document;
import org.javadocuments.domain.SolrDocument;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SolrService solrService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document getDocument(@PathVariable Integer id) {
        return documentService.getDocument(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Document addDocument(@RequestBody Document document) {
        int id = documentService.addDocument(document);
        System.out.println(document);
        return documentService.getDocument(id);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<SolrDocument> searchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        return solrService.searchDocuments(searchTerms);
    }

    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    public String indexAllDocuments() {
        return (solrService.indexAllDocuments(documentService.getAllDocuments()))?"{result: true}":"result:false";
    }
}
