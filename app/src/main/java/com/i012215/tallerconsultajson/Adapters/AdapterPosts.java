package com.i012215.tallerconsultajson.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i012215.tallerconsultajson.MainActivityComments;
import com.i012215.tallerconsultajson.Models.ModelPosts;
import com.i012215.tallerconsultajson.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolder>{

    List<ModelPosts> postModelList = new ArrayList<>();
    Context context;

    // Constructor de la clase
    public AdapterPosts(List<ModelPosts> postModelList, Context context) {
        this.postModelList = postModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item__post, parent, false);

        // Pasar la vista (item.xml) al ViewHolder
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textViewPostId.setText(Integer.toString(postModelList.get(position).getId_post()));
        holder.textViewUserId.setText(Integer.toString(postModelList.get(position).getUserid()));
        holder.textViewTitle.setText(postModelList.get(position).getTitle());
        holder.textViewBody.setText(postModelList.get(position).getBody());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewPostId;
        TextView textViewUserId;
        TextView textViewTitle;
        TextView textViewBody;

        public ViewHolder(View item){
            super(item);
            item.setOnClickListener(this);

            textViewPostId=(TextView) item.findViewById(R.id.id_tv_postID);
            textViewUserId=(TextView) item.findViewById(R.id.id_UserId);
            textViewTitle=(TextView) item.findViewById(R.id.id_tv_PostTitle);
            textViewBody=(TextView) item.findViewById(R.id.id_tv_PostBody);
        }

        @Override
        public void onClick(View view) {

            Context contextItem = view.getContext();
            Intent intent = new Intent(context, MainActivityComments.class);
            intent.putExtra("userId", postModelList.get(getLayoutPosition()).getId_post());
            contextItem.startActivity(intent);
        }
    }
    public int getItemCount() {
        return 0;
    }
}
