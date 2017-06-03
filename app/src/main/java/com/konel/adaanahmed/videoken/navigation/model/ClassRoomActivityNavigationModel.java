package com.konel.adaanahmed.videoken.navigation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaan.1703@gmail.com>
 * @version : 1.0.0
 * @since : 03 Jun 2017 2:26 PM
 */


public class ClassRoomActivityNavigationModel implements Parcelable {

    private String youtubeUrl;

    public ClassRoomActivityNavigationModel(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.youtubeUrl);
    }

    private ClassRoomActivityNavigationModel(Parcel in) {
        this.youtubeUrl = in.readString();
    }

    public static final Parcelable.Creator<ClassRoomActivityNavigationModel> CREATOR = new Parcelable.Creator<ClassRoomActivityNavigationModel>() {
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
