package com.bakhdev.notesapp.domain.usecase;

import com.bakhdev.notesapp.domain.model.Note;

import io.reactivex.Completable;

public interface NoteUseCase {
    Completable insertNote(Note note);
}
