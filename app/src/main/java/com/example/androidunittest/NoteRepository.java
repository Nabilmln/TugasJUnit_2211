package com.example.androidunittest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteRepository {
    private Map<String, Note> notes;

    public NoteRepository() {
        notes = new HashMap<>();
    }

    public Note addNote(String title, String content) {
        Note note = new Note(title, content);
        notes.put(note.getId(), note);
        return note;
    }

    public Note getNote(String id) {
        return notes.get(id);
    }

    public List<Note> getAllNotes() {
        return new ArrayList<>(notes.values());
    }

    public boolean updateNote(String id, String title, String content) {
        Note note = notes.get(id);
        if (note == null) {
            return false;
        }
        note.setTitle(title);
        note.setContent(content);
        return true;
    }

    public boolean deleteNote(String id) {
        if (notes.containsKey(id)) {
            notes.remove(id);
            return true;
        }
        return false;
    }

    public int getNotesCount() {
        return notes.size();
    }
}