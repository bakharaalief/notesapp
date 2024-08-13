package com.bakhdev.notesapp.presentation.add;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.bakhdev.notesapp.R;
import com.bakhdev.notesapp.databinding.ActivityAddNoteBinding;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.presentation.base.BaseActivity;
import com.bakhdev.notesapp.presentation.dialog.LoadingDialog;
import com.bakhdev.notesapp.presentation.dialog.MessageDialog;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

@AndroidEntryPoint
public class AddNoteActivity extends BaseActivity<ActivityAddNoteBinding>
        implements View.OnClickListener {
    private CompositeDisposable compositeDisposable;
    private AddNoteViewModel addNoteViewModel;
    private LoadingDialog loadingDialog;
    private MessageDialog messageDialog;

    @Override
    protected ActivityAddNoteBinding setBinding() {
        return ActivityAddNoteBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
        setUpToolbar();
        binding.addBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_btn) {
            checkData();
        }
    }

    private void setUp() {
        compositeDisposable = new CompositeDisposable();
        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        loadingDialog = new LoadingDialog();
        messageDialog = new MessageDialog();
    }

    private void setUpToolbar() {
        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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

    private void checkData() {
        String title = "";
        String desc = "";
        if (binding.titleInput.getEditText() != null && binding.descInput.getEditText() != null) {
            title = binding.titleInput.getEditText().getText().toString();
            desc = binding.descInput.getEditText().getText().toString();
        }

        if (!title.isEmpty() && !desc.isEmpty()) {
            insertData(title, desc);
        } else {
            showMessageDialog(getString(R.string.form_empty), getString(R.string.please_fill_the_data));
        }
    }

    private void insertData(String title, String desc) {
        showLoadingDialog(true);
        Note note = new Note(0, title, desc);
        compositeDisposable.add(
                addNoteViewModel.insertNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                showLoadingDialog(false);
                                onBackPressed();
                            }

                            @Override
                            public void onError(Throwable e) {
                                showMessageDialog(getString(R.string.status), e.getMessage());
                                showLoadingDialog(false);
                            }
                        })
        );
    }
}