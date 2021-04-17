package com.example.techbullstest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.MyViewHolder> {
    Context context;
    private ArrayList<MovieBean> al_movies;

    public AdapterMovieList(Context context, ArrayList<MovieBean> al_movies) {
        this.context = context;
        this.al_movies = al_movies;
    }


    public AdapterMovieList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movies, parent, false);

        return new AdapterMovieList.MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull AdapterMovieList.MyViewHolder holder, final int position)
    {
        holder.txtv_name.setText("Name : "+al_movies.get(position).getTitle());
        holder.txtv_year.setText("Year : "+al_movies.get(position).getYear());

        Picasso.get()
                .load(al_movies.get(position).getPoster())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgv_poster);



    }

    public int getItemCount()
    {
        return al_movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtv_name, txtv_year;
        CircleImageView imgv_poster;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtv_name =  itemView.findViewById(R.id.txtv_name);
            txtv_year =  itemView.findViewById(R.id.txtv_year);
            imgv_poster =  itemView.findViewById(R.id.imgv_poster);

        }
    }
}
