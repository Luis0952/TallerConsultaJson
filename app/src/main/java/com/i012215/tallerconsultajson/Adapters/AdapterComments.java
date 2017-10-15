package com.i012215.tallerconsultajson.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.i012215.tallerconsultajson.MainActivityPost;
import com.i012215.tallerconsultajson.Models.ModelComments;
import com.i012215.tallerconsultajson.Models.ModelPosts;
import com.i012215.tallerconsultajson.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.ViewHolder>{

List<ModelComments> commentsModelList=new ArrayList<>();
Context context;

    public AdapterComments(List<ModelComments> commentsModelList, Context context){
        this.commentsModelList=commentsModelList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item__comments, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textViewId.setText(Integer.toString(commentsModelList.get(position).getId_comments()));
        holder.textViewPostId.setText(Integer.toString(commentsModelList.get(position).getPostid()));
        holder.textViewBody.setText(commentsModelList.get(position).getBody());
        holder.textViewEmail.setText(commentsModelList.get(position).getEmail());
    }


    @Override
    public int getItemCount() {
        return commentsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewId;
        TextView textViewPostId;
        TextView textViewEmail;
        TextView textViewBody;

        public ViewHolder(View item) {
            super(item);

            item.setOnClickListener(this);
            textViewId = (TextView) item.findViewById(R.id.id_tv_Idcomments);
            textViewPostId = (TextView) item.findViewById(R.id.id_tv_Idpost);
            textViewBody = (TextView) item.findViewById(R.id.id_tv_body_comments);
            textViewEmail = (TextView) item.findViewById(R.id.id_tv_email_comments);
        }

        @Override
        public void onClick(View view) {
            Context contextItem = view.getContext();
            Intent intent = new Intent(context, MainActivityPost.class);
            // intent.putExtra("albumId", albumModelList.get(getLayoutPosition()).getId());
            contextItem.startActivity(intent);

        }
        }
    }