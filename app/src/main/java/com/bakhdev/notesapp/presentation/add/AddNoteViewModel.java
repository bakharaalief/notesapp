package com.bakhdev.notesapp.presentation.add;

import androidx.lifecycle.ViewModel;

import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.domain.usecase.NoteUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Completable;

@HiltViewModel
public class AddNoteViewModel extends ViewModel {
    private final NoteUseCase noteUseCase;

    @Inject
    public AddNoteViewModel(NoteUseCase noteUseCase) {
        this.noteUseCase = noteUseCase;
    }

    Completable insertNote(Note note){
        return noteUseCase.insertNote(note);
    }
}
