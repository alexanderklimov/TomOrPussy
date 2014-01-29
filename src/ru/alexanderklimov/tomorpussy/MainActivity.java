package ru.alexanderklimov.tomorpussy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button mTomcatButton;
	private Button mPussycatButton;
	private Button mNextButton;
	private TextView mQuestionTextView;
	private static final String KEY_INDEX = "index"; // ключ к индексу

	private Cat[] mCatBank = new Cat[] { new Cat(R.string.cat1, true),
			new Cat(R.string.cat2, true), new Cat(R.string.cat3, true),
			new Cat(R.string.cat4, false), new Cat(R.string.cat5, false),
			new Cat(R.string.cat6, false), new Cat(R.string.cat7, true),
			new Cat(R.string.cat8, false), new Cat(R.string.cat9, true) };

	private int mCurrentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTomcatButton = (Button) findViewById(R.id.button_tomcat);
		mPussycatButton = (Button) findViewById(R.id.button_pussycat);
		mNextButton = (Button) findViewById(R.id.button_next);
		mQuestionTextView = (TextView) findViewById(R.id.textView_question);

		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}

		// int question = mCatBank[mCurrentIndex].getQuestion();
		// mQuestionTextView.setText(question);
		updateQuestion();

		mTomcatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, R.string.toast_correct,
				// Toast.LENGTH_SHORT).show();
				checkAnswer(true);
			}
		});

		mPussycatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(MainActivity.this, R.string.toast_incorrect,
				// Toast.LENGTH_SHORT).show();
				checkAnswer(false);
			}
		});

		// Кнопка Далее
		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentIndex = (mCurrentIndex + 1) % mCatBank.length;

				updateQuestion();
				// int question = mCatBank[mCurrentIndex].getQuestion();
				// mQuestionTextView.setText(question);
				// setTitle("Кот или кошка (" + mCurrentIndex + ")");
			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	private void updateQuestion() {
		int question = mCatBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
		setTitle("Кот или кошка (" + mCurrentIndex + ")");
	}

	private void checkAnswer(boolean chooseTrue) {
		boolean answerIsTrue = mCatBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;
		if (chooseTrue == answerIsTrue) {
			messageResId = R.string.toast_correct;
		} else {
			messageResId = R.string.toast_incorrect;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
