package com.vuthanh.exclereader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class Language extends AppCompatActivity {
    ListView lvLanguage1;
    ArrayList<LanguageData> arrayLanguage;
    LanguageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ImageButton back = (ImageButton) findViewById(R.id.com_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        compare();

    }

    private void compare() {
        lvLanguage1 = (ListView) findViewById(R.id.listview_language);
        arrayLanguage = new ArrayList<>();
        arrayLanguage.add(new LanguageData(R.drawable.ic_german,"German"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_chinese,"Chinese"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_english,"English"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_bulgarian,"Bulgarian"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_french,"French"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_greek,"Greek"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_indonesian,"Indonesian"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_italia,"Italian"));
        arrayLanguage.add(new LanguageData(R.drawable.ic_vietnamese,"Vietnamese"));
        adapter = new LanguageAdapter(this,R.layout.language,arrayLanguage);
        lvLanguage1.setAdapter(adapter);
    }
}