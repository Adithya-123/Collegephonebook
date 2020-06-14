package com.coign.sam;

import java.io.IOException;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class List extends ListActivity{
	Dbhandler myDbHelper;
	ListView lv;
	SQLiteDatabase Mydatabase;
	ArrayList<String> aa=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//getListAdapter();
		lv=getListView();
		Bundle b2=getIntent().getExtras();
		final String br=b2.getString("branch");
		final String type=b2.getString("type");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^      "+br);
		
		 this.myDbHelper=new Dbhandler(this);
			FetchingData();
			System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
		Mydatabase=myDbHelper.getReadableDatabase();
			aa=this.myDbHelper.employee(Mydatabase,br,type);
	
			System.out.println("$%^&*^^&&&&&&&&&&&&&         "+aa);
			if(aa.size()>0){
				System.out.println("$%^&*^^&&&&&&&&&&&&&         "+aa.size());
			setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,aa));
			}
			else{
				System.out.println("$%^&*^^&&&&&&&&&&&&&    vvvvvvvvvvvvvvvvv     ");
				Toast.makeText(getApplicationContext(), "NO RECORDS IN DATABASE", 100).show();
			}
			 lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						String select=getListAdapter().getItem(position).toString();
						Toast.makeText(getApplicationContext(), select, 100).show();
						Intent it=new Intent(List.this,details.class);
						it.putExtra("name", select);
						it.putExtra("branch", br);
						startActivity(it);
						
					}

					
					
				
					});


	}
	private void FetchingData() {
	
		// TODO Auto-generated method stub
		 try {  
			 
	        	myDbHelper.onCreateDataBase();
	        	       	
	        	
	 	} catch (IOException ioe) {
	 
	 		throw new Error("Unable to create database");
	 
	 	} 
	 	try {
	 
	 		myDbHelper.openDataBase();
	 		Mydatabase = myDbHelper.getWritableDatabase();
			System.out.println("executed");
	 	
	 	}catch(SQLException sqle){
	 
	 		throw sqle;
	 
	 	}
		// TODO Auto-generated method stub
			
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("add employee");
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String m1=(String) item.getTitle();
		if(m1.equalsIgnoreCase("add employee")){
			Intent add=new Intent(List.this,AddEmployee.class);
			startActivity(add);
		}
		return super.onOptionsItemSelected(item);
	}
}
