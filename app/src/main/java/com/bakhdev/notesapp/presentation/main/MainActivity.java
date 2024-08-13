package com.bakhdev.notesapp.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bakhdev.notesapp.R;
import com.bakhdev.notesapp.databinding.ActivityMainBinding;
import com.bakhdev.notesapp.presentation.add.AddNoteActivity;
import com.bakhdev.notesapp.presentation.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding>
        implements View.OnClickListener {

    @Override
    protected ActivityMainBinding setBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_btn){
            toAddActivity();
        }
    }

    private void toAddActivity(){
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}