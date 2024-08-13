package com.bakhdev.notesapp.di;

import com.bakhdev.notesapp.data.NoteRepositoryImpl;
import com.bakhdev.notesapp.domain.repository.NoteRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract NoteRepository provideNoteRepository(NoteRepositoryImpl noteRepositoryImpl);
}
