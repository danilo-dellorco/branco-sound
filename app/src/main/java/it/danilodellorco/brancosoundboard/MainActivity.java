package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {


    public class holder implements View.OnClickListener{
        Button btnPlay;
        Button btnShare;
        MediaPlayer mp;

        public holder(){
            btnPlay = findViewById(R.id.btnPlay);
            btnShare = findViewById(R.id.btnShare);
            mp = MediaPlayer.create(getApplicationContext(), R.raw.battisti);
            btnPlay.setOnClickListener(this);
            btnShare.setOnClickListener(this);
        }

        private void CopyRAWtoSDCard(int id, String path) throws IOException {
            File mydir = new File(Environment.getExternalStorageDirectory() + "/branco/");
            if(!mydir.exists())
                mydir.mkdirs();
            else
                Log.d("error", "dir. already exists");
            InputStream in = getResources().openRawResource(id);
            FileOutputStream out = new FileOutputStream(path);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnPlay){
                mp.start();
            }
            if (v.getId() == R.id.btnShare){
                String path = Environment.getExternalStorageDirectory() + "/branco";
                try {
                    CopyRAWtoSDCard(R.raw.battisti,path + File.separator + "battisti.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri uri = Uri.parse(path + File.separator + "battisti.mp3");
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                //String mimeType = getContentResolver().getType(uri);
                sharingIntent.setType("audio/mp3");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holder holder = new holder();



    }
}
