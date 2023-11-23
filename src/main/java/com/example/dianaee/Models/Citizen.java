package com.example.dianaee.Models;

public class Citizen {
    private Long citizen_id, document_id, person_id;
    private String shifer;
    private Person person;
    private Document document;

    public Citizen() {
    }

    public Citizen(Long citizen_id, Long person_id, Long document_id,
                   String shifer, Person person, Document document) {
        this.citizen_id = citizen_id;
        this.person_id = person_id;
        this.document_id = document_id;
        this.shifer = shifer;
        this.person = person;
        this.document = document;
    }

    public Citizen( Long person_id, Long document_id, String shifer,
                   Person person, Document document) {
        this.person_id = person_id;
        this.document_id = document_id;
        this.shifer = shifer;
        this.person = person;
        this.document = document;
    }

    public Citizen(String shifer, Person person, Document document) {
        this.shifer = shifer;
        this.person = person;
        this.document = document;
    }

    public Long getCitizenId() {
        return citizen_id;
    }

    public void setCitizenId(Long citizen_id) {
        this.citizen_id = citizen_id;
    }

    public Long getDocumentId() {
        return document_id;
    }

    public void setDocumentId(Long document_Id) {
        this.document_id = document_Id;
    }

    public Long getPersonId() {
        return person_id;
    }

    public void setPersonId(Long person_Id) {
        this.person_id = person_Id;
    }

    public String getShifer() {
        return shifer;
    }

    public void setShifer(String shifer) {
        this.shifer = shifer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "citizen_id=" + citizen_id +
                ", person_Id=" + person_id +
                ", document_Id=" + document_id +
                ", shifer='" + shifer + '\'' +
                ", person=" + person +
                ", document=" + document +
                '}';
    }
}
