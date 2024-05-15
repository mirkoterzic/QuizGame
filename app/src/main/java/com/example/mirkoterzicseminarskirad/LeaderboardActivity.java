package com.example.mirkoterzicseminarskirad;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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
                R.layout.custom_leaderboard_item,
                cursor,
                new String[]{"name", "result","time"},
                new int[]{R.id.textViewName,R.id.textViewResult,R.id.textViewTime},
                0
        );
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                String columnName = cursor.getColumnName(columnIndex);
                if (columnName.equals("result")) {
                    int resultValue = cursor.getInt(columnIndex);
                    ((TextView) view).setText("Result: " + resultValue);
                    return true;
                } else if (columnName.equals("time")) {
                    int timeValue = cursor.getInt(columnIndex);
                    ((TextView) view).setText("Time elapsed: " + timeValue + " seconds");
                    return true;
                }
                return false;
            }
        });

        // Set the adapter for the ListView
        leaderboardListView.setAdapter(adapter);
    }
}
