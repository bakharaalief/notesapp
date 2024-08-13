package com.bakhdev.notesapp.di;

import com.bakhdev.notesapp.data.NoteRepositoryImpl;
import com.bakhdev.notesapp.domain.repository.NoteRepository;
import com.bakhdev.notesapp.domain.usecase.NoteUseCase;
import com.bakhdev.notesapp.domain.usecase.NoteUseCaseImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract NoteUseCase provideNoteUseCase(NoteUseCaseImpl noteUseCaseImpl);
}
