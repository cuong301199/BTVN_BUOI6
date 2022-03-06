package com.example.btvn_buoi6.folder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_buoi6.R;

import java.io.Serializable;
import java.util.List;

public class FolderAdapter extends RecyclerView.Adapter {

    List<FolderModel> folderModelList;
    private Context context;


    public FolderAdapter(List<FolderModel> folderModelList, Context mcontext) {
        this.folderModelList = folderModelList;
        this.context = mcontext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_folder,parent,false);
        return new FolderViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,  int position) {
        int index = position;
        FolderModel folder = folderModelList.get(position);
        ((FolderViewholder) holder).tv_name.setText(folder.getName());
        ((FolderViewholder) holder).tv_discription.setText(folder.getDescription());


        ((FolderViewholder) holder).layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Activity_Edit_Folder.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item_folder",folder);
                bundle.putSerializable("list_folder", (Serializable) folderModelList);
                bundle.putInt("index", index );
                intent.putExtras(bundle);
                ((Activity)context).startActivityForResult(intent,Activity_Folder.REQUEST_CODE_EDIT_ITEM);

            }
        });

    }

    @Override
    public int getItemCount() {
        return folderModelList.size();
    }

    public class FolderViewholder extends RecyclerView.ViewHolder{
        private TextView tv_name, tv_discription;
        private LinearLayout layout_item;
        public FolderViewholder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_discription = itemView.findViewById(R.id.tv_discription);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
