package myapplication.pc1.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    int mExplicitButtonPress;
    Button mExplicitButton;

    int mImplicitButtonPress;
    Button mImplicitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExplicitButton = (Button)findViewById(R.id.button);
        mExplicitButton.setOnClickListener(this);

        mImplicitButton = (Button)findViewById(R.id.button2);
    }

    public void onButtonClicked(View view){
        if(view.getId() == R.id.button2){
            mImplicitButtonPress++;
            if(mImplicitButton != null) {
                mImplicitButton.setText(String.valueOf(mImplicitButtonPress));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            mExplicitButtonPress++;
            if(mExplicitButton != null) {
                mExplicitButton.setText(String.valueOf(mExplicitButtonPress));
            }
        }
    }
}
