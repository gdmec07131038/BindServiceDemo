package cn.edu.gdmec.s07131038.bindservicedemo;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button mybtn1,mybtn2,mybtn3,mybtn4;
	TextView mytv;
	Intent myIt=new Intent("cn.edu.gdmec.boundservice");
	
	boolean isbound=false;
	BoundService myboundservice;
	
	ServiceConnection mConnnection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			isbound=false;
		}
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			myboundservice=((BoundService.LocalBinder)arg1).getService();
			isbound=true;
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytv=(TextView) findViewById(R.id.textView1);
        mybtn1=(Button) findViewById(R.id.button1);
        mybtn2=(Button) findViewById(R.id.button2);
        mybtn3=(Button) findViewById(R.id.button3);
        mybtn4=(Button) findViewById(R.id.button4);
        mybtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bindService(myIt, mConnnection, Context.BIND_AUTO_CREATE);
			}
		});
        mybtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(mConnnection);
				isbound=false;
			}
		});
        mybtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				long a=Math.round(Math.random()*100);
				long b=Math.round(Math.random()*100);
				if(isbound){
					long avg=myboundservice.Avg(a, b);
					mytv.setText((String.valueOf(a)+"+"+String.valueOf(b))+"/2"+"="+String.valueOf(avg));
				}
			}
		});
        mybtn4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=String.valueOf(myboundservice.PI);
				if(isbound){
					mytv.setText(str);
				}
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
