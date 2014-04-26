package com.ericvoje.polynotepad;

import java.io.File;

import android.app.Activity;
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
		}
		loadNote(filename);
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
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
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
		}

	}

	// Saves note to given file
	public void saveNote(View view) {

		Toast toast = Toast.makeText(getApplicationContext(), filename
				+ " Saved", Toast.LENGTH_LONG);
		toast.show();
	}

}
