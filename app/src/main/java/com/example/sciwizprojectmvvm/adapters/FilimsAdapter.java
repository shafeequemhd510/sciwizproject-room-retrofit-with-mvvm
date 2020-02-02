package com.example.sciwizprojectmvvm.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sciwizprojectmvvm.DiscriptionActivity;
import com.example.sciwizprojectmvvm.R;
import com.example.sciwizprojectmvvm.models.Result;

import java.util.List;

public class FilimsAdapter extends RecyclerView.Adapter<FilimsAdapter.FilimsHolder> {

    List<Result> results;

    public FilimsAdapter(List<Result> results)  {this.results=results;}{

    }

    @NonNull
    @Override
    public FilimsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.filimslayout,parent,false);
        return new FilimsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilimsHolder holder, final int position) {
        holder.tv.setText(results.get(position).getTitle());
        holder.tv2.setText("Director :"+results.get(position).getDirector());
        holder.tv3.setText("Producer :"+results.get(position).getProducer());
        holder.tv4.setText("ReleaseDates :"+results.get(position).getReleaseDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), DiscriptionActivity.class);
                intent.putExtra("title",results.get(position).getTitle());
                intent.putExtra("director",results.get(position).getDirector());
                intent.putExtra("producer",results.get(position).getProducer());
                intent.putExtra("ReleaseDates",results.get(position).getReleaseDate());
                intent.putExtra("Discription",results.get(position).getOpeningCrawl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class FilimsHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView tv2;
        TextView tv3;
        TextView tv4;


        public FilimsHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
            tv2=itemView.findViewById(R.id.textView2);
            tv3=itemView.findViewById(R.id.textView3);
            tv4=itemView.findViewById(R.id.textView4);

        }
    }

}