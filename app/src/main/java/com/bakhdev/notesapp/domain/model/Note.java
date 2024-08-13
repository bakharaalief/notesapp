package com.bakhdev.notesapp.domain.model;

public class Note {
    final int id;
    final String title;
    final String desc;
    boolean showDelete;

    public Note(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.showDelete = false;
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

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public boolean isShowDelete() {
        return showDelete;
    }
}
