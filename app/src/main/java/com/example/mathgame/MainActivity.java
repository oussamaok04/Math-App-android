package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btngo, button0, button1, button2, button3, replay;
    ConstraintLayout challenge;
    Random rand = new Random();
    TextView score, counter, operation, result;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int corr_answer_loc;
    int score_save = 0;
    int question_no = 0;

//Note : when i press the correct answer is still doesn't show correct!
//Note : the result text doesn't show at all
//Note : counter and score

    public void answer_result(View v){

        try {

            if(v.getTag().toString().equals(Integer.toString(corr_answer_loc))){
                result.setVisibility(View.VISIBLE);
                result.setText("Correct!");
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                score_save++;
            } else {
                result.setVisibility(View.VISIBLE);
                result.setText("Wrong!");
                Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            }

            gameGenerator(v);
            question_no++;

            score.setText(Integer.toString(score_save) + "/" + Integer.toString(question_no));


        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void gameGenerator(View v){

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int incorrectanswer;
        corr_answer_loc = rand.nextInt(4);

        answers.clear();
        operation.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0; i < 4; i++) {

            if ( i == corr_answer_loc){
                answers.add(a + b);
            }else{
                incorrectanswer = rand.nextInt(41);
                while (incorrectanswer == a + b){
                    incorrectanswer = rand.nextInt(41);
                }
                answers.add(incorrectanswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        replay.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngo = (Button) findViewById(R.id.btngo);
        challenge = (ConstraintLayout) findViewById(R.id.challenge);
        score = (TextView) findViewById(R.id.score);
        counter = (TextView) findViewById(R.id.timer);
        operation = (TextView) findViewById(R.id.operation);
        result = (TextView) findViewById(R.id.result);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        replay = (Button) findViewById(R.id.replay);


        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btngo.setVisibility(View.INVISIBLE);
                challenge.setVisibility(View.VISIBLE);

                try {
                    gameGenerator(v);

                    new CountDownTimer(30100, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int seconds = (int) millisUntilFinished / 1000;
                            String Sseconds = Integer.toString(seconds);
                            if (seconds <= 9){
                                Sseconds = "0" + seconds;
                            }
                            counter.setText(Sseconds + "s");
                        }

                        @Override
                        public void onFinish() {
                            counter.setText("00s");
                            result.setText("Your score is : " + score.getText().toString());
                            replay.setVisibility(View.VISIBLE);
                        }
                    }.start();

                } catch ( Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    score_save = 0;
                    question_no = 0;
                    result.setText("");
                    counter.setText("30s");
                    score.setText("0/0");
                    replay.setVisibility(View.INVISIBLE);

                    gameGenerator(v);

                    new CountDownTimer(30100, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int seconds = (int) millisUntilFinished / 1000;
                            String Sseconds = Integer.toString(seconds);
                            if (seconds <= 9){
                                Sseconds = "0" + seconds;
                            }
                            counter.setText(Sseconds + "s");
                        }

                        @Override
                        public void onFinish() {
                            counter.setText("00s");
                            result.setText("Your score is : " + score.getText().toString());
                            replay.setVisibility(View.VISIBLE);
                        }
                    }.start();

                } catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}