package com.i012215.tallerconsultajson.Adapters;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AlertController;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.i012215.tallerconsultajson.MainActivity;
import com.i012215.tallerconsultajson.MainActivityComments;
import com.i012215.tallerconsultajson.MainActivityPost;
import com.i012215.tallerconsultajson.Models.ModelUser;
import com.i012215.tallerconsultajson.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Eduardo on 12/10/2017.
 */

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {
    List<ModelUser> userModelList = new ArrayList<>();
    Context context;

    public AdapterUser(List<ModelUser> userModelList, Context context) {
        this.userModelList = userModelList;
        this.context = context;
    }

    @Override
    public AdapterUser.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item__user, parent, false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterUser.ViewHolder holder, int position) {
        holder.textViewUserId.setText(Integer.toString(userModelList.get(position).getId_user()));
        holder.textViewName.setText(userModelList.get(position).getName());
        holder.textViewUserName.setText(userModelList.get(position).getUsername());
        holder.textViewAddress.setText(userModelList.get(position).getAddress());
        holder.textViewCompany.setText(userModelList.get(position).getCompany());
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewUserId;
        TextView textViewName;
        TextView textViewUserName;
        TextView textViewAddress;
        TextView textViewCompany;

        public ViewHolder(View item)
        {
            super(item);

            item.setOnClickListener(this);

            textViewUserId = (TextView) item.findViewById(R.id.id_UserId);
            textViewName = (TextView) item.findViewById(R.id.id_tv_name);
            textViewUserName = (TextView) item.findViewById(R.id.id_tv_username);
            textViewAddress = (TextView) item.findViewById(R.id.tv_direccion);
            textViewCompany = (TextView) item.findViewById(R.id.tv_company);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, MainActivityPost.class);
            intent.putExtra("userId", userModelList.get(getLayoutPosition()).getId_user());
            context.startActivity(intent);

        }
    }
}
