package com.example.mirkoterzicseminarskirad;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button quizButton;

    private Button leaderboard_btn;
    private Button clr_leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       quizButton = findViewById(R.id.start_quiz_button);
       leaderboard_btn= findViewById(R.id.leaderboard_btn);
       clr_leaderboard=findViewById(R.id.clr_leaderboard);


        QuizQuestions Questions= new QuizQuestions();
         QuizQuestion[] questions=  Questions.getQuestions();



        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Quiz Game Activity
                startQuizActivityWithPlayerName(questions);

            }
        });

        leaderboard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leaderboardIntent = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(leaderboardIntent);

            }
        });
        clr_leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearLeaderBoard();
            }
        });


    }
    private void clearLeaderBoard() {
        // Create an instance of the database helper
        MyDBHelper dbHelper = new MyDBHelper(MainActivity.this);

        // Get a writable database instance
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Delete all rows from the "results" table
        db.delete("leaderboard", null, null);

        // Close the database
        db.close();

        // Show a toast message to indicate that the leaderboard has been cleared
        Toast.makeText(MainActivity.this, "Leaderboard cleared", Toast.LENGTH_SHORT).show();
    }

    private void startQuizActivityWithPlayerName(QuizQuestion[] questions) {
        // Create an AlertDialog to prompt the user to enter their name
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter Your Name");

        // Set up the input field
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Start Quiz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Retrieve the user's name from the input field
                String playerName = input.getText().toString();


                // Check if the playerName is empty or contains only spaces
                if (playerName.isEmpty() || playerName.matches("^\\s*$")) {
                    Toast.makeText(MainActivity.this, "Player name cannot be empty or contain only spaces", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if the playerName is unique by querying the leaderboard database
                MyDBHelper dbHelper = new MyDBHelper(MainActivity.this);
                Cursor cursor = dbHelper.getAllResults();

                // Check if the cursor contains the "name" column
                int nameColumnIndex = cursor.getColumnIndex("name");
                if (nameColumnIndex == -1) {
                    // Handle the case where the "name" column is not found
                    Toast.makeText(MainActivity.this, "Column 'name' not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                while (cursor.moveToNext()) {
                    String name = cursor.getString(nameColumnIndex);
                    if (name.equalsIgnoreCase(playerName)) {
                        Toast.makeText(MainActivity.this, "Player name must be unique", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                cursor.close();

                // Start the QuizActivity with the player's name as an extra in the intent
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("questions", questions);
                intent.putExtra("playerName", playerName);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Show the AlertDialog
        builder.show();
    }


}