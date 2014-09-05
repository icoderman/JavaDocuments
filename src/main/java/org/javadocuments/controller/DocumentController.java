package org.javadocuments.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.javadocuments.domain.Document;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SolrService solrService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Document getDocument(@PathVariable Integer id) {
        return documentService.getDocumentById(id);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Document addDocument(@RequestBody final Document document) {
        int id = documentService.addDocument(document);
        return documentService.getDocumentById(id);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<Document> searchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        return solrService.searchDocuments(searchTerms);
    }

    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    public List<Document> indexAllDocuments() {
        return solrService.indexAllDocuments(documentService.getAllDocuments());
    }
}
