package com.unagit.assettestapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements MyListAdapter.ListClickListener {

    final static String IMG_PATH = "pics";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.web_view);
        loadHtmlAssetInto(webView);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_list);
        listAssetNamesUsing(recyclerView);


    }

    private void listAssetNamesUsing(RecyclerView recyclerView) {
        String[] list = new String[0];
        try {
            list = getAssets().list(IMG_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null && list.length != 0) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(new MyListAdapter(list, this));
        } else {
            Toast.makeText(this, "No assets found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadHtmlAssetInto(WebView webView) {
        webView.loadUrl("file:///android_asset/portfolio/index.html");
    }

    @Override
    public void onClick(String fileName) {
        if (fileName.toLowerCase().endsWith(".jpg")) {
            String filePath = IMG_PATH + "/" + fileName;
            ImageView imgView = findViewById(R.id.img_view);
            try {
                InputStream stream = getAssets().open(filePath);
                Bitmap bm = BitmapFactory.decodeStream(stream);
                imgView.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
