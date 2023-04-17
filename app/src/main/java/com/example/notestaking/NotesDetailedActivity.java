package com.example.notestaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesDetailedActivity extends AppCompatActivity {
    TextView notes_title, notes_desc, notes_date, notes_id;
    CardView notes_container_detailed;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detailed);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");


        notes_title = (TextView) findViewById(R.id.title_txt_detailed);
        notes_desc = (TextView) findViewById(R.id.desc_txt_detailed);
        notes_date = (TextView) findViewById(R.id.textView_date_detailed);
        notes_container_detailed = (CardView) findViewById(R.id.notes_container_detailed);


        notes_title.setText(title);
        notes_desc.setText(desc);
        notes_date.setText(date);

        int colorCode = getRandomColor();
        notes_container_detailed.setCardBackgroundColor(getResources().getColor(colorCode));

    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);
        colorCode.add(R.color.color6);
        colorCode.add(R.color.color7);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }
}