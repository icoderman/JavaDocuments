package org.javadocuments.service;

import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.domain.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Mock
    private DocumentDAO documentDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDocument() {
        int id = 1;
        Document document = new Document();
        document.setId(id);
        when(documentDAO.getDocument(id)).thenReturn(document);
        document = documentService.getDocument(id);
        assertNotNull("Document was not found", document);
        assertEquals("Expected " + id, id, document.getId());
    }

    @Test
    public void testAddDocument() {
        Document document = new Document();
        documentService.addDocument(document);
        verify(documentDAO, times(1)).addDocument(document);
    }

    @Test
    public void testUpdateDocument() {
        Document document = new Document();
        documentService.updateDocument(document);
        verify(documentDAO, times(1)).updateDocument(document);
    }

    @Test
    public void testDeleteDocument() {
        int id = 1;
        Document document = new Document();
        document.setId(id);
        documentService.addDocument(document);
        documentService.deleteDocument(id);
        verify(documentDAO, times(1)).deleteDocument(id);
    }

    @Test
    public void testGetAllDocuments() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document());
        documentList.add(new Document());
        when(documentDAO.getAllDocuments()).thenReturn(documentList);
        assertEquals("Expected 2 documents", 2, documentService.getAllDocuments().size());
    }

}