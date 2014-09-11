package org.javadocuments.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.javadocuments.domain.Document;
import org.javadocuments.domain.SolrDocument;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SolrService solrService;

    /** Get document from database by id
     *
     * @param id
     * @return  document in json
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getDocument(@PathVariable Integer id) {
        Document resDoc =  documentService.getDocument(id);
        if (resDoc == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(resDoc, HttpStatus.OK);
    }

    /** Get all documents from database
     *
     * @return  documents in json
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAllDocuments() {
        List<Document> resDocList =  documentService.getAllDocuments();
        if (resDocList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(resDocList, HttpStatus.OK);
    }

    /** Add document to database
     *
     * @param document
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addDocument(@RequestBody @Valid Document document, BindingResult result) {
        if (!result.hasErrors() && documentService.addDocument(document)) {
            solrService.indexDocument(document);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /** Search document in solr by fieldname:value
     *
     * @param searchTerms
     * @return
     * @throws SolrServerException
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<SolrDocument> searchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        return solrService.searchDocuments(searchTerms);
    }

    /** Google like search by solr
     *
     * @param searchTerms   field name should be "text"
     * @return
     * @throws SolrServerException
     */
    @RequestMapping(value = "simplesearch", method = RequestMethod.POST)
    public List<SolrDocument> simpleSearchDocuments(@RequestBody final Map searchTerms) throws SolrServerException {
        if (searchTerms.containsKey("text")) {
            return solrService.simpleSearchDocuments((String)searchTerms.get("text"));
        }
        return null;
    }

    /** Indexing all documents from db to solr
     *
     * @return
     */
    @RequestMapping(value = "/indexAll", method = RequestMethod.GET)
    public ResponseEntity indexAllDocuments() {
        if (solrService.indexAllDocuments(documentService.getAllDocuments())) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}