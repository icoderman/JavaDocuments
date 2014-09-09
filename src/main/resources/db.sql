CREATE TABLE documents (
    id bigserial primary key not null,
    name varchar(255) not null,
    author varchar(255) not null,
    path varchar(255) not null,
    description text not null,
    createddate date
);