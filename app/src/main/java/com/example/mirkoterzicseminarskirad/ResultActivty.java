package com.example.mirkoterzicseminarskirad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivty extends AppCompatActivity {

    TextView result;
    TextView player_name;
    TextView time_elapsed;
    private int correct=0;
    private int time;
    Button homepage_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result =findViewById(R.id.result);
        time_elapsed = findViewById(R.id.time);
        player_name= findViewById(R.id.player_name);
        homepage_btn = findViewById(R.id.homepage_btn);
        QuizQuestions Questions= new QuizQuestions();

        correct=(int) getIntent().getSerializableExtra("correctAnswers");
        time=(int) getIntent().getSerializableExtra("time");
        result.setText(correct+"/"+Questions.getQuestionsLength());
        time_elapsed.setText("Time elapsed  " +time + " seconds");
        String playerName = getIntent().getStringExtra("playerName");
        player_name.setText(playerName);

        homepage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get player name and result
                String playerName = player_name.getText().toString();
                String resultText = result.getText().toString();
                int resultValue = Integer.parseInt(resultText.split("/")[0]);
                int timeValue=time;
                // Insert data into the database
                MyDBHelper dbHelper = new MyDBHelper(ResultActivty.this);
                dbHelper.addResult(playerName, resultValue,timeValue);

                Intent homepage= new Intent(ResultActivty.this,MainActivity.class);
                startActivity(homepage);
                finish();
            }

        });






    }
}