package com.ddvader44.officebuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                startActivity(new Intent(MainActivity.this,pdfActivity.class));
            }
        });
    }
}
