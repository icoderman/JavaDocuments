package org.javadocuments;

import org.javadocuments.dao.DocumentDAO;
import org.javadocuments.domain.Document;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class App {
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        /*
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        DocumentDAO documentDAO = (DocumentDAO)context.getBean("documentDAO");

        Document document1 = new Document("Name2", "Author12", "Path1", "Description1");
        documentDAO.addDocument(document1);

        System.out.println(documentDAO.getDocumentById(13));

        document1.setName("Vasia");
        document1.setId(13);
        documentDAO.updateDocument(document1);
        System.out.println(documentDAO.getDocumentById(13));

        documentDAO.deleteDocument(14);
        context.close();
        */
    }
}