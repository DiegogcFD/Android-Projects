package com.diego.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        final int noteId = intent.getIntExtra("noteId", -1);

        if(noteId == -1) {

            editText.setText(MainActivity.notes.get(noteId));

        }

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable text) {

            }

            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence text, int start, int before, int count) {
                MainActivity.notes.set(noteId, String.valueOf(text));
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }
        });

    }
}
