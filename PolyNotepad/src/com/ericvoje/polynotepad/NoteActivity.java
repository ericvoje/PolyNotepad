package com.ericvoje.polynotepad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends Activity {

	String filename;
	EditText noteText;
	Button saveButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		// Show the Up button in the action bar.
		setupActionBar();

		// Init UI components
		noteText = (EditText) findViewById(R.id.note_field);
		saveButton = (Button) findViewById(R.id.save_button);

		// Handle intent extras
		Intent intent = getIntent();
		boolean isNew = intent.getBooleanExtra("NEW_NOTE", true);
		if (isNew) {
			// New note
			filename = "newnote.txt";
		} else {
			// Load note
			filename = intent.getStringExtra("NOTE_FILE");
			loadNote(filename);
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Loads note from given filename
	public void loadNote(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			// Load file only if it exists
			StringBuffer outStringBuf = new StringBuffer();
			String inputLine = "";
			FileInputStream inputStream;

			try {
				inputStream = openFileInput(filename);

				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader inBuff = new BufferedReader(isr);
				while ((inputLine = inBuff.readLine()) != null) {
					outStringBuf.append(inputLine);
					outStringBuf.append("\n");
				}
				inputStream.read();
				inputStream.close();
				Toast toast = Toast.makeText(getApplicationContext(), filename
						+ " Loaded", Toast.LENGTH_LONG);
				toast.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Saves note to given file
	public void saveNote(View view) {
		String string = noteText.getText().toString();
		FileOutputStream outputStream;

		try {
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			outputStream.write(string.getBytes());
			outputStream.close();
			Toast toast = Toast.makeText(getApplicationContext(), filename
					+ " Saved", Toast.LENGTH_LONG);
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
