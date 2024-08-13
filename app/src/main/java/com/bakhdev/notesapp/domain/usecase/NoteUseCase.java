package com.bakhdev.notesapp.domain.usecase;

import com.bakhdev.notesapp.domain.model.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface NoteUseCase {
    Flowable<List<Note>> getNotes();
    Completable insertNote(Note note);
    Completable deleteNote(Note note);
    Completable updateNote(Note note);
}
