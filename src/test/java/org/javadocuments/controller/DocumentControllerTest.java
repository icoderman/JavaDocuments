package org.javadocuments.controller;

import org.javadocuments.dao.DocumentDAOImpl;
import org.javadocuments.domain.Document;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.DocumentServiceImpl;
import org.javadocuments.service.SolrService;
import org.javadocuments.service.SolrServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class DocumentControllerTest {

    @InjectMocks
    private DocumentController documentController;

    @Mock
    private DocumentService documentService;

    @Mock
    private SolrService solrService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
     }

    @Test
    public void testGetDocument() throws Exception {
        DocumentService mockDocumentService = org.mockito.Mockito.mock(DocumentService.class);
        when(mockDocumentService.getDocumentById(16)).thenReturn(new Document());
        mockMvc.perform(get("/document/16")).andExpect(status().isOk());
    }

    @Test
    public void testAddDocument()throws Exception {

        Document newDoc = new Document();
        newDoc.setId(123);
        newDoc.setName("TestName");
        newDoc.setAuthor("TestAuthor");
        newDoc.setPath("TestPath");
        newDoc.setDescription("TestDesc");
        newDoc.setCreatedDate(new Date());

        DocumentService mockDocumentService = org.mockito.Mockito.mock(DocumentService.class);
        when(mockDocumentService.getDocumentById(123)).thenReturn(new Document());
        when(mockDocumentService.addDocument(newDoc)).thenReturn(1);

        mockMvc.perform(post("/document/add").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newDoc))).andExpect(status().isOk());
    }


}
