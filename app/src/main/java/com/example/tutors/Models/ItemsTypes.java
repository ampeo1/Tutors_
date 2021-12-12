package com.example.tutors.Models;

public enum ItemsTypes {
    ALL("Все предметы"),
    MATHS("Математика"),
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    RUSSIAN_LANGUAGE("Русский язык");

    private String subject;
    ItemsTypes(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
