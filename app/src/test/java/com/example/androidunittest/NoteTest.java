package com.example.androidunittest;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NoteTest {

    /*
     * Test Case 1: Menguji konstruktor dan getter Note
     * Tujuan: Memastikan bahwa konstruktor Note membuat objek dengan benar
     * dan getter mengembalikan nilai yang sesuai
     *
     * Langkah pengujian:
     * 1. Membuat objek Note dengan judul dan konten tertentu
     * 2. Memastikan ID tidak null (otomatis dibuat)
     * 3. Memastikan judul dan konten sesuai dengan yang dimasukkan
     * 4. Memastikan createdAt dan updatedAt tidak null dan diinisialisasi
     */
    @Test
    public void testNoteConstructorAndGetters() {
        // Membuat objek Note
        String title = "Judul Test";
        String content = "Konten Test";
        Note note = new Note(title, content);

        // Memastikan ID tidak null
        assertNotNull(note.getId());

        // Memastikan judul dan konten sesuai
        assertEquals(title, note.getTitle());
        assertEquals(content, note.getContent());

        // Memastikan tanggal dibuat
        assertNotNull(note.getCreatedAt());
        assertNotNull(note.getUpdatedAt());
    }

    /*
     * Test Case 2: Menguji setter dan updatedAt Note
     * Tujuan: Memastikan bahwa setter mengubah nilai dengan benar
     * dan updatedAt diperbarui saat nilai diubah
     *
     * Langkah pengujian:
     * 1. Membuat objek Note
     * 2. Menyimpan waktu updatedAt awal
     * 3. Menunggu untuk memastikan perbedaan waktu
     * 4. Mengubah judul dengan setter
     * 5. Memastikan judul berubah
     * 6. Memastikan updatedAt berubah
     * 7. Mengulangi langkah yang sama untuk konten
     */
    @Test
    public void testNoteSettersAndUpdatedAt() throws InterruptedException {
        // Membuat objek Note
        Note note = new Note("Judul Original", "Konten Original");

        // Menyimpan waktu updatedAt awal
        Date initialUpdateTime = note.getUpdatedAt();

        // Menunggu untuk memastikan perbedaan waktu
        Thread.sleep(100);

        // Mengubah judul
        note.setTitle("Judul Baru");

        // Memastikan judul berubah
        assertEquals("Judul Baru", note.getTitle());

        // Memastikan updatedAt berubah
        assertTrue(note.getUpdatedAt().after(initialUpdateTime));

        // Menyimpan waktu updatedAt setelah perubahan judul
        Date titleUpdateTime = note.getUpdatedAt();

        // Menunggu untuk memastikan perbedaan waktu
        Thread.sleep(100);

        // Mengubah konten
        note.setContent("Konten Baru");

        // Memastikan konten berubah
        assertEquals("Konten Baru", note.getContent());

        // Memastikan updatedAt berubah lagi
        assertTrue(note.getUpdatedAt().after(titleUpdateTime));
    }
}