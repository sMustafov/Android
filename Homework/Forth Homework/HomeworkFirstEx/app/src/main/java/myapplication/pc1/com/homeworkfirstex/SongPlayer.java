package myapplication.pc1.com.homeworkfirstex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SongPlayer extends Activity implements View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener {

    private static MediaPlayer mMediaPlayer;
    private ArrayList<File> mSongs;
    int mPosition;
    SeekBar mSeekBar;
    Button mPlayButton, mBackwardButton, mForwardButton, mPictureButton, mLyricsButton;
    TextView mSongNameView, mTime;
    Uri mUri;
    Thread mUpdateSeekBar;
    String mSongName;
    ImageView picView;
    float mx,my;

    String selectedImagePath;

    private long minutes;
    private long seconds;
    private String min;
    private String sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_player_layout);

        picView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent event) {

                float curX, curY;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mx = event.getX();
                        my = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = event.getX();
                        curY = event.getY();
                        picView.scrollBy((int) (mx - curX), (int) (my - curY));
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        curX = event.getX();
                        curY = event.getY();
                        picView.scrollBy((int) (mx - curX), (int) (my - curY));
                        break;
                }

                return true;
            }
        });

        mPlayButton = (Button)findViewById(R.id.playButton);
        mBackwardButton = (Button)findViewById(R.id.buttonBackward);
        mForwardButton = (Button)findViewById(R.id.buttonForward);
        mSongNameView = (TextView)findViewById(R.id.songsName);
        mTime = (TextView)findViewById(R.id.time);

        mSeekBar = (SeekBar)findViewById(R.id.seekBar);

        mPlayButton.setOnClickListener(this);
        mBackwardButton.setOnClickListener(this);
        mForwardButton.setOnClickListener(this);
        mPictureButton.setOnClickListener(this);
        mLyricsButton.setOnClickListener(this);

        mBackwardButton.setOnLongClickListener(this);
        mForwardButton.setOnLongClickListener(this);

        if (mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }

        mUpdateSeekBar = new Thread() {
            @Override
            public void run() {
                int totalDuration = mMediaPlayer.getDuration();
                int currentPosition = 0;

                while (currentPosition < totalDuration) {
                    try {
                        sleep(1000);
                        currentPosition = mMediaPlayer.getCurrentPosition();
                        if (mSeekBar != null) {
                            mSeekBar.setProgress(currentPosition);
                        }
                        mSeekBar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        mSeekBar.setProgress(totalDuration);
                        break;
                    }
                }
            }
        };

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        mSongs = (ArrayList)bundle.getParcelableArrayList("songList");
        mPosition = bundle.getInt("pos",0);

        mUri = Uri.parse(mSongs.get(mPosition).toString());
        mMediaPlayer = MediaPlayer.create(getApplicationContext(), mUri);

        mSongNameView = (TextView)findViewById(R.id.songsName);
        mSongName = mSongs.get(mPosition).getName().toString();
        mSongNameView.setText(mSongName);

        mMediaPlayer.start();
        mSeekBar.setMax(mMediaPlayer.getDuration());

        setSongTime();

        mUpdateSeekBar.start();

        mSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.playButton:
                if (mMediaPlayer.isPlaying()){
                    mPlayButton.setText(">");
                    mMediaPlayer.pause();
                }else{
                    mMediaPlayer.start();
                    mPlayButton.setText("||");
                }
                break;
            case R.id.buttonForward:
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mPosition = (mPosition + 1) % mSongs.size();

                mSongNameView = (TextView)findViewById(R.id.songsName);
                mSongName = mSongs.get(mPosition).getName().toString();
                mSongNameView.setText(mSongName);

                mUri = Uri.parse(mSongs.get(mPosition).toString());
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), mUri);
                mMediaPlayer.start();
                mSeekBar.setMax(mMediaPlayer.getDuration());

                setSongTime();

                break;
            case R.id.buttonBackward:
                mMediaPlayer.stop();
                mMediaPlayer.release();
                if (mPosition - 1 < 0){
                    mPosition = mSongs.size() - 1;
                }else {
                    mPosition = mPosition - 1;
                }

                mSongNameView = (TextView)findViewById(R.id.songsName);
                mSongName = mSongs.get(mPosition).getName().toString();
                mSongNameView.setText(mSongName);

                mUri = Uri.parse(mSongs.get(mPosition).toString());
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), mUri);
                mMediaPlayer.start();
                mSeekBar.setMax(mMediaPlayer.getDuration());

                setSongTime();

                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.buttonForward:
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + 5000);
                break;
            case R.id.buttonBackward:
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - 5000);
                break;
        }

        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mMediaPlayer.seekTo(seekBar.getProgress());
    }

    public void setSongTime(){
        minutes = TimeUnit.MILLISECONDS.toMinutes(mMediaPlayer.getDuration());
        seconds = TimeUnit.MILLISECONDS.toSeconds(mMediaPlayer.getDuration());
        min = Long.toString(minutes);
        sec = Long.toString(seconds).substring(0,2);
        mTime.setText(min + ":" + sec);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getImagePath(selectedImageUri);
            }
        }

        Drawable d = Drawable.createFromPath(selectedImagePath);
        picView.setImageDrawable(d);

    }

    public String getImagePath(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
}
