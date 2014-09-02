JavaDocuments
=============

1. Create empty maven project.
2. Create sql script that will create table "documents" with next fields: id, name, author, path, description, createdDate in PostreSQL DB.
3. Create DAO services which will add, read and update rows in "documents" table.  Spring JDBCTemplate and Spring SimpleJDBCInsert should be used.
4. Configure SOLR schema to adjust "documents" fields.
5. Create REST web services:
- that will index all documents from DB
- that will search documents by any document field,
- that will add row in "documents" table
All request and responses should be in JSON format.
6. Create integration tests for each REST web service. Mockito library should be used.
