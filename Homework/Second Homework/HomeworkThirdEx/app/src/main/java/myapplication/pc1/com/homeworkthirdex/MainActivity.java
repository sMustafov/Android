package myapplication.pc1.com.homeworkthirdex;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context mCtx;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> data2 = new ArrayList<String>();
        ArrayList<String> data3 = new ArrayList<String>();
        ArrayList<ImageButton> data4 = new ArrayList<ImageButton>();

        for (int index = 0; index < 50; index++) {
            ImageButton ib = (ImageButton)findViewById(R.id.imageButton);
            String name = String.format("Song " + index + " song song song ...");
            String info = String.format("Author of song with number " + index);
            String action = null;
            if(index % 2 == 0){
                action = "";
            }else {
                action = "explicit";
            }
            data.add(index, String.valueOf(name));
            data2.add(index, String.valueOf(info));
            data3.add(index, String.valueOf(action));
            data4.add(index, ib);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleViewAdapter(data, data2, data3, data4);

        mRecyclerView.setAdapter(mAdapter);

        RecycleViewCustomDecoration itemCustomDecoration = new RecycleViewCustomDecoration();

        mRecyclerView.addItemDecoration(itemCustomDecoration);

        mCtx = this;
    }
}
