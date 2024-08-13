package com.bakhdev.notesapp.domain.usecase;

import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Singleton
public class NoteUseCaseImpl implements NoteUseCase {
    NoteRepository noteRepository;

    @Inject
    public NoteUseCaseImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Flowable<List<Note>> getNotes() {
        return noteRepository.getNotes();
    }

    @Override
    public Completable insertNote(Note note) {
        return noteRepository.insertNote(note);
    }

    @Override
    public Completable deleteNote(Note note) {
        return noteRepository.deleteNote(note);
    }

    @Override
    public Completable updateNote(Note note) {
        return noteRepository.updateNote(note);
    }
}
