package geoquiz.android.bignerdranch.com.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tom on 8/9/2014.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private boolean mAnswerisTrue;
    private Button mShowAnswerButton;
    private TextView mShowAnswerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerisTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mShowAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        /* WIRE-up click actions for buttons */
        mShowAnswerButton = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Display the answer
                if (mAnswerisTrue)
                    mShowAnswerTextView.setText(R.string.true_button);
                else
                    mShowAnswerTextView.setText(R.string.false_button);

            }
        });
    }


}
