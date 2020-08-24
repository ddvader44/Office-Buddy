package com.ddvader44.officebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ddvader44.officebuddy.doc.docActivity;
import com.ddvader44.officebuddy.excel.excelActivity;
import com.ddvader44.officebuddy.pdf.pdfActivity;
import com.ddvader44.officebuddy.ppt.pptActivity;

public class MainActivity extends AppCompatActivity {
    CardView pdf,doc,excel,ppt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdf = findViewById(R.id.pdfView);
        doc = findViewById(R.id.docView);
        excel = findViewById(R.id.excelView);
        ppt= findViewById(R.id.pptView);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, pdfActivity.class));
            }
        });
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this , docActivity.class));
            }
        });
        excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, excelActivity.class));
            }
        });
        ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, pptActivity.class));
            }
        });
    }
}
