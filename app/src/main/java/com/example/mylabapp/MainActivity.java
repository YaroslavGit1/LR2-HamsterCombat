package com.example.mylabapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.Subscribe;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private long count = 0, add = 1;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        EventBus.getDefault().register(this);

    }

    public void onClick(View view) {
        count += add;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("DefaultLocale")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message event) {
        count = event.getCount();
        add = event.getAdd();
        String timesText = getTimesText(count);
        text.setText(String.format("У вас на счете %d %s", count, timesText));
    }

    public void OnClickShopActivity(View view){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            EventBus.getDefault().post(new Message(count, add));
        }, 200);
    }

    public void OnClickRateActivity(View view){
        Intent intent = new Intent(this, RateActivity.class);
        startActivity(intent);
    }

}