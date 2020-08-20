package com.ddvader44.officebuddy.pdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ddvader44.officebuddy.R;

import java.io.File;
import java.util.ArrayList;

public class pdfActivity extends AppCompatActivity {
    ListView pdfListView;

    public static ArrayList<File> fileList = new ArrayList<>();
    pdfAdapter obj_adapter;
    public static int REQUEST_PERMISSION = 1;
    boolean permission;
    File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        pdfListView = findViewById(R.id.listView_pdf);
        dir = new File(Environment.getExternalStorageDirectory().toString());

        missionPermission();
        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(pdfActivity.this, viewPdfActivity.class);
               intent.putExtra("position",position);
               startActivity(intent);

            }
        });
    }

    private void missionPermission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(pdfActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(pdfActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);

            }
        } else {
            permission = true;

            getfile(dir);

            obj_adapter = new pdfAdapter(getApplicationContext(), fileList);
            pdfListView.setAdapter(obj_adapter);

        }


    }

    public ArrayList<File> getfile(File dir) {

    File listFile[] = dir.listFiles();
    if(listFile!=null && listFile.length>0)
    {
        for (int i = 0; i < listFile.length; i++) {

            if (listFile[i].isDirectory()) {
                getfile(listFile[i]);

            } else {

                boolean booleanpdf = false;
                if (listFile[i].getName().endsWith(".pdf")) {

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

                obj_adapter = new pdfAdapter(getApplicationContext(), fileList);
                pdfListView.setAdapter(obj_adapter);

            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }
}
