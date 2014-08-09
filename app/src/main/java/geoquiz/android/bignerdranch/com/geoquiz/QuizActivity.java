package geoquiz.android.bignerdranch.com.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {
    /* ############################################## */
    /*          Constants
    /* ############################################## */
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";


    /* ############################################## */
    /*          Member Objects
    /* ############################################## */
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas,true),
            new TrueFalse(R.string.question_asia,true),
            new TrueFalse(R.string.question_mideast,false),
    };


    /* ############################################## */
    /*          Begin Methods
    /* ############################################## */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called.");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        /* get question and display it */
        if (savedInstanceState!=null)
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX, 0);

        updateQuestion();

        /* wire-up click actions for buttons */
        mPreviousButton = (Button)findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mCurrentIndex>0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
            }
        });
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // start CheatActivity

            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        // add event listener to button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                checkAnswer(true);
            }

        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v) {
                checkAnswer(false);
            }
        });

        /* wire-up click action for the entire screen */
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState.");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateQuestion(){
        //Log.d(TAG, "Updating text for next question " + mCurrentIndex, new Exception());
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
    }

    private void checkAnswer(boolean userPressedTrue){
        int messageResID=0;

        if (userPressedTrue=mQuestionBank[mCurrentIndex].isTrueQuestion()){
            /* answered correctly */
            messageResID=R.string.correct_toast;
        }
        else {
            /* answered incorrectly */
            messageResID=R.string.incorrect_toast;
        }

        Toast.makeText(QuizActivity.this, messageResID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called.");
    }

    @Override
    public void onPause() {
        super.onStart();
        Log.d(TAG, "onPause() called.");
    }

    @Override
    public void onResume() {
        super.onStart();
        Log.d(TAG, "onResume() called.");
    }

    @Override
    public void onStop() {
        super.onStart();
        Log.d(TAG, "onStop() called.");
    }

    @Override
    public void onDestroy() {
        super.onStart();
        Log.d(TAG, "onDestroy() called.");
    }

}
