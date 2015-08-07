package com.mystudy.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mystudy.ui.QuickIndexBar.LetterChangedListener;

/**
 * this is demo activity that show index bar's function
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {

	// the listview show person's data
	private ListView lv;
	// the textview show the character that user pressed
	private TextView tv;
	// the quickbar that user quick index bar
	private QuickIndexBar qib;
	// the datas of person's
	private List<Person> mPersons;
	// the adapter of person's listview
	private NameAdapater nameAdapater;
	// the handler
	private Handler mHandler = new Handler();

	/**
	 * this function that setcontentview and findview by id
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main);
		initView();
		initData();
		initEvent();
	}

	/**
	 * this initial event
	 */
	private void initEvent() {
		qib.setoListener(new LetterChangedListener() {

			@Override
			public void OnLetterChanged(String character) {
				ShowLetter(character);
				for (int i = 0; i < mPersons.size(); i++) {
					if (mPersons.get(i).getPinYin().equals(character)) {
						lv.setSelection(i);
					}
				}
			}
		});
	}

	protected void ShowLetter(String character) {
		tv.setText(character);
		tv.setVisibility(View.VISIBLE);
		mHandler.removeCallbacks(null);
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				tv.setVisibility(View.GONE);
			}
		}, 2000);
	}

	/**
	 * this function that  initial data
	 */
	private void initData() {
		mPersons = new ArrayList<Person>();
		for (int i = 0; i < Datas.NAMES.length; i++) {
			Person person = new Person();
			person.setName(Datas.NAMES[i]);
			person.setPinYin(PinYinUtils.getChinesePinYin(Datas.NAMES[i])
					.charAt(0) + "");
			mPersons.add(person);
		}
		nameAdapater = new NameAdapater(mPersons, MainActivity.this);
		lv.setAdapter(nameAdapater);
		Collections.sort(mPersons);
	}

	/**
	 * initial view
	 */
	private void initView() {
		// initial listview
		lv = (ListView) findViewById(R.id.lv);
		// initial textview
		tv = (TextView) findViewById(R.id.tv);
		// initial quickindex bar
		qib = (QuickIndexBar) findViewById(R.id.qib);
	}
}
