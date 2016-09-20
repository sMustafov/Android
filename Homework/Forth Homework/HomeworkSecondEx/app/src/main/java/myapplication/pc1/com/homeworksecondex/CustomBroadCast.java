package myapplication.pc1.com.homeworksecondex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("Hello")) {
            Toast.makeText(context, intent.getStringExtra("Hello"), Toast.LENGTH_SHORT).show();
            BroadCastHandler.getInstance().updateValue("Pass data to activity");
        }
    }
}