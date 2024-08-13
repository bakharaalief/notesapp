package com.bakhdev.notesapp.domain.usecase;

import com.bakhdev.notesapp.data.NoteRepositoryImpl;
import com.bakhdev.notesapp.data.room.AppDatabase;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.repository.NoteRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

@Singleton
public class NoteUseCaseImpl implements NoteUseCase {
    NoteRepository noteRepository;

    @Inject
    public NoteUseCaseImpl(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @Override
    public Completable insertNote(Note note) {
        return noteRepository.insertNote(note);
    }
}
