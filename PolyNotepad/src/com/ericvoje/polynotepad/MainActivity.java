package com.ericvoje.polynotepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button newButton, loadButton, settingsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		newButton = (Button) findViewById(R.id.new_button);
		loadButton = (Button) findViewById(R.id.load_button);
		settingsButton = (Button) findViewById(R.id.settings_button);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Starts NoteActivity with blank note
	public void newNote(View view) {
		Intent intent = new Intent(this, NoteActivity.class);
		intent.putExtra("NEW_NOTE", true);
		startActivity(intent);
	}

	// Starts NoteActivity with a saved note
	public void loadNote(View view) {
		Intent intent = new Intent(this, NoteActivity.class);
		intent.putExtra("NEW_NOTE", false);
		// Add file name
		intent.putExtra("NOTE_FILE", "loadnote.txt");
		startActivity(intent);
	}

}
