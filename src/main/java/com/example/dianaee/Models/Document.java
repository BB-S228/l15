package com.example.dianaee.Models;

public class Document {
    private Long document_id;
    private String name, serial, number, organ, date;

    public Document() {
    }

    public Document(Long document_id, String name, String serial, String number, String organ, String date) {
        this.document_id = document_id;
        this.name = name;
        this.serial = serial;
        this.number = number;
        this.organ = organ;
        this.date = date;
    }

    public Document(String name, String serial, String number, String organ, String date) {
        this.name = name;
        this.serial = serial;
        this.number = number;
        this.organ = organ;
        this.date = date;
    }

    public Long getDocumentId() {
        return document_id;
    }

    public void setDocumentId(Long document_id) {
        this.document_id = document_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Document{" +
                "document_id=" + document_id +
                ", name='" + name + '\'' +
                ", serial='" + serial + '\'' +
                ", number='" + number + '\'' +
                ", organ='" + organ + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
