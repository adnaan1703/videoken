package com.konel.adaanahmed.videoken.db;

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

    public void setNotes(RealmList<Note> notes) {
        for (Note note : notes) {
            this.notes.add(note);
        }
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }
}
