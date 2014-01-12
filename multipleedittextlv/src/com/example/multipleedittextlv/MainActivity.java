package com.example.multipleedittextlv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private HashMap<Integer, String> totalValueMap;
	private TextView ttlamountpayable_valueT = null;
	private int focus_change;
	private ArrayList<String> accountList;

	private LinearLayout lLayout;
	private View inflated_layout_view = null;
	private EditText enteramount_valTR = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		initialize();

	}

	private void initialize() {
		addValueInsideList();
		totalValueMap=new HashMap<Integer, String>();
		ttlamountpayable_valueT = (TextView) findViewById(R.id.ttlamountpayable_valueT);
		setTopupData();
	}
	
	public void addValueInsideList(){
		accountList=new ArrayList<String>();
		for(int i=0;i<10;i++){
			accountList.add("Account"+i);
		}
	}

	private void setTopupData() {

			for (int i = 0; i < accountList.size(); i++) {
				final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				inflated_layout_view = (LinearLayout) inflater.inflate(
						R.layout.mainllayout_row, null);

				enteramount_valTR = (EditText) inflated_layout_view
						.findViewById(R.id.enterVal);
				enteramount_valTR.setId(i);

				lLayout = (LinearLayout) findViewById(R.id.linearlayout_T);

				lLayout.addView(inflated_layout_view);

				enteramount_valTR
						.setOnFocusChangeListener(new View.OnFocusChangeListener() {

							@Override
							public void onFocusChange(View v, boolean hasFocus) {

								if (!hasFocus) {

									final int position = v.getId();
									focus_change = position;

								} else {

									final int position = v.getId();
									focus_change = position;

								}
							}
						});

				enteramount_valTR.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						totalValueMap.put(focus_change, s.toString().trim());
						System.out.println("==focus change" + focus_change);
						getTotal();

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s) {
					}
				});

			}

		}


	public void getTotal() {

		int totalAmount = 0;
		System.out.println("==Balance Map" + totalValueMap.size());

		Set<Integer> keySet = totalValueMap.keySet();
		for (Integer integer : keySet) {

			try {
				System.out.println("===total" + totalAmount);
				totalAmount = totalAmount
						+ (Integer.parseInt(totalValueMap.get(integer)));
				System.out.println("==Total Value in loop" + totalAmount);
			} catch (Exception exception) {
				Log.e("Number Format Exception", "Number Format Exception"
						+ exception);
			}

		}

		System.out.println("==Total Value of Array List" + totalAmount);
		ttlamountpayable_valueT.setText(Integer.toString(totalAmount));
	}

	public void onTopHeaderClick(View v) {

		finish();

	}

}
