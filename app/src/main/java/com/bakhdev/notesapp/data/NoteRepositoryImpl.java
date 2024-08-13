package com.bakhdev.notesapp.data;

import com.bakhdev.notesapp.data.room.AppDatabase;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.repository.NoteRepository;
import com.bakhdev.notesapp.helper.Mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

@Singleton
public class NoteRepositoryImpl implements NoteRepository {
    AppDatabase appDatabase;

    @Inject
    public NoteRepositoryImpl(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    public Completable insertNote(Note note) {
        return appDatabase.noteDao().insert(Mapper.toNoteEntity(note));
    }
}
