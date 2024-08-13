package com.bakhdev.notesapp.presentation.main;

import androidx.lifecycle.ViewModel;

import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.usecase.NoteUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private final NoteUseCase noteUseCase;

    @Inject
    public MainViewModel(NoteUseCase noteUseCase) {
        this.noteUseCase = noteUseCase;
    }

    Flowable<List<Note>> getNotes() {
        return noteUseCase.getNotes();
    }

    Completable deleteNote(Note note) {
        return noteUseCase.deleteNote(note);
    }
}
