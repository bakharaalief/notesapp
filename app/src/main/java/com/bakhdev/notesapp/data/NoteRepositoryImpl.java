package com.bakhdev.notesapp.data;

import com.bakhdev.notesapp.data.room.AppDatabase;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.repository.NoteRepository;
import com.bakhdev.notesapp.helper.Mapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Singleton
public class NoteRepositoryImpl implements NoteRepository {
    AppDatabase appDatabase;

    @Inject
    public NoteRepositoryImpl(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Flowable<List<Note>> getNotes() {
        return appDatabase.noteDao().getNotes().map(Mapper::toListNote);
    }

    @Override
    public Completable insertNote(Note note) {
        return appDatabase.noteDao().insert(Mapper.toNoteEntity(note));
    }

    @Override
    public Completable deleteNote(Note note) {
        return appDatabase.noteDao().delete(Mapper.toNoteEntity(note));
    }

    @Override
    public Completable updateNote(Note note) {
        return appDatabase.noteDao().update(Mapper.toNoteEntity(note));
    }
}
