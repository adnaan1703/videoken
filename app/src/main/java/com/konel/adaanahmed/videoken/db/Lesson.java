package com.konel.adaanahmed.videoken.db;

import android.support.annotation.NonNull;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 8:00 PM
 */


@SuppressWarnings("unused")
public class Lesson extends RealmObject {

    @PrimaryKey
    @Required
    private String videoId;
    private RealmList<Note> notes = new RealmList<>();

    public Lesson() {
    }

    public Lesson(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }

    public void addNotes(RealmList<Note> notes) {
        for (Note note : notes) {
            addNote(note);
        }
    }

    public void addNote(@NonNull Note note) {

        if (notes.size() > 0) {
            if (note.getTime() <= notes.get(0).getTime())
                notes.add(0, note);
            else if (note.getTime() >= notes.get(notes.size() - 1).getTime())
                notes.add(note);
            else {
                for (int i = 0; i < notes.size() - 1; i++) {
                    if (note.getTime() >= notes.get(i).getTime() && note.getTime() < notes.get(i + 1).getTime()) {
                        notes.add(i + 1, note);
                        break;
                    }
                }
            }
        } else
            notes.add(note);
    }
}
