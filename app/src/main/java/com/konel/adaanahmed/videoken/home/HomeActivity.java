package com.konel.adaanahmed.videoken.home;

import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;

import com.konel.adaanahmed.videoken.R;
import com.konel.adaanahmed.videoken.VkBaseActivity;
import com.konel.adaanahmed.videoken.navigation.NavigationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends VkBaseActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.home_activity_search)
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initComponents();
    }


    @Override
    public void initComponents() {
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.trim();
        if (!TextUtils.isEmpty(query)) {
            NavigationUtil.startClassRoomActivity(this, query);
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
