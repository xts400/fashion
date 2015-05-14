package com.fashion.zillah;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zillah.database.ZillahDatabaseAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ClientCountActivity extends Activity {
	private TextView date_textbox;
	private TextView client_count_textbox;
	private ZillahDatabaseAdapter database_adapter;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_count);
		date_textbox = (TextView) findViewById(R.id.txt_client_count_date);
		client_count_textbox = (TextView) findViewById(R.id.txt_client_count);
		database_adapter = new ZillahDatabaseAdapter(this);

		// Get Current date and time
		SimpleDateFormat date_format = new SimpleDateFormat(
				"dd-MM-yyyy hh:mm:ss");
		Date date = new Date();
		String date_string = date_format.format(date);
		date_textbox.setText(date_string);

		// Get client count
		Cursor client_list = database_adapter.listClients();

		if (client_list.getCount() > 0) {
			client_count_textbox.setText(String.valueOf(client_list.getCount()));
		} else {
			client_count_textbox.setText("Client count is null");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_count, menu);
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
}
