package org.javadocuments.controller;

import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.dao.DocumentDAOImpl;
import org.javadocuments.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
public class DocumentController {

    @Autowired
    private DocumentDAOImpl documentDAO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Document getGreeting(@PathVariable Integer id) {

       // ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/applicationContext.xml");

       // DocumentDAO documentDAO = (DocumentDAO)context.getBean("documentDAO");

        //Document document1 = new Document("Name2", "Author12", "Path1", "Description1");
        //documentDAO.addDocument(document1);

        //System.out.println(documentDAO.getDocumentById(13));

        //document1.setName("Vasia");
        //document1.setId(13);
        //documentDAO.updateDocument(document1);
        //System.out.println(documentDAO.getDocumentById(13));

//        documentDAO.deleteDocument(14);
        System.out.println(id);
        System.out.println(documentDAO.getDocumentById(id));
        return documentDAO.getDocumentById(id);
        //context.close();

    }
}
