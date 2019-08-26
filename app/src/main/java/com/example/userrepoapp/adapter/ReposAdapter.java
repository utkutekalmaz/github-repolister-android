package com.example.userrepoapp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userrepoapp.R;
import com.example.userrepoapp.view.repodetails.RepoDetailInterface;
import com.example.userrepoapp.model.RepoItem;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposHolder> {
    private static final String TAG = "RepoApp";
    List<RepoItem> repos;
    Context context;
    RepoDetailInterface repoDetailInterface;
    SharedPreferences sharedPreferences;

    public ReposAdapter(List<RepoItem> repos, Context context){
        this.repos = repos;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("repoFavPreferences",Context.MODE_PRIVATE);
        Log.d(TAG, "ReposAdapter: adapter created with item size: " + getItemCount());
    }

    @NonNull
    @Override
    public ReposHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: view holders created: "+i);
        View v = LayoutInflater.from(context).inflate(R.layout.repo_list_item,viewGroup,false);
        ReposHolder reposHolder = new ReposHolder(v);
        return reposHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReposHolder reposHolder, final int pos) {
        Log.d(TAG, "onBindViewHolder: view holder bind: " + repos.get(pos).getName());
        reposHolder.repoName.setText( repos.get(pos).getName() );
        if ( sharedPreferences.getBoolean(String.valueOf(repos.get(pos).getId()), false) ){
            reposHolder.repoStar.setImageResource(R.drawable.starfilled);
        }
        reposHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repoDetailInterface.showDetails(repos.get(pos));
            }
        });
    }


    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ReposHolder extends RecyclerView.ViewHolder{

        TextView repoName;
        ImageView repoStar;

        public ReposHolder(@NonNull View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repoListItemTv);
            repoStar = itemView.findViewById(R.id.repoStarImV);
        }
    }

    public void setRepoDetailInterfaceListener(RepoDetailInterface rep){
        this.repoDetailInterface = rep;
    }

}
