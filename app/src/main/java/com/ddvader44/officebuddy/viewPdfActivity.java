package com.ddvader44.officebuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class viewPdfActivity extends AppCompatActivity {
    PDFView pdfView;
    int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        position = getIntent().getIntExtra("position",-1);

        displayPDF();

    }

    private void displayPDF() {
        pdfView.fromFile(pdfActivity.fileList.get(position))
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}
