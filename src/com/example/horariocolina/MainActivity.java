package com.example.horariocolina;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemSelectedListener {
	  private String array_spinner[];
	  private ArrayAdapter<String> adapter;
	  private TextView tets;
	  private Spinner spi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		array_spinner = new String[6];
		array_spinner[0] = "4";
		array_spinner[1] ="8";
		array_spinner[2] = "12";
		array_spinner[3] = "16";
		array_spinner[4] = "20";
		array_spinner[5] = "24";
		spi =  (Spinner) findViewById(R.id.spinner1);

		adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, array_spinner);
		spi.setAdapter(adapter);
		spi.setOnItemSelectedListener(this);
		
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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





	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		DatabaseHelper myDbHelper;
    	myDbHelper = new DatabaseHelper(this);
 
    	try {
			myDbHelper.createDataBase();
			myDbHelper.getDatabase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
    	try {
    		myDbHelper.getDatabase();
    	} catch (SQLException sqle){
    		throw sqle;
    	}
		
		
		 Cursor cursor = myDbHelper.getDatabase().rawQuery("select \"_id\",\""+spi.getSelectedItem().toString()+"\" from \"HORARIOS\"",null);
		 String[] from ={"_id",spi.getSelectedItem().toString()};
		 int[] to ={R.id.id,R.id.textHora};
			SimpleCursorAdapter cs = new SimpleCursorAdapter(getBaseContext(), R.layout.activity_listamodel, cursor, from, to,0);
	        ListView lt1 = (ListView) findViewById(R.id.listH);
	        lt1.setAdapter(cs);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
