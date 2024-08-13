package com.bakhdev.notesapp.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "desc")
    String desc;

    public NoteEntity(int id, String title, String desc){
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
