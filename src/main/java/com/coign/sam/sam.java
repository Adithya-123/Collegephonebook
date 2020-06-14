package com.coign.sam;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class sam extends Activity {
	Dbhandler myDbHelper;
	SQLiteDatabase Mydatabase;
	ArrayList<String> aa,depts;
	
	Button btn,btn1,btn2,btn3,btn4,btn5,btn6;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getdepartmentdetails();
       
        final MediaPlayer mp=MediaPlayer.create(this, R.raw.ocean);
       final MediaPlayer mp1=MediaPlayer.create(this,R.raw.button);
       // mp.start();
        btn=(Button)findViewById(R.id.cse);
       
        btn1=(Button)findViewById(R.id.ece);
      
         btn2=(Button)findViewById(R.id.eee);
        
           btn3=(Button)findViewById(R.id.mech);
        
           btn4=(Button)findViewById(R.id.chem);
         
           btn5=(Button)findViewById(R.id.civil);
      
           
        for(int k=0;k<depts.size();k++)
        {
        	 if(depts.get(k).equals("CSE"))
        	 btn.setVisibility(Button.VISIBLE);
        	 else if(depts.get(k).equals("ECE"))
        		 btn1.setVisibility(Button.VISIBLE);
        	 else if(depts.get(k).equals("EEE"))
        		 btn2.setVisibility(Button.VISIBLE);
        	 else if(depts.get(k).equals("MECH"))
        		 btn3.setVisibility(Button.VISIBLE);
        	 else if(depts.get(k).equals("CHEM"))
        		 btn4.setVisibility(Button.VISIBLE);
        	 else if(depts.get(k).equals("CIVIL"))
        		 btn5.setVisibility(Button.VISIBLE);
        	System.out.println("VVVVVVVVVVVVVVVVVVVVVv   "+depts.get(k));
        }
         
     // final EditText et=(EditText)findViewById(R.id.editText1);
        
      
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mp1.start();				
				Intent it=new Intent(sam.this,dept.class);
				 final String cse=btn.getText().toString();
				it.putExtra("branch", cse);
	            startActivity(it);	            
				//CSE();
			}
		});
        
    btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp1.start();				
				Intent it=new Intent(sam.this,dept.class);
				 final String ece=btn1.getText().toString();
				it.putExtra("branch", ece);
	            startActivity(it);	            
				//CSE();
			}
		});
    btn2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mp1.start();				
			Intent it=new Intent(sam.this,dept.class);
			 final String eee=btn2.getText().toString();
			it.putExtra("branch", eee);
            startActivity(it);	            
			//CSE();
		}
	});
    btn3.setOnClickListener(new OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mp1.start();				
			Intent it=new Intent(sam.this,dept.class);
			 final String mech=btn3.getText().toString();
			it.putExtra("branch", mech);
            startActivity(it);	            
			//CSE();
		}
	});
   btn4.setOnClickListener(new OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mp1.start();				
			Intent it=new Intent(sam.this,dept.class);
			 final String chem=btn4.getText().toString();
			it.putExtra("branch", chem);
            startActivity(it);	            
			//CSE();
		}
	});
    btn5.setOnClickListener(new OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mp1.start();				
			Intent it=new Intent(sam.this,dept.class);
			 final String civil=btn5.getText().toString();
			it.putExtra("branch", civil);
         startActivity(it);	            
			//CSE();
		}
	});
     /*btn6.setOnClickListener(new OnClickListener() {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mp1.start();				
			Intent it=new Intent(sam.this,List.class);
			 final String mech=btn3.getText().toString();
			it.putExtra("branch", mech);
         startActivity(it);	            
			//CSE();
		}
	});*/
       }
    
	private void getdepartmentdetails() {
		// TODO Auto-generated method stub
		 this.myDbHelper=new Dbhandler(this);
			FetchingData();
			System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
		Mydatabase=myDbHelper.getReadableDatabase();
		depts=this.myDbHelper.getDeparments(Mydatabase);
		//System.out.println(depts.toString());
	}
	/*protected void CSE() {
		// TODO Auto-generated method stub
		 this.myDbHelper=new Dbhandler(this);
			FetchingData();
			System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
		Mydatabase=myDbHelper.getReadableDatabase();
			aa=this.myDbHelper.employee(Mydatabase,"CSE");
			System.out.println("$%^&*^^&&&&&&&&&&&&&         "+aa);
		
		
	}*/
	protected void FetchingData() {
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
		
	
}