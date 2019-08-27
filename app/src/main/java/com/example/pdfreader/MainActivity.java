package com.example.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private int pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        pageNumber = extras.getInt("MyPageNumber");

        openBook("pairofwords.pdf",pageNumber);

    }
    private void openBook(String bookName, int pageNumber)
    {
        PDFView pdfView=findViewById(R.id.pdfView);

        pdfView.fromAsset(bookName)
                //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(pageNumber)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        getSupportActionBar().setTitle("Total Page: "+pageCount+"  Page: "+(page+1));

                        storingDataInSharedPreference((page));
                    }
                })
                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(null)
               // .scrollHandle(new DefaultScrollHandle(this))
                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacin g between pages in dp. To define spacing color, set view background
                .spacing(0)
                .load();
    }
    private void storingDataInSharedPreference(int currentPage)
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("key_page", currentPage);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }
}
