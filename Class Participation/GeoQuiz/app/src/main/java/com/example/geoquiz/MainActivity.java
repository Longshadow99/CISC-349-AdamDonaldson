package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.d("In onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private static  final String TAG= "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        //Logger.d("onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Logger.d("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Logger.d("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Logger.d("onResume");
    }

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
    private int mCurrentIndex = 0;
    private static final int REQUEST_CODE_CHEAT = 0;

    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        String message = "Thank you for not cheating";
        if (requestCode == REQUEST_CODE_CHEAT){
            if (resultCode == Activity.RESULT_OK){
                message = "You cheated!";
                Logger.d(message);

            }else{
                Logger.d(message);

            }

        }
        cheatToast(message);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        //ID Finders
        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mCheatButton = findViewById(R.id.cheat_button);
        mQuestionTextView = findViewById(R.id.question_text_view);


        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        int question = mQuestionBank[mCurrentIndex].getmTextResID();
        mQuestionTextView.setText(question);
        //Click events
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                int question = mQuestionBank[mCurrentIndex].getmTextResID();
                mQuestionTextView.setText(question);
                updateQuestion();
            }
        });
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                Intent i = new Intent(MainActivity.this, CheatActivity.class);

                i.putExtra(EXTRA_ANSWER_IS_TRUE,  String.valueOf(mQuestionBank[mCurrentIndex].ismAnswerTrue()));
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }



        private void updateQuestion() {
            int question = mQuestionBank[mCurrentIndex].getmTextResID();
            mQuestionTextView.setText(question);
        }
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast bread = Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT);
        bread.show();
    }
    private void cheatToast(String message){
        Toast bread = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        bread.show();
    }

}