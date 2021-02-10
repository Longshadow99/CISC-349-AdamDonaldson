package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

public class CheatActivity extends AppCompatActivity {
    private Button mShowAnswer;
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent i = getIntent();
        mAnswerIsTrue = Boolean.parseBoolean(i.getStringExtra(MainActivity.EXTRA_ANSWER_IS_TRUE));
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer_button);

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "Hello from cheat activity");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}