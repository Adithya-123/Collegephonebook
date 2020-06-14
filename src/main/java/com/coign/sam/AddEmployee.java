package com.coign.sam;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEmployee extends Activity {
	Dbhandler myDbHelper;
	SQLiteDatabase Mydatabase;
	String uname, branch, id1, type2/* ,ty1 */;
	int deptid1;
	EditText name, email1, phnum, sub, exp, qua, desig/* ,type */;
	Bundle b2;
	ArrayList<String> aa, depts;
	ArrayList<String> al = new ArrayList<String>();
	Spinner sp, sp1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		System.out.println("^^^^^^^^^^^^^^^^ " + depts);

		getdepartmentdetails();
		System.out.println("^^^^^^^^^^^^^^^^ " + depts);
		sp = (Spinner) findViewById(R.id.dept);
		name = (EditText) findViewById(R.id.txtName);
		qua = (EditText) findViewById(R.id.qual1);
		desig = (EditText) findViewById(R.id.des);
		exp = (EditText) findViewById(R.id.exp);
		phnum = (EditText) findViewById(R.id.pnum);
		email1 = (EditText) findViewById(R.id.email);
		sub = (EditText) findViewById(R.id.subj);

		sp1 = (Spinner) findViewById(R.id.spinner1);
		al.add("Teach");
		al.add("Non-Teach");
		ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, al);
		sp1.setAdapter(adp);

		// depts=new ArrayList<String>();
		sp.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, depts));
		Button buttonadd = (Button) findViewById(R.id.btnAdd);
		// to get employee details to edit
		b2 = getIntent().getExtras();
		if (b2 != null) {
			uname = b2.getString("uname");
			branch = b2.getString("branch");
			if (uname != "") {

				getempdetails(uname, branch);
				name.setText(uname);
				buttonadd.setText("Update Details");

			}
		}
		// end to get employee details to edit

		buttonadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// validate();
				insert();
				Intent i2 = new Intent(AddEmployee.this, sam.class);
				startActivity(i2);
			}

			protected void validate() {
				// TODO Auto-generated method stub
				String s1 = name.getText().toString();
				FetchingData();
				System.out
						.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
				Mydatabase = myDbHelper.getReadableDatabase();

			}
		});
	}

	private void getdepartmentdetails() {
		// TODO Auto-generated method stub
		this.myDbHelper = new Dbhandler(this);
		FetchingData();
		System.out
				.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
		Mydatabase = myDbHelper.getReadableDatabase();
		depts = this.myDbHelper.getDeparments(Mydatabase);

	}

	String name1, s, br1, qual, exp1, phnum1, sub1, desig1, email;

	private void getempdetails(String uname2, String branch2) {

		// TODO Auto-generated method stub
		try {

			this.myDbHelper = new Dbhandler(this);
			FetchingData();
			Mydatabase = myDbHelper.getReadableDatabase();
			// String s=myDbHelper.values();

			Cursor c = Mydatabase
					.rawQuery(
							"SELECT * FROM EmployeeDetails inner join Dept on EmployeeDetails.DeptID=Dept.DeptID WHERE EmployeeDetails.EmpName='"
									+ uname2
									+ "' and Dept.DeptName='"
									+ branch2 + "' ", null);
			c.moveToFirst();
			if (c != null) {
				int c1 = c.getColumnIndex("EmpQualification");
				int c2 = c.getColumnIndex("EmpDesignation");
				int c3 = c.getColumnIndex("EmpExp");
				int c4 = c.getColumnIndex("EmpPhnum");
				int c5 = c.getColumnIndex("Empemailid");
				int c6 = c.getColumnIndex("subject");
				int c8 = c.getColumnIndex("EmpType");
				do {

					qual = c.getString(c1);
					qua.setText(qual);
					desig1 = c.getString(c2);
					desig.setText(desig1);
					exp1 = c.getString(c3);
					exp.setText(exp1);
					phnum1 = c.getString(c4);
					phnum.setText(phnum1);
					email = c.getString(c5);
					email1.setText(email);
					sub1 = c.getString(c6);
					sub.setText(sub1);
					type2 = c.getString(c8);
					sp1.setTag(type2);
					System.out.println("$$$$$$$$$$$$$$  " + s);

					// Toast.makeText(getApplicationContext(),
					// "retrived value is "+s, 10).show();
				} while (c.moveToNext());
				Toast.makeText(getApplicationContext(),
						"retrived value is " + s, 10).show();
			}
		} catch (SQLiteException se) {

		}

	}

	protected void insert() {
		// TODO Auto-generated method stub
		if (b2 != null) {
			uname = b2.getString("uname");
			branch = b2.getString("branch");
			deptid1 = Integer.parseInt(b2.getString("deptid"));
			if (uname != "") {
				String ename = name.getText().toString();
				Log.e("ename", ename);
				String phnum1 = phnum.getText().toString();
				String emailid = email1.getText().toString();
				String subject = sub.getText().toString();
				String quali = qua.getText().toString();
				String des = desig.getText().toString();
				String expr = exp.getText().toString();
				String emptyp = sp1.getSelectedItem().toString();

				this.myDbHelper = new Dbhandler(this);
				FetchingData();
				System.out
						.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
				Mydatabase = myDbHelper.getReadableDatabase();
				// Mydatabase.execSQL("INSERT INTO EmployeeDetails VALUES ('"+id+"','"+ename+"','"+quali+"','"+des+"','"+expr+"','"+phnum1+"','"+emailid+"','"+subject+"') ");
				Mydatabase
						.execSQL("update employeedetails set EmpDesignation='"
								+ des + "',EmpPhnum='" + phnum1 + "',EmpExp='"
								+ expr + "',Empemailid='" + emailid
								+ "',subject='" + subject
								+ "',EmpQualification='" + quali
								+ "',EmpType='" + emptyp + "' where EmpName='"
								+ uname + "' and DeptID=" + deptid1);
				System.out.println("^^^^^^^^^^^^^^^ inserted value is   "
						+ ename);
				Toast.makeText(getApplicationContext(), ename, 70).show();
				// deptid.clearComposingText();
				email1.clearComposingText();
				email1.clearComposingText();
			}
		} else {

			String ename = name.getText().toString();

			Log.e("ename", ename);
			String phnum1 = phnum.getText().toString();
			String id = sp.getSelectedItem().toString();

			if (id.equals("CSE")) {

				id1 = "1";
			} else if (id.equals("ECE")) {
				id1 = "2";
			} else if (id.equals("EEE")) {
				id1 = "3";
			} else if (id.equals("MECH")) {
				id1 = "4";
			} else if (id.equals("CIVIL")) {

				id1 = "5";
			} else if (id.equals("CHEM")) {

				id1 = "6";
			}
			// System.out.println("VVVVVVVVVVVVVVVVVVVVVv   "+depts.get(k));

			String emailid = email1.getText().toString();

			String subject = sub.getText().toString();

			String quali = qua.getText().toString();

			String des = desig.getText().toString();

			String expr = exp.getText().toString();
			// String ty=type.getText().toString();
			String aaaaa = sp1.getSelectedItem().toString();
			/*
			 * if(ty.equalsIgnoreCase("Teach")){ ty1="Teach"; } else
			 * if(ty.equalsIgnoreCase("Non-Teach"));{ ty1="Non-Teach"; }
			 */
			this.myDbHelper = new Dbhandler(this);
			System.out.println("^^^^^^^^^^^^&&&&&&&&&&  name is" + ename);
			FetchingData();
			System.out
					.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
			Mydatabase = myDbHelper.getReadableDatabase();
			Mydatabase.execSQL("INSERT INTO EmployeeDetails VALUES ('" + id1
					+ "','" + ename + "','" + quali + "','" + des + "','"
					+ expr + "','" + phnum1 + "','" + emailid + "','" + subject
					+ "','" + aaaaa + "') ");

			Toast.makeText(getApplicationContext(), ename, 70).show();
			// deptid.clearComposingText();
			email1.clearComposingText();
			email1.clearComposingText();
		}
	}

	private void FetchingData() {
		// TODO Auto-generated method stub
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

		} catch (SQLException sqle) {

			throw sqle;

		}

	}

}
