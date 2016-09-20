package myapplication.pc1.com.homeworksecondex;

import android.app.ActionBar;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Called",Toast.LENGTH_SHORT).show();

        Intent intentForBroadCast = new Intent();
        intentForBroadCast.putExtra("Hello","Hello New BroadCast");
        intentForBroadCast.setAction("");

        context.sendBroadcast(intentForBroadCast);
    }
}
