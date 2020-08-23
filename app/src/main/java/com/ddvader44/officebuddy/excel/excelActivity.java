package com.ddvader44.officebuddy.excel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ddvader44.officebuddy.BuildConfig;
import com.ddvader44.officebuddy.R;
import com.ddvader44.officebuddy.doc.docActivity;
import com.ddvader44.officebuddy.doc.docAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class excelActivity extends AppCompatActivity {
    ListView excelListView;
    public static ArrayList<File> fileList = new ArrayList<>();
    excelAdapter obj_adapter;
    public static int REQUEST_PERMISSION = 1;
    boolean permission;
    File dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        excelListView = findViewById(R.id.listView_excel);
        dir = new File(Environment.getExternalStorageDirectory().toString());

        missionPermission();
        excelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                String fileUrl = name;
                File file = new File(fileUrl);
                String mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

                Uri theUri =  FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", file);

                Intent intent = new Intent();

                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                intent.setDataAndType(theUri, mime);

                startActivity(intent);

            }
        });
    }

    private void missionPermission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(excelActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(excelActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);

            }
        } else {
            permission = true;

            getfile(dir);

            obj_adapter = new excelAdapter(getApplicationContext(), fileList);
            excelListView.setAdapter(obj_adapter);

        }

    }
    public ArrayList<File> getfile(File dir) {

        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);

                } else {

                    boolean booleanpdf = false;
                    if (listFile[i].getName().endsWith(".xlsx") || listFile[i].getName().endsWith(".xls")) {

                        for (int j = 0; j < fileList.size(); j++) {
                            if (fileList.get(j).getName().equals(listFile[i].getName())) {
                                booleanpdf = true;
                            } else {

                            }
                        }

                        if (booleanpdf) {
                            booleanpdf = false;
                        } else {
                            fileList.add(listFile[i]);

                        }
                    }
                }
            }
        }
        return fileList;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                permission = true;
                getfile(dir);

                obj_adapter = new excelAdapter(getApplicationContext(), fileList);
                excelListView.setAdapter(obj_adapter);

            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }
}
