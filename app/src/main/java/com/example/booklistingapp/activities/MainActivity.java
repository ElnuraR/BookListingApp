package com.example.booklistingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.booklistingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchEdt = findViewById(R.id.search_text);
        Button searchBtn = findViewById(R.id.search_button);

        searchBtn.setOnClickListener(v -> {
            readNetworkState(this);

            if (searchEdt.getText().toString().isEmpty()) {
                searchEdt.setError("Please enter the searching information");
                return;
            }

            Intent intent = new Intent(this, BookActivity.class);
            intent.putExtra("Search", searchEdt.getText().toString());
            startActivity(intent);

        });
    }

    private boolean readNetworkState(Context context) {
        boolean is_connected;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected = info != null && info.isConnectedOrConnecting();

        return is_connected;
    }
}