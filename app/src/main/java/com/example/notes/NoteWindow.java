package com.example.notes;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.databinding.FragmentNoteWindowBinding;

public class NoteWindow extends Fragment {
    private FragmentNoteWindowBinding binding;

    private String noteTitle, noteText, dateTime;
    private OnNoteWindowDataListener fragmentListener;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public NoteWindow() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteWindowDataListener) {
            fragmentListener = (OnNoteWindowDataListener) context;
        }
        else {
            Log.d("AttachError", "Error");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteWindowBinding.inflate(inflater, container, false);

        binding.saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteTitle = binding.noteTitle.getText().toString();
                noteText = binding.textInputEditText.getText().toString();
                dateTime = "";


                fragmentListener.onNoteWindowDataListener(noteTitle, noteText, dateTime);
            }
        });
        return binding.getRoot();
    }

    public interface OnNoteWindowDataListener {
        void onNoteWindowDataListener(String noteTitle, String noteText, String dateTime);
    }
}