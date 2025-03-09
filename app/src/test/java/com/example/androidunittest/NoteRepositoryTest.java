package com.example.androidunittest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NoteRepositoryTest {
    private NoteRepository noteRepository;

    @Before
    public void setup() {
        noteRepository = new NoteRepository();
    }

    /*
     * Test Case 1: Menguji fungsi addNote dan getAllNotes
     * Tujuan: Memastikan bahwa fungsi addNote berhasil menambahkan catatan baru
     * ke repository dan getAllNotes mengembalikan daftar catatan yang sesuai
     *
     * Langkah pengujian:
     * 1. Menambahkan 3 catatan ke repository
     * 2. Mengambil semua catatan menggunakan getAllNotes
     * 3. Memastikan jumlah catatan adalah 3
     * 4. Memastikan data catatan sesuai dengan yang diharapkan
     */
    @Test
    public void testAddNoteAndGetAllNotes() {
        // Menambahkan 3 catatan
        noteRepository.addNote("Judul 1", "Isi catatan 1");
        noteRepository.addNote("Judul 2", "Isi catatan 2");
        noteRepository.addNote("Judul 3", "Isi catatan 3");

        // Mengambil semua catatan
        List<Note> notes = noteRepository.getAllNotes();

        // Verifikasi jumlah catatan
        assertEquals(3, notes.size());

        // Verifikasi data catatan
        boolean foundNote1 = false;
        boolean foundNote2 = false;
        boolean foundNote3 = false;

        for (Note note : notes) {
            if (note.getTitle().equals("Judul 1") && note.getContent().equals("Isi catatan 1")) {
                foundNote1 = true;
            } else if (note.getTitle().equals("Judul 2") && note.getContent().equals("Isi catatan 2")) {
                foundNote2 = true;
            } else if (note.getTitle().equals("Judul 3") && note.getContent().equals("Isi catatan 3")) {
                foundNote3 = true;
            }
        }

        assertTrue(foundNote1);
        assertTrue(foundNote2);
        assertTrue(foundNote3);
    }

    /*
     * Test Case 2: Menguji fungsi updateNote
     * Tujuan: Memastikan bahwa fungsi updateNote berhasil memperbarui catatan yang ada
     * dan gagal jika mencoba memperbarui catatan yang tidak ada
     *
     * Langkah pengujian:
     * 1. Menambahkan catatan baru
     * 2. Mendapatkan ID catatan tersebut
     * 3. Memperbarui catatan dengan ID yang valid
     * 4. Memastikan pembaruan berhasil
     * 5. Memastikan data catatan berubah sesuai pembaruan
     * 6. Mencoba memperbarui catatan dengan ID yang tidak valid
     * 7. Memastikan pembaruan gagal
     */
    @Test
    public void testUpdateNote() {
        // Menambahkan catatan
        Note note = noteRepository.addNote("Judul Awal", "Isi Awal");
        String validId = note.getId();

        // Memperbarui catatan dengan ID valid
        boolean updateResult = noteRepository.updateNote(validId, "Judul Baru", "Isi Baru");

        // Verifikasi pembaruan berhasil
        assertTrue(updateResult);

        // Mengambil catatan yang diperbarui
        Note updatedNote = noteRepository.getNote(validId);

        // Verifikasi data catatan berubah
        assertEquals("Judul Baru", updatedNote.getTitle());
        assertEquals("Isi Baru", updatedNote.getContent());

        // Mencoba memperbarui catatan dengan ID yang tidak valid
        boolean invalidUpdateResult = noteRepository.updateNote("id_tidak_valid", "Judul Invalid", "Isi Invalid");

        // Verifikasi pembaruan gagal
        assertFalse(invalidUpdateResult);
    }
}