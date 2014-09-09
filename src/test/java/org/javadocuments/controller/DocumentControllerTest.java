package org.javadocuments.controller;

import org.javadocuments.domain.Document;
import org.javadocuments.service.DocumentService;
import org.javadocuments.service.SolrService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Document newDoc = new Document();
        newDoc.setId(16);
        newDoc.setName("TestName");
        newDoc.setAuthor("TestAuthor");
        newDoc.setPath("TestPath");
        newDoc.setDescription("TestDesc");
        newDoc.setCreatedDate(new Date());

        when(mockDocumentService.getDocument(16)).thenReturn(newDoc);

        mockMvc.perform(get("/document/16"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is("16")));
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
        when(mockDocumentService.getDocument(123)).thenReturn(newDoc);
        when(mockDocumentService.addDocument(newDoc)).thenReturn(true);

        mockMvc.perform(post("/document/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
