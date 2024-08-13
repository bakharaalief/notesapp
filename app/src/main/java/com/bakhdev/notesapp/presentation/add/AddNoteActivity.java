package com.bakhdev.notesapp.presentation.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.bakhdev.notesapp.R;
import com.bakhdev.notesapp.databinding.ActivityAddNoteBinding;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.presentation.base.BaseActivity;
import com.bakhdev.notesapp.presentation.dialog.MessageDialog;
import com.bakhdev.notesapp.presentation.main.MainActivity;

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
    private MessageDialog messageDialog;

    public static String IS_EDIT = "ID_EDIT";
    public static String ID_VALUE = "ID_VALUE";
    public static String TITLE_VALUE = "TITLE_VALUE";
    public static String DESC_VALUE = "DESC_VALUE";

    private boolean isEdit = false;
    private Note note;

    @Override
    protected ActivityAddNoteBinding setBinding() {
        return ActivityAddNoteBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
        setUpToolbar();
        setUpButton();
        setUp();
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

    private void setData() {
        isEdit = getIntent().getBooleanExtra(IS_EDIT, false);
        int id = getIntent().getIntExtra(ID_VALUE, 0);
        String title = getIntent().getStringExtra(TITLE_VALUE);
        String desc = getIntent().getStringExtra(DESC_VALUE);
        note = new Note(id, title, desc);
        if (binding.titleInput.getEditText() != null && binding.descInput.getEditText() != null) {
            binding.titleInput.getEditText().setText(title);
            binding.descInput.getEditText().setText(desc);
        }
    }

    private void setUpToolbar() {
        binding.topAppBar.setNavigationOnClickListener(view -> onBackPressed());
        String title = isEdit ? "Edit Note" : "Add Note";
        binding.topAppBar.setTitle(title);
    }

    private void setUpButton() {
        String title = isEdit ? getString(R.string.edit) : getString(R.string.add);
        binding.addBtn.setText(title);
    }

    private void setUp() {
        compositeDisposable = new CompositeDisposable();
        addNoteViewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        messageDialog = new MessageDialog();
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
            if (isEdit) updateData(note.getId(), title, desc);
            else insertData(title, desc);
        } else {
            showMessageDialog(getString(R.string.form_empty), getString(R.string.please_fill_the_data));
        }
    }

    private void insertData(String title, String desc) {
        Note note = new Note(0, title, desc);
        compositeDisposable.add(
                addNoteViewModel.insertNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                onBackPressed();
                            }

                            @Override
                            public void onError(Throwable e) {
                                showMessageDialog(getString(R.string.status), e.getMessage());
                            }
                        })
        );
    }

    private void updateData(int id, String title, String desc) {
        Note note = new Note(id, title, desc);
        compositeDisposable.add(
                addNoteViewModel.updateNote(note)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                toMain();
                            }

                            @Override
                            public void onError(Throwable e) {
                                showMessageDialog(getString(R.string.status), e.getMessage());
                            }
                        })
        );
    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}