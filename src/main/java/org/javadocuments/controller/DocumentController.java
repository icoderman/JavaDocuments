package org.javadocuments.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.javadocuments.domain.Document;
import org.javadocuments.domain.SolrDocument;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

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
    public ResponseEntity getDocument(@PathVariable Integer id) {
        Document resDoc =  documentService.getDocument(id);
        if (resDoc == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(resDoc, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllDocuments() {
        List<Document> resDocList =  documentService.getAllDocuments();
        if (resDocList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(resDocList, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addDocument(@RequestBody Document document) {
        if (documentService.addDocument(document)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<SolrDocument> searchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        return solrService.searchDocuments(searchTerms);
    }

    @RequestMapping(value = "simplesearch", method = RequestMethod.POST)
    public List<SolrDocument> simpleSearchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        if (searchTerms.containsKey("text")) {
            return solrService.simpleSearchDocuments((String)searchTerms.get("text"));
        }
        return null;
    }

    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    public ResponseEntity indexAllDocuments() {
        if (solrService.indexAllDocuments(documentService.getAllDocuments())) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}