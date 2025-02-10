package com.example.mylabapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private long count = 0;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
    }

    public void onClick(View view) {
        count++;
        String timesText = getTimesText(count);
        text.setText(String.format("У вас на счете %d %s", count, timesText));

        if (count == 10) {
            Toast.makeText(this, "ВЫ ХОМЯК УРОВНЯ 1", Toast.LENGTH_SHORT).show();
        }
        if (count == 20) {
            Toast.makeText(this, "ВЫ ХОМЯК УРОВНЯ 2", Toast.LENGTH_SHORT).show();
        }
        if (count == 30) {
            Toast.makeText(this, "ВЫ ХОМЯК УРОВНЯ 3", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTimesText(long count) {
        if (count % 10 == 1 && count % 100 != 11) {
            return "коин";
        } else if (count % 10 >= 2 && count % 10 <= 4 && !(count % 100 >= 12 && count % 100 <= 14)) {
            return "коина";
        } else {
            return "коинов";
        }
    }
}