package com.example.userrepoapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.userrepoapp.adapter.ReposAdapter;
import com.example.userrepoapp.model.RepoItem;
import com.example.userrepoapp.presenter.RepoPresenter;
import com.example.userrepoapp.view.repodetails.RefreshDataInterface;
import com.example.userrepoapp.view.repodetails.RepoDetailFragment;
import com.example.userrepoapp.view.repodetails.RepoDetailInterface;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AppContract.View, RefreshDataInterface {

    private static final String TAG = "UserRepoApp";
    AppContract.Presenter mPresenter;
    ReposAdapter reposAdapter;
    TextView searchBox;
    Button searchButton;
    RecyclerView reposRecycleView;
    LinearLayoutManager linearLayoutManager;
    RepoDetailFragment repoDetailFragment;
    RepoDetailInterface mRepoDetailInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initViews();

        mPresenter = new RepoPresenter(MainActivity.this);
        searchButton.setOnClickListener(new SearchFunction());
    }

    @Override
    public void showRepos(List<RepoItem> repoList) {
        Log.d(TAG, "showRepos: repository count: " + repoList.size());
        reposAdapter = new ReposAdapter(repoList, MainActivity.this);
        reposAdapter.setRepoDetailInterfaceListener(new RepoDetailInterface() {
            @Override
            public void showDetails(RepoItem repoItem) {
                detailsFunction(repoItem);
            }
        });
        reposRecycleView.setAdapter(reposAdapter);
    }

    public void initViews(){
        searchBox = findViewById(R.id.searchTv);
        searchButton = findViewById(R.id.searchBtn);
        reposRecycleView = findViewById(R.id.repoListRV);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        reposRecycleView.setLayoutManager(linearLayoutManager);
        reposRecycleView.addItemDecoration( new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void detailsFunction(RepoItem repoItem) {
        repoDetailFragment = new RepoDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.MainLayout, repoDetailFragment, "repoDetail");
        fragmentTransaction.addToBackStack("addedRepoDetail");
        fragmentTransaction.commit();
//        repoDetail.setRepoItem(repoItem);
        Bundle detailFragArgs = new Bundle();
        detailFragArgs.putSerializable("repoDetails", repoItem);
        repoDetailFragment.setArguments(detailFragArgs);
    }

    class SearchFunction implements View.OnClickListener  {

        @Override
        public void onClick(View v) {
            if (!searchBox.getText().toString().equals("")) {
                mPresenter.fetchRepos(searchBox.getText().toString());
            }
        }
    }

    @Override
    public void dataChanged(){
        reposAdapter.notifyDataSetChanged();
    }


}
