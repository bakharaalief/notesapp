package com.bakhdev.notesapp.helper;

import com.bakhdev.notesapp.data.room.NoteEntity;
import com.bakhdev.notesapp.domain.model.Note;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static NoteEntity toNoteEntity(Note note) {
        return new NoteEntity(
                note.getId(),
                note.getTitle(),
                note.getDesc()
        );
    }

    public static List<Note> toListNote(List<NoteEntity> listNoteEntity) {
        return listNoteEntity.stream().map(
                it -> new Note(it.getId(), it.getTitle(), it.getDesc())
        ).collect(Collectors.toList());
    }
}
