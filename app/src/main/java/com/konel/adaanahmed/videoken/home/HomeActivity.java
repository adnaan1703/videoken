package com.konel.adaanahmed.videoken.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;

import com.konel.adaanahmed.videoken.CodeUtil;
import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.VkBaseActivity;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends VkBaseActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.home_activity_search)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initComponents();
    }

    private void initComponents() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.trim();
        if (!TextUtils.isEmpty(query) && !TextUtils.isEmpty(CodeUtil.getYoutubeIdFromUrl(query))) {
            NavigationUtil.startClassRoomActivity(this, CodeUtil.getYoutubeIdFromUrl(query));
        } else {
            showToast("Please Enter the complete youtube url");
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
