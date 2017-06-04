package com.konel.adaanahmed.videoken.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.SearchView;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.db.Lesson;
import com.konel.adaanahmed.videoken.db.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends VkBaseActivity implements SearchView.OnQueryTextListener,
        HomeActivityContract.View, HomeNotesListAdapter.HomeNotesInteractionListener {

    @BindView(R.id.home_activity_search)
    SearchView searchView;
    @BindView(R.id.home_activity_lesson_list)
    RecyclerView rvLessons;

    private HomeActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onStop();
    }

    private void initComponents() {
        searchView.setOnQueryTextListener(this);
        presenter = new HomeActivityPresenter(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.trim();
        if (!TextUtils.isEmpty(query) && !TextUtils.isEmpty(CodeUtil.getYoutubeIdFromUrl(query))) {
            presenter.onQuerySubmitted(CodeUtil.getYoutubeIdFromUrl(query));
        } else {
            showToast("Please Enter the correct youtube url");
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @NonNull
    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void showLessons(ArrayList<Lesson> lessons) {
        rvLessons.setLayoutManager(new LinearLayoutManager(this));
        rvLessons.setAdapter(new HomeLessonListAdapter(lessons, this));
    }

    @Override
    public void showNoLesson() {
        // TODO: 04/Jun/17 @adnaan: nothing as for now..
    }

    @Override
    public void onNoteSelected(String videoId, Note note) {
        presenter.onNoteSelected(videoId, note.getTime());
    }
}
