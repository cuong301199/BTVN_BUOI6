package com.example.btvn_buoi6.folder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btvn_buoi6.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity_Folder extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_ITEM = 123;
    public static final int REQUEST_CODE_EDIT_ITEM = 1234;

    RecyclerView rcv_folder;
    public List<FolderModel> folderList;
    Toolbar toolbar;
    TextView tv_add_item_toolbar;
    FolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        rcv_folder = findViewById(R.id.rcv_folder);
        toolbar = findViewById(R.id.toolbar);
        tv_add_item_toolbar = findViewById(R.id.tv_add_item_toolbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcv_folder.setLayoutManager(linearLayoutManager);

        adapter = new FolderAdapter(setDataFolderList(),this);

        rcv_folder.setAdapter(adapter);

        setToolbar();

        tv_add_item_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivityAddItem();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_ITEM && resultCode == RESULT_OK ){
            FolderModel folder = (FolderModel) data.getExtras().getSerializable("itemFolder");
            folderList.add(new FolderModel(folder.getName(),folder.getDescription()));
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Thêm thư mục thành công", Toast.LENGTH_SHORT).show();

        }else if(requestCode == REQUEST_CODE_EDIT_ITEM && resultCode == RESULT_OK ){
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String content = bundle.getString("discription");
            folderList.get(bundle.getInt("index")).setName(name);
            folderList.get(bundle.getInt("index")).setDescription(content);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Sửa thư mục thành công", Toast.LENGTH_SHORT).show();
        }



    }

    private void toActivityAddItem() {
        Intent intent = new Intent(Activity_Folder.this,Activity_Add_Folder.class);
        intent.putExtra("list", (Serializable) folderList);
        startActivityForResult(intent, REQUEST_CODE_ADD_ITEM);
    }

    private void setToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getWindow().setStatusBarColor(getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private List<FolderModel> setDataFolderList() {
        folderList = new ArrayList<>();
        folderList.add(new FolderModel("Tổng hợp tin tức thời sự","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
        folderList.add(new FolderModel("Cảm hứng sáng tạo","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
        folderList.add(new FolderModel("Do It Your Self","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
//        folderList.add(new FolderModel("Tổng hợp tin tức thời sự","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
//        folderList.add(new FolderModel("Tổng hợp tin tức thời sự","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
//        folderList.add(new FolderModel("Tổng hợp tin tức thời sự","Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả báo hiện nay"));
        return folderList;
    }
}