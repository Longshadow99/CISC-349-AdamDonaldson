package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mTotal = 0;
    private int mCurrent = 0;
    private String mOperation = "empty";
    private Button mZeroButton;
    private Button mOneButton;
    private Button mTwoButton;
    private Button mThreeButton;
    private Button mFourButton;
    private Button mFiveButton;
    private Button mSixButton;
    private Button mSevenButton;
    private Button mEightButton;
    private Button mNineButton;
    private Button mPlusButton;
    private Button mMinusButton;
    private Button mEqualButton;
    private TextView mDisplayView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mZeroButton = findViewById(R.id.zero_button);
        mOneButton = findViewById(R.id.one_button);
        mTwoButton = findViewById(R.id.two_button);
        mThreeButton = findViewById(R.id.three_button);
        mFourButton = findViewById(R.id.four_button);
        mFiveButton = findViewById(R.id.five_button);
        mSixButton = findViewById(R.id.six_button);
        mSevenButton = findViewById(R.id.seven_button);
        mEightButton = findViewById(R.id.eight_button);
        mNineButton = findViewById(R.id.nine_button);
        mPlusButton = findViewById(R.id.plus_button);
        mMinusButton = findViewById(R.id.minus_button);
        mEqualButton = findViewById(R.id.equal_button);
        mDisplayView = findViewById(R.id.display_view);

        mZeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(0);
            }
        });
        mOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(1);
            }
        });
        mTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(2);
            }
        });
        mThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(3);
            }
        });
        mFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(4);
            }
        });
        mFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(5);
            }
        });
        mSixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(6);
            }
        });
        mSevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(7);
            }
        });
        mEightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(8);
            }
        });
        mNineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCurrent(9);
            }
        });
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveOperation("plus");
            }
        });
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solveOperation("minus");
            }
        });
        mEqualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOperation == "plus"){
                    mTotal = mTotal + mCurrent;
                    mCurrent = 0;
                    mOperation = "empty";
                }else{
                    mTotal = mTotal - mCurrent;
                    mCurrent = 0;
                    mOperation = "empty";
                }
                mDisplayView.setText(String.valueOf(mTotal));
            }
        });
        mEqualButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resetCalc();
                return true;
            }
        });
    }

    private void resetCalc(){
        mTotal = 0;
        mCurrent = 0;
        mOperation = "empty";
        Toast toast = Toast.makeText(this, "Reset",1);
        toast.show();
        mDisplayView.setText(String.valueOf(mTotal));
    }
    private void updateCurrent(int num){
        mCurrent = mCurrent * 10 + num;
        mDisplayView.setText(String.valueOf(mCurrent));
    }
    private void solveOperation(String op){
        if (mOperation == "empty"){
            mTotal = mCurrent;
            mCurrent = 0;
            mOperation = op;
        }else{
            if(mOperation == "plus"){
                mTotal = mTotal + mCurrent;
                mCurrent = 0;
                mOperation = op;
            }else{
                mTotal = mTotal - mCurrent;
                mCurrent = 0;
                mOperation = op;
            }
        }
        mDisplayView.setText(String.valueOf(mTotal));
    }

}