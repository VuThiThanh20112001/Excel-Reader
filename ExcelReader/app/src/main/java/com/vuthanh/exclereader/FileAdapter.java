package com.vuthanh.exclereader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.io.File;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private ArrayList<FileData> fileData;
    private Context context;

    public FileAdapter(ArrayList<FileData> fileData, Context context) {
        this.fileData = fileData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.file_excle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileData item= fileData.get(position);
        holder.name_files.setText(item.getPath());
        holder.capacity_files.setText(item.getPath()+"");
        holder.date_files.setText(item.getPath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(context, item.getNameFile(), Toast.LENGTH_SHORT).show();*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return fileData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name_files;
        public TextView capacity_files;
        public TextView date_files;
        public LinearLayout layout_files;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_files = (TextView) itemView.findViewById(R.id.name_files);
            capacity_files = (TextView) itemView.findViewById(R.id.capacity_files);
            date_files = (TextView) itemView.findViewById(R.id.date_files);
            layout_files = (LinearLayout) itemView.findViewById(R.id.layout_files);
        }


    }
}
