package com.example.userrepoapp.view.repodetails;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.userrepoapp.MainActivity;
import com.example.userrepoapp.R;
import com.example.userrepoapp.model.RepoItem;


/**
 * A simple {@link Fragment} subclass.
 */

public class RepoDetailFragment extends Fragment {

    TextView repoName, starCount, openIssues, ownerName;
    String imageLink;
    ImageView avatar, star;
    SharedPreferences sharedPreferences;
    Context mContext;
    RepoItem repoItem;
    RefreshDataInterface refreshDataInterface;

    public RepoDetailFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_repo_detail, container, false);
        repoName = v.findViewById(R.id.RepoDetailName);
        starCount= v.findViewById(R.id.RepoDetailStarCount);
        openIssues= v.findViewById(R.id.RepoDetailOpenIssues);
        ownerName= v.findViewById(R.id.RepoDetailOwnerName);
        avatar = v.findViewById(R.id.RepoDetailAvatar);
        star = v.findViewById(R.id.RepoDetailStar);
        mContext = getContext();
        refreshDataInterface = (MainActivity) getActivity();

        setRepoItem( (RepoItem) getArguments().getSerializable("repoDetails"));

        star.setOnClickListener(new ToggleFavorite());

        return v;
    }

    void setRepoItem(RepoItem repoItem){
        this.repoItem = repoItem;
        repoName.setText(repoItem.getName());
        starCount.setText(repoItem.getStargazersCount().toString());
        openIssues.setText(repoItem.getOpenIssues().toString());
        ownerName.setText(repoItem.getOwner().getLogin());
        imageLink = repoItem.getOwner().getAvatarUrl();
        Glide.with(this).load(imageLink).into(avatar).clearOnDetach();
        sharedPreferences = mContext.getSharedPreferences("repoFavPreferences",Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(String.valueOf(repoItem.getId()), false)){
            star.setImageResource(R.drawable.starfilled);
        } else {
            star.setImageResource(R.drawable.star);
        }

    }

    class ToggleFavorite implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            sharedPreferences.edit().putBoolean( String.valueOf(repoItem.getId()), !sharedPreferences.getBoolean(String.valueOf(repoItem.getId()), false) ).apply();
            if (sharedPreferences.getBoolean(String.valueOf(repoItem.getId()), false)){
                star.setImageResource(R.drawable.starfilled);
                refreshDataInterface.dataChanged();
            } else {
                star.setImageResource(R.drawable.star);
                refreshDataInterface.dataChanged();
            }
        }
    }

}
