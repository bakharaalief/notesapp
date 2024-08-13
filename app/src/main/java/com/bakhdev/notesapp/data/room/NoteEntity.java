package com.bakhdev.notesapp.data.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    final
    int id;
    @ColumnInfo(name = "title")
    final
    String title;
    @ColumnInfo(name = "desc")
    final
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
