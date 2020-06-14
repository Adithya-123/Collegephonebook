package com.coign.sam;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class details extends Activity {
String name1,s,br1,qual,exp,phnum,sub,desig,email,deptid;
Dbhandler myDbHelper;
SQLiteDatabase Mydatabase;
TextView tv,tv1,tv2,tv3,tv4,tv5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		TextView nam=(TextView)findViewById(R.id.name);
		 tv=(TextView)findViewById(R.id.num);
		 tv1=(TextView)findViewById(R.id.add);
		 tv2=(TextView)findViewById(R.id.textView6);
		 tv3=(TextView)findViewById(R.id.textView8);
		tv4=(TextView)findViewById(R.id.email);
		tv5=(TextView)findViewById(R.id.subject);
		 Bundle b=getIntent().getExtras();
		  name1=b.getString("name");
		  nam.setText(name1);
		 br1=b.getString("branch");
		  System.out.println("%%%%%%%%%%%%%%%%% "+name1);
		  openAndQueryDatabase();
	}
	private void openAndQueryDatabase() {
		// TODO Auto-generated method stub
		try{
			
	 		this.myDbHelper=new Dbhandler(this);
	 		FetchingData();
	 		Mydatabase=myDbHelper.getReadableDatabase();
			//String s=myDbHelper.values();
	 		
			Cursor c=Mydatabase.rawQuery("SELECT * FROM EmployeeDetails inner join Dept on EmployeeDetails.DeptID=Dept.DeptID WHERE EmployeeDetails.EmpName='"+name1+"' and Dept.DeptName='"+br1+"' " , null);
			c.moveToFirst();
			if(c!=null){
				int c1=c.getColumnIndex("EmpQualification");
				int c2=c.getColumnIndex("EmpDesignation");
				int c3=c.getColumnIndex("EmpExp");
				int c4=c.getColumnIndex("EmpPhnum");
				int c5=c.getColumnIndex("Empemailid");
				int c6=c.getColumnIndex("subject");
				int c8=c.getColumnIndex("EmpType");
				int c7=c.getColumnIndex("DeptID");
				do{
					
					 qual=c.getString(c1);
				     tv.setText(qual);
				      desig=c.getString(c2);
				     tv1.setText(desig);
				     exp=c.getString(c3);
				     tv2.setText(exp);
				     phnum=c.getString(c4);
				     tv3.setText(phnum);
				     email=c.getString(c5);
				     tv4.setText(email);
				     sub=c.getString(c6);
				     tv5.setText(sub);
				     deptid=c.getString(c7);
					System.out.println("$$$$$$$$$$$$$$  "+s);
					String type=c.getString(c8);
					//Toast.makeText(getApplicationContext(), "retrived value is "+s, 10).show();
				}while(c.moveToNext());
				//Toast.makeText(getApplicationContext(), "retrived value is "+s, 10).show();
			}
		}catch(SQLiteException se){
			
		}
		
		
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
		menu.add("call");
		menu.add("message");
		menu.add("Edit");
		menu.add("Delete");
		menu.add("Mail");

		return super.onCreateOptionsMenu(menu);
	}		
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String m1=(String) item.getTitle();
		if(m1.equalsIgnoreCase("call")){
			Intent it=new Intent(details.this,call.class);
			it.putExtra("num", phnum);
			startActivity(it);
			
		}
		if(m1.equalsIgnoreCase("message")){
			Intent it=new Intent(details.this,MessageSending.class);
			it.putExtra("num", phnum);
			startActivity(it);
			
		}
		if(m1.equalsIgnoreCase("Mail")){
			Intent mailIntent = new Intent(android.content.Intent.ACTION_SEND);
			mailIntent.setType("plain/text");
			mailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });
			mailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Invitation");
			mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"");
			startActivity(mailIntent);
			
		}
		if(m1.equalsIgnoreCase("Edit")){
			Intent it=new Intent(details.this,AddEmployee.class);
			it.putExtra("uname", name1);
			it.putExtra("branch", br1);	
			it.putExtra("deptid", deptid);	
			startActivity(it);
		}
		if(m1.equalsIgnoreCase("Delete")){
			alertbox("Confirmation !","Are You Sure Do You Want TO Upload ?");
		}
		return super.onOptionsItemSelected(item);
	}
	private void alertbox(String string, String string2) {
		// TODO Auto-generated method stub
		 new AlertDialog.Builder(this)
	      .setMessage("are you sure! you want to delete")
	      .setTitle("Delete")
	      .setCancelable(true)
	      .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int id) {
  	Delete();
  	Intent it1=new Intent(details.this,sam.class);
  	
		startActivity(it1);
     }
 })
 .setNegativeButton("No", new DialogInterface.OnClickListener() {
     public void onClick(DialogInterface dialog, int id) {
          dialog.cancel();
     }
 })
	      .show();
	   }
	protected void Delete() {
		// TODO Auto-generated method stub
		this.myDbHelper=new Dbhandler(this);
 		FetchingData();
 		Mydatabase=myDbHelper.getReadableDatabase();
 		Mydatabase.execSQL("DELETE  FROM EmployeeDetails WHERE EmpName='"+name1+"' and EmpPhnum='"+phnum+"'");
 		
 		Toast.makeText(getApplicationContext(), "Record Deleted sucessfully", 80).show();
 		
		
	}
		
	
		
	
		
	

}
