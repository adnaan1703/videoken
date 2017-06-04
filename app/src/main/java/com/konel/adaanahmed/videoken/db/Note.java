package com.konel.adaanahmed.videoken.db;

import io.realm.RealmObject;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 7:59 PM
 */

@SuppressWarnings("unused")
public class Note extends RealmObject {

    private String text;
    private int time;

    public Note() {
    }

    public Note(String text, int time) {
        this.text = text;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
