package com.bakhdev.notesapp.domain.repository;

import com.bakhdev.notesapp.domain.model.Note;

import io.reactivex.Completable;

public interface NoteRepository {
    Completable insertNote(Note note);
}
