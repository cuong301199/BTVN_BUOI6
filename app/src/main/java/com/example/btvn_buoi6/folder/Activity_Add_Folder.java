package com.example.btvn_buoi6.folder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btvn_buoi6.R;

import java.util.List;

public class Activity_Add_Folder extends AppCompatActivity {
    TextView tv_cancel, tv_save;
    EditText edt_name, edt_discription;
    List<FolderModel> mlistFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_folder);

        tv_cancel = findViewById(R.id.tv_cancel);
        tv_save = findViewById(R.id.tv_save);
        edt_name = findViewById(R.id.edt_name);
        edt_discription = findViewById(R.id.edt_discription);

        getWindow().setStatusBarColor(getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mlistFolder = (List<FolderModel>) getIntent().getSerializableExtra("list");


        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItemFoler();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void saveItemFoler() {
        String name = edt_name.getText().toString().trim();
        String discription = edt_discription.getText().toString().trim();

        if (checkFolderName(name)) {
            Toast.makeText(this, "Tên thư mục đã tồn tại", Toast.LENGTH_SHORT).show();
        } else if (name.equals("")) {
            Toast.makeText(this, "Tên thư mục không được để trống", Toast.LENGTH_SHORT).show();
        } else if (discription.equals("")) {
            Toast.makeText(this, "Mô tả thư mục không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Activity_Folder.class);
            intent.putExtra("itemFolder", new FolderModel(name, discription));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean checkFolderName(String name) {
        for (FolderModel folder : mlistFolder) {
            if (name.equals(folder.getName())) {
                return true;
            }
        }
        return false;
    }
}
