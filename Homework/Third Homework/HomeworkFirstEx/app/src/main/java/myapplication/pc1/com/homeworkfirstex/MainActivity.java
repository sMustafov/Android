package myapplication.pc1.com.homeworkfirstex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentOne.IBtnPressed  {


    Button btn;
    FragmentTwo fragmentToCom;
    FragmentTwo fragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onButtonPressed(String dataToExchange) {
        if(fragmentToCom != null)
        {
            fragmentToCom.onDataExchange(dataToExchange);
            getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragmentTwo).commit();
        }
    }

    @Override
    public void onButtonReplacePressed() {

        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragmentTwo).commit();

    }

}
