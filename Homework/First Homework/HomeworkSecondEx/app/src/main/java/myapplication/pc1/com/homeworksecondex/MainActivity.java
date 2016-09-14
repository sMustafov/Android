package myapplication.pc1.com.homeworksecondex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int lastPressedButton; // mButton = 1, mLeftButton = 2, mRightButton = 3
    int mButtonPressed;
    int mLeftButtonPressed;
    int mRightButtonPressed;

    Button mButton, mLeftButton, mRightButton;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

        mLeftButton = (Button) findViewById(R.id.leftButton);
        mLeftButton.setOnClickListener(this);

        mRightButton = (Button) findViewById(R.id.rightButton);
        mRightButton.setOnClickListener(this);

        text = (TextView)findViewById(R.id.textView);
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            mButtonPressed++;
            lastPressedButton = 1;
            if(mButton != null) {
                if (mButtonPressed == 2) {
                    text.setText(String.valueOf(mButton.getText()));
                    mButtonPressed = 0;
                    mLeftButtonPressed = 0;
                    mRightButtonPressed = 0;
                } else {
                    text.setText(String.valueOf(mButton.getId()));
                    mLeftButtonPressed = 0;
                    mRightButtonPressed = 0;
                }
            }
        }else if(v.getId() == R.id.leftButton){
            mLeftButtonPressed++;
            lastPressedButton = 2;
            if (mLeftButtonPressed == 2) {
                text.setText(String.valueOf(mLeftButton.getText()));
                mButtonPressed = 0;
                mLeftButtonPressed = 0;
                mRightButtonPressed = 0;
            } else {
                text.setText(String.valueOf(mLeftButton.getId()));
                mButtonPressed = 0;
                mRightButtonPressed = 0;
            }
        }else if(v.getId() == R.id.rightButton){
            mRightButtonPressed++;
            lastPressedButton = 3;
            if(mRightButton != null) {
                if (mRightButtonPressed == 2) {
                    text.setText(String.valueOf(mRightButton.getText()));
                    mButtonPressed = 0;
                    mLeftButtonPressed = 0;
                    mRightButtonPressed = 0;
                } else {
                    text.setText(String.valueOf(mRightButton.getId()));
                    mButtonPressed = 0;
                    mLeftButtonPressed = 0;
                }
            }
        }else if(v.getId() == R.id.textView){
            if(text != null){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String componentId = String.valueOf(text.getId());
                String componentText = String.valueOf(text.getText());

                if(lastPressedButton == 1){
                    componentId = String.valueOf(mButton.getId());
                    componentText = String.valueOf(text.getText());
                }else if(lastPressedButton == 2){
                    componentId = String.valueOf(mLeftButton.getId());
                    componentText = String.valueOf(text.getText());
                }else if(lastPressedButton == 3){
                    componentId = String.valueOf(mRightButton.getId());
                    componentText = String.valueOf(text.getText());
                }

                intent.putExtra("COMPONENT_ID", componentId);
                intent.putExtra("COMPONENT_TEXT", componentText);

                startActivity(intent);
            }
        }
    }
}