package myapplication.pc1.com.homeworksecondex;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(getIntent().hasExtra("Data"))
        {
            Toast.makeText(this,getIntent().getStringExtra("Data"),Toast.LENGTH_SHORT).show();
        }


        BroadCastHandler.getInstance().addObserver(this);
    }

    public void registerForBroadcast(View view)
    {
        receiver = new MyReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");

        registerReceiver(receiver,filter);

    }

    public void unregisterForBroadcast(View v)
    {
        if(receiver != null)
            unregisterReceiver(receiver);
    }

    @Override
    public void update(Observable o, Object arg) {
        Toast.makeText(this,"Passed data to activity !!!",Toast.LENGTH_SHORT).show();
    }
}