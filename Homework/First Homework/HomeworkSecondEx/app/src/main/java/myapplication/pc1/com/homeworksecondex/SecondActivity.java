package myapplication.pc1.com.homeworksecondex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity_layout);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        textView2.setText(extras.getString("COMPONENT_ID"));
        textView3.setText(extras.getString("COMPONENT_TEXT"));
    }
}
