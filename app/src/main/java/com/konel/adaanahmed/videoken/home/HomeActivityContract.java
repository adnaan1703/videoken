package com.konel.adaanahmed.videoken.home;

import com.konel.adaanahmed.videoken.base.BasePresenter;
import com.konel.adaanahmed.videoken.base.BaseView;
import com.konel.adaanahmed.videoken.db.Lesson;

import java.util.ArrayList;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 3:07 AM
 */


interface HomeActivityContract {
    interface View extends BaseView {
        void showLessons(ArrayList<Lesson> lessons);

        void showNoLesson();
    }

    interface Presenter extends BasePresenter<View> {
        void getLessons();

        void onQuerySubmitted(String videoId);
    }
}
