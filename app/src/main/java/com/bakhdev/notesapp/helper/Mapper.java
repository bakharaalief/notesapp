package com.bakhdev.notesapp.helper;

import com.bakhdev.notesapp.data.room.NoteEntity;
import com.bakhdev.notesapp.domain.model.Note;

public class Mapper {
    public static NoteEntity toNoteEntity(Note note) {
        return new NoteEntity(
                note.getId(),
                note.getTitle(),
                note.getDesc()
        );
    }
}
