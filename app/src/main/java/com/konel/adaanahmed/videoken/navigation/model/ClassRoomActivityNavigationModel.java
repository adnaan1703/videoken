package com.konel.adaanahmed.videoken.navigation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:26 PM
 */


public class ClassRoomActivityNavigationModel implements Parcelable {

    private String youtubeId;
    private int startTime;

    public ClassRoomActivityNavigationModel(String youtubeId) {
        this(youtubeId, 0);
    }

    public ClassRoomActivityNavigationModel(String youtubeId, int startTime) {
        this.youtubeId = youtubeId;
        this.startTime = startTime;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public int getStartTime() {
        return startTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.youtubeId);
        dest.writeInt(this.startTime);
    }

    private ClassRoomActivityNavigationModel(Parcel in) {
        this.youtubeId = in.readString();
        this.startTime = in.readInt();
    }

    public static final Creator<ClassRoomActivityNavigationModel> CREATOR = new Creator<ClassRoomActivityNavigationModel>() {
        @Override
        public ClassRoomActivityNavigationModel createFromParcel(Parcel source) {
            return new ClassRoomActivityNavigationModel(source);
        }

        @Override
        public ClassRoomActivityNavigationModel[] newArray(int size) {
            return new ClassRoomActivityNavigationModel[size];
        }
    };
}
