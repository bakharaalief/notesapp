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
import com.bakhdev.notesapp.presentation.adapter.OnItemClickListener;
import com.bakhdev.notesapp.presentation.add.AddNoteActivity;
import com.bakhdev.notesapp.presentation.base.BaseActivity;
import com.bakhdev.notesapp.presentation.dialog.LoadingDialog;
import com.bakhdev.notesapp.presentation.dialog.MessageDialog;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements View.OnClickListener, OnItemClickListener {
    private MainViewModel mainViewModel;
    private NotesAdapter notesAdapter;
    private CompositeDisposable compositeDisposable;
    private LoadingDialog loadingDialog;
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
        loadingDialog = new LoadingDialog();
        messageDialog = new MessageDialog();
    }

    private void setUpAdapter() {
        notesAdapter = new NotesAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.notesRv.setLayoutManager(linearLayoutManager);
        binding.notesRv.setAdapter(notesAdapter);
    }

    private void showLoadingDialog(boolean show) {
        if (show) {
            loadingDialog.setCancelable(false);
            loadingDialog.show(getSupportFragmentManager(), "Loading_Dialog");
        } else {
            loadingDialog.dismiss();
        }
    }

    private void showMessageDialog(String title, String msg) {
        messageDialog.setTitle(title);
        messageDialog.setMsg(msg);
        messageDialog.show(getSupportFragmentManager(), "MSG_DIALOG");
    }

    @Override
    public void onItemClick(Note note) {
        System.out.println(note.getTitle());
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
        showLoadingDialog(true);
        compositeDisposable.add(
                mainViewModel.getNotes()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSubscriber<List<Note>>() {
                            @Override
                            public void onNext(List<Note> notes) {
                                notesAdapter.submitList(notes);
                                showLoadingDialog(false);
                            }

                            @Override
                            public void onError(Throwable t) {
                                showMessageDialog("error", t.getMessage());
                                showLoadingDialog(false);
                            }

                            @Override
                            public void onComplete() {
                                showLoadingDialog(false);
                            }
                        })
        );
    }
}