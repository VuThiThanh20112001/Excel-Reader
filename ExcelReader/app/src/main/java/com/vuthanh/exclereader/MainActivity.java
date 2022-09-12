package com.vuthanh.exclereader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final int RECORD_REQUEST_CODE = 1;
    private final int RECORD_REQUEST_FOLDER = 123;
    ArrayList<FileData> arrayList1 = new ArrayList<>();
    ArrayList<File> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission_file_location = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
//        int permission_coarse_location = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int permission_files_location = ContextCompat.checkSelfPermission(this,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE);
        if (permission_files_location != PackageManager.PERMISSION_GRANTED
                || permission_file_location != PackageManager.PERMISSION_GRANTED
                /*|| permission_coarse_location != PackageManager.PERMISSION_GRANTED*/) {
            makeRequest();
        }
//        showDialog(this);

        ImageButton settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,
                        Settings.class);
                startActivity(intent);
            }
        });


        initView();
        findViewById(R.id.all_documents).setOnClickListener(v -> {
            if (findViewById(R.id.all_documents).isSelected()) {
                findViewById(R.id.all_documents).setSelected(false);
            } else {
                findViewById(R.id.all_documents).setSelected(true);
                findViewById(R.id.recent).setSelected(false);
                findViewById(R.id.favourite).setSelected(false);
            }
        });
        findViewById(R.id.recent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findViewById(R.id.recent).isSelected()) {
                    findViewById(R.id.recent).setSelected(false);
                } else {
                    findViewById(R.id.recent).setSelected(true);
                    findViewById(R.id.favourite).setSelected(false);
                    findViewById(R.id.all_documents).setSelected(false);
                }
            }
        });
        findViewById(R.id.favourite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findViewById(R.id.favourite).isSelected()) {
                    findViewById(R.id.favourite).setSelected(false);
                } else {
                    findViewById(R.id.favourite).setSelected(true);
                    findViewById(R.id.recent).setSelected(false);
                    findViewById(R.id.all_documents).setSelected(false);
                }

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        RECORD_REQUEST_FOLDER);

            }
        });


    }


    private void makeRequest() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, RECORD_REQUEST_CODE);
    }


    public void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = loadFile(this, ".xlsm");
        arrayList = loadFile(this, ".xls");
        arrayList = loadFile(this, ".xlsx");
        FileAdapter fileAdapter = new FileAdapter(arrayList1, getApplicationContext());
        recyclerView.setAdapter(fileAdapter);
    }

    private void load(ArrayList<File> mList) {
        for(int i=0;i<mList.size();i++)
        {
            arrayList1.add(new FileData(mList.get(i).getPath()));
        }
    }

    public ArrayList<File> loadFile(Context context, String type) {
        ArrayList<File> list = new ArrayList<>();
        Uri table = MediaStore.Files.getContentUri("external");
        String selection = "_data LIKE '%." + type + "'";

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(table, null, selection, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (cursor == null || cursor.getCount() <= 0 || !cursor.moveToFirst()) {
                // this means error, or simply no results found
                return list;
            }
            int data = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);

            do {
                String path = cursor.getString(data);
                File file = new File(path);
                if (file.length() == 0) {
                    continue;
                }
                list.add(file);

            }
            while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        load(list);
        return list;
    }
//    public void showDialog(final Activity activity) {
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.dialog_allfile);
//        TextView allow = (TextView) dialog.findViewById(R.id.bt_allow);
//        TextView deny = (TextView) dialog.findViewById(R.id.bt_deny);
//        allow.setOnClickListener(view ->  {
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    if (Environment.isExternalStorageManager()) {
//                    } else { //request for the permission
//                        Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//                        Uri uri = Uri.fromParts("package", "com.vuthanh.exclereader", null);
//                        intent.setData(uri);
//                        startActivity(intent);
//                    }
//                }
//
//            dialog.dismiss();
//        });
//        deny.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//
//                dialog.dismiss();
//
//            }
//        });
//
//        dialog.show();
//    }


}