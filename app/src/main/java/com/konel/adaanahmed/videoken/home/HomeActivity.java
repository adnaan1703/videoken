package com.konel.adaanahmed.videoken.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.SearchView;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.base.VkBaseActivity;
import com.konel.adaanahmed.videoken.db.Lesson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends VkBaseActivity implements SearchView.OnQueryTextListener,
        HomeActivityContract.View {

    @BindView(R.id.home_activity_search)
    SearchView searchView;

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

    private void initComponents() {
        searchView.setOnQueryTextListener(this);
        presenter = new HomeActivityPresenter(this, getSupportLoaderManager());
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
    }

    @Override
    public void showNoLesson() {
    }
}
