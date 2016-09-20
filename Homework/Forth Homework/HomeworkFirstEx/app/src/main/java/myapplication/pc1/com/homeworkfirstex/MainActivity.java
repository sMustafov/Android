package myapplication.pc1.com.homeworkfirstex;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "Permission";
    private static final int REQUEST_READ_STORAGE = 112;

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

        askPermission();

        ArrayList<String> items = new ArrayList<String>();
        final ArrayList<File> mySongs = findSongs(Environment.getExternalStorageDirectory());

        if(mySongs != null) {

            for (int index = 0; index < mySongs.size(); index++) {
                ImageButton ib = (ImageButton)findViewById(R.id.imageButton);
                items.add(index, mySongs.get(index).getName().toString().replace(".mp3", ""));
                String info = String.format("Author of song with number " + index);
                String action = null;
                if(index % 2 == 0){
                    action = "";
                }else {
                    action = "explicit";
                }
                data.add(index, String.valueOf(items));
                data2.add(index, String.valueOf(info));
                data3.add(index, String.valueOf(action));
                data4.add(index, ib);
            }
        }else{
            items.add(Environment.getExternalStorageState().toString());
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerViewAdapter(data, data2, data3, data4);

        mRecyclerView.setAdapter(mAdapter);

        RecyclerViewCustomDecoration itemCustomDecoration = new RecyclerViewCustomDecoration();

        mRecyclerView.addItemDecoration(itemCustomDecoration);

        mCtx = this;
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_READ_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_STORAGE: {
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");
                } else {

                    Log.i(TAG, "Permission has been granted by user");
                }
                return;
            }
        }
    }

    public void askPermission() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the SD-CARD is required for this app to Download PDF.")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Clicked");
                        makeRequest();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest();
            }
        }
    }

    public ArrayList<File> findSongs(File root) {
        ArrayList<File> fileArray = new ArrayList<>();

        File[] files = root.listFiles();

        if (files != null) {
            for (File singleFile : files) {
                if (singleFile.isDirectory() && !singleFile.isHidden()) {
                    fileArray.addAll(findSongs(singleFile));
                } else {
                    if (singleFile.getName().endsWith(".mp3")) {
                        fileArray.add(singleFile);
                    }
                }
            }

            return fileArray;
        } else {

            return null;
        }
    }
}