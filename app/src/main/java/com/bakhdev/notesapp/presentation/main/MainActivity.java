package com.bakhdev.notesapp.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bakhdev.notesapp.R;
import com.bakhdev.notesapp.databinding.ActivityMainBinding;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.presentation.adapter.NotesAdapter;
import com.bakhdev.notesapp.presentation.add.AddNoteActivity;
import com.bakhdev.notesapp.presentation.base.BaseActivity;
import com.bakhdev.notesapp.presentation.detail.DetailActivity;
import com.bakhdev.notesapp.presentation.dialog.MessageDialog;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements View.OnClickListener {
    private MainViewModel mainViewModel;
    private NotesAdapter notesAdapter;
    private CompositeDisposable compositeDisposable;
    private MessageDialog messageDialog;

    @Override
    protected ActivityMainBinding setBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
        setUpAdapter();
        getNotesData();
        binding.addBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void setUp() {
        compositeDisposable = new CompositeDisposable();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        messageDialog = new MessageDialog();
    }

    private void setUpAdapter() {
        notesAdapter = new NotesAdapter(this::onItemClick, this::onItemDeleteClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.notesRv.setLayoutManager(linearLayoutManager);
        binding.notesRv.setAdapter(notesAdapter);
    }

    private void showMessageDialog(String title, String msg) {
        messageDialog.setTitle(title);
        messageDialog.setMsg(msg);
        messageDialog.show(getSupportFragmentManager(), "MSG_DIALOG");
    }

    public void onItemClick(Note note) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ID_VALUE, note.getId());
        intent.putExtra(DetailActivity.TITLE_VALUE, note.getTitle());
        intent.putExtra(DetailActivity.DESC_VALUE, note.getDesc());
        startActivity(intent);
    }

    public void onItemDeleteClick(Note note) {
        deleteNote(note);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_btn) {
            toAddActivity();
        }
    }

    private void toAddActivity() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void getNotesData() {
        compositeDisposable.add(
                mainViewModel.getNotes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<List<Note>>() {
                            @Override
                            public void onNext(List<Note> notes) {
                                if (notes.isEmpty())
                                    binding.emptyLayout.setVisibility(View.VISIBLE);
                                else binding.emptyLayout.setVisibility(View.GONE);
                                notesAdapter.submitList(notes);
                            }

                            @Override
                            public void onError(Throwable t) {
                                showMessageDialog("error", t.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );
    }

    private void deleteNote(Note note) {

        compositeDisposable.add(
                mainViewModel.deleteNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                showMessageDialog(getString(R.string.status), e.getMessage());
                            }
                        })
        );
    }
}