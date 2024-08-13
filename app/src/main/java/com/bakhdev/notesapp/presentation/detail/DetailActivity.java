package com.bakhdev.notesapp.presentation.detail;

import android.content.Intent;
import android.os.Bundle;

import com.bakhdev.notesapp.R;
import com.bakhdev.notesapp.databinding.ActivityDetailBinding;
import com.bakhdev.notesapp.domain.model.Note;
import com.bakhdev.notesapp.presentation.add.AddNoteActivity;
import com.bakhdev.notesapp.presentation.base.BaseActivity;

public class DetailActivity extends BaseActivity<ActivityDetailBinding> {
    public static String ID_VALUE = "ID_VALUE";
    public static String TITLE_VALUE = "TITLE_VALUE";
    public static String DESC_VALUE = "DESC_VALUE";

    private Note note;

    @Override
    protected ActivityDetailBinding setBinding() {
        return ActivityDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpToolbar();
        setData();
    }

    private void setUpToolbar() {
        binding.topAppBar.setNavigationOnClickListener(view -> onBackPressed());
        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.edit) {
                toEdit();
                return true;
            }
            return false;
        });
    }

    private void setData() {
        int id = getIntent().getIntExtra(ID_VALUE, 0);
        String title = getIntent().getStringExtra(TITLE_VALUE);
        String desc = getIntent().getStringExtra(DESC_VALUE);

        note = new Note(id, title, desc);

        binding.titleTv.setText(title);
        binding.descTv.setText(desc);
    }

    private void toEdit() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        intent.putExtra(AddNoteActivity.IS_EDIT, true);
        intent.putExtra(AddNoteActivity.ID_VALUE, note.getId());
        intent.putExtra(AddNoteActivity.TITLE_VALUE, note.getTitle());
        intent.putExtra(AddNoteActivity.DESC_VALUE, note.getDesc());
        startActivity(intent);
    }
}