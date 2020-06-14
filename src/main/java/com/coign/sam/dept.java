package com.coign.sam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class dept extends Activity{
	Button teac,nonteach;
String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dept);
		Bundle b2=getIntent().getExtras();
		final String br=b2.getString("branch");
		teac=(Button)findViewById(R.id.button1);
		nonteach=(Button)findViewById(R.id.button2);
		teac.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(dept.this,List.class);
				type=(String) teac.getText();
				Toast.makeText(getApplicationContext(), type, 100).show();
				it.putExtra("branch", br);
				it.putExtra("type", type);
	            startActivity(it);	            
				
			}
		});
    nonteach.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(dept.this,List.class);
				type=(String) nonteach.getText();
				Toast.makeText(getApplicationContext(), type, 100).show();
				it.putExtra("branch", br);
				it.putExtra("type", type);
	            startActivity(it);	            
				
			}
		});
	}
	

}
