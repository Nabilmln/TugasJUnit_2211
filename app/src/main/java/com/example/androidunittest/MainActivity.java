package com.example.androidunittest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etContent;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private TextView tvEmpty;

    private NoteAdapter noteAdapter;
    private NoteRepository noteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi repository
        noteRepository = new NoteRepository();

        // Inisialisasi view
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        btnAdd = findViewById(R.id.btn_add);
        recyclerView = findViewById(R.id.recycler_view);
        tvEmpty = findViewById(R.id.tv_empty);

        // Setup RecyclerView
        noteAdapter = new NoteAdapter(noteRepository.getAllNotes());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

        // Setup listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Judul dan konten tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = noteRepository.addNote(title, content);
                noteAdapter.updateNotes(noteRepository.getAllNotes());
                updateEmptyViewVisibility();

                // Clear fields
                etTitle.setText("");
                etContent.setText("");

                Toast.makeText(MainActivity.this, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });

        updateEmptyViewVisibility();
    }

    private void updateEmptyViewVisibility() {
        if (noteRepository.getNotesCount() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}