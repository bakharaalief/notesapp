package com.bakhdev.notesapp.domain.model;

public class Note {
    int id;
    String title;
    String desc;

    public Note(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
