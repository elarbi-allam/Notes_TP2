package com.elarbiallam.notes;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    // Liste statique pour que les données survivent entre les activités
    private static List<Note> notes = new ArrayList<>();

    public static void addNote(Note note) {
        notes.add(note);
    }

    public static List<Note> getNotes() {
        return notes;
    }
}
