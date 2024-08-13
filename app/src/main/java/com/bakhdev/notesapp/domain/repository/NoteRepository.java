package com.bakhdev.notesapp.domain.repository;

import com.bakhdev.notesapp.domain.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface NoteRepository {
    Flowable<List<Note>> getNotes();

    Completable insertNote(Note note);
}
