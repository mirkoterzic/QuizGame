package com.example.mirkoterzicseminarskirad;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LeaderboardActivity extends AppCompatActivity {
    ListView leaderboardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        leaderboardListView = findViewById(R.id.leaderboardListView);

        // Retrieve leaderboard data from the database
        MyDBHelper dbHelper = new MyDBHelper(this);
        Cursor cursor = dbHelper.getAllResults();

        // Create an adapter to display the data in the ListView
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{"name", "result"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );

        // Set the adapter for the ListView
        leaderboardListView.setAdapter(adapter);
    }
}
