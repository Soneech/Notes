package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteWindow.OnNoteWindowDataListener {
    private ActivityMainBinding binding;
    private NoteWindow noteWindow;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addNoteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addNoteBtn) {
            noteWindow = new NoteWindow();
            createNote(noteWindow);
        }
    }

    public void createNote(NoteWindow noteLayout) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.noteBody, noteLayout);
        fragmentTransaction.addToBackStack("stack");
        fragmentTransaction.commit();
    }

    public void showNotesList() {  // инфа из бд

    }

    @Override
    public void onNoteWindowDataListener(String noteTitle, String noteText, String dateTime) {
        Log.d("DATA_FROM_FRAGMENT", noteTitle);
        // fragmentTransaction.remove(noteWindow);
        // showNotesList();
        binding.noteView.setText(noteTitle);
    }
}