package com.example.btvn_buoi6.folder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btvn_buoi6.R;

import java.util.List;

public class Activity_Edit_Folder extends AppCompatActivity {

    TextView tv_cancel, tv_save;
    EditText edt_name, edt_discription;
    List<FolderModel> mlistFolder;
    FolderModel folder;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_folder);

        tv_cancel = findViewById(R.id.tv_cancel);
        tv_save = findViewById(R.id.tv_save);
        edt_name = findViewById(R.id.edt_name);
        edt_discription = findViewById(R.id.edt_discription);

        getWindow().setStatusBarColor(getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Bundle bundle = getIntent().getExtras();
        mlistFolder = (List<FolderModel>) bundle.getSerializable("list_folder");
        folder = (FolderModel) bundle.getSerializable("item_folder");
        index = bundle.getInt("index");


        edt_name.setText(folder.getName());
        edt_discription.setText(folder.getDescription());


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editItemFoler();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
    private void editItemFoler() {
        String name = edt_name.getText().toString().trim();
        String discription = edt_discription.getText().toString().trim();

        if (name.equals("")) {
            Toast.makeText(this, "Tên thư mục không được để trống", Toast.LENGTH_SHORT).show();
        } else if (discription.equals("")) {
            Toast.makeText(this, "Mô tả thư mục không được để trống", Toast.LENGTH_SHORT).show();
        } else if (name.equals(folder.getName()) && discription.equals(folder.getDescription())) {
            Toast.makeText(this, "Bạn chưa thay đổi tên và mô tả thư mục", Toast.LENGTH_SHORT).show();
        }else if(checkFolderName(name)){
            Toast.makeText(this, "Tên thư mục đã tồn tại", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, Activity_Folder.class);
            Bundle bundle = new Bundle();

            bundle.putString("name",name);
            bundle.putString("discription",discription);
            bundle.putInt("index",index);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    private boolean checkFolderName(String name) {
        for (int i = 0 ; i < mlistFolder.size(); i++) {
            if(i == index){
                continue;
            }
            if (name.equals(mlistFolder.get(i).getName())) {
                return true;
            }
        }
        return false;
    }
}