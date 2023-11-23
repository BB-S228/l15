package com.example.dianaee.Models;

public class Person {
    private Long person_id;
    private String fio, inn, type, date;

    public Person() {
    }

    public Person(Long person_id, String fio, String inn, String type, String date) {
        this.person_id = person_id;
        this.fio = fio;
        this.inn = inn;
        this.type = type;
        this.date = date;
    }

    public Person(String fio, String inn, String type, String date) {
        this.fio = fio;
        this.inn = inn;
        this.type = type;
        this.date = date;
    }

    public Long getPersonId() {
        return person_id;
    }

    public void setPersonId(Long person_id) {
        this.person_id = person_id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String type) {
        this.date = type;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", fio='" + fio + '\'' +
                ", inn='" + inn + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
