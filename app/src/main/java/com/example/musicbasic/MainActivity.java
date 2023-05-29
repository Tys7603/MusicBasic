package com.example.musicbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.musicbasic.Adapter.AdapterSong;
import com.example.musicbasic.Interface.ItemClickUri;
import com.example.musicbasic.Modal.Song;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView countSongs;
    RecyclerView rcvSong;
    MediaPlayer mediaPlayer;
    List<Song> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getListRaw();
        init();
        setAdapterSong();
    }

    public void init(){
        rcvSong = findViewById(R.id.rc_ListSong);
        countSongs =findViewById(R.id.count_songs);
        countSongs.setText(String.valueOf(list.size()));
    }

    public void setAdapterSong(){
        AdapterSong adapterSong = new AdapterSong(list, this , uri -> {
            try {
                mediaPlayer.reset();
            }catch (Exception e){
                e.printStackTrace();
            }
            mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
            mediaPlayer.start();

        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rcvSong.setLayoutManager(layoutManager);
        rcvSong.setAdapter(adapterSong);
    }

    public void getListRaw(){
        Field[] fields = R.raw.class.getFields();
        for(int i = 0 ; i < fields.length ; i++){
            list.add(new Song(fields[i].getName() , getRawUri(fields[i].getName())));
        }

    }
    public Uri getRawUri(String fileName){
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + File.pathSeparator + File.separator + File.separator + getPackageName() + "/raw/" + fileName);
    }

}