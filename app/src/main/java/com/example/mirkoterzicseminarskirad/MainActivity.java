package com.example.mirkoterzicseminarskirad;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button quizButton;

    private Button leaderboard_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       quizButton = findViewById(R.id.start_quiz_button);
       leaderboard_btn= findViewById(R.id.leaderboard_btn);


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