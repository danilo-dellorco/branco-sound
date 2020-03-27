package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public class holder implements View.OnClickListener{
        ListView listView;
        ImageView imageView;
        TextView tvName;
        MediaPlayer mp;
        Button btnStop;
        String name = getIntent().getStringExtra("Nome");

        public holder(){
            int array = getIntent().getIntExtra("Array",0);
            final Context context = getApplicationContext();
            imageView = findViewById(R.id.imageView);
            tvName = findViewById(R.id.tvName);
            btnStop = findViewById(R.id.btnStop);
            btnStop.setOnClickListener(this);

            mp = new MediaPlayer();
            listView = findViewById(R.id.listView);
            /// Getting list of Strings from your resource
            String[] testArray = getResources().getStringArray(array);
            List<String> testList = Arrays.asList(testArray);

            // Instanciating Adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_list_item_1, testList);

            // setting adapter on listview
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = listView.getItemAtPosition(position).toString();
                    String pos = Integer.toString(position+1);
                    Log.v ("MAP","raw/"+name+"_"+pos);
                    int resId = getResources().getIdentifier("raw/"+"panilallo"+"_"+pos, null, context.getPackageName());
                    musicPlay(resId);
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                    String selectedItem = listView.getItemAtPosition(pos).toString();
                    int resId = getResources().getIdentifier("raw/"+selectedItem, null, context.getPackageName());
                    musicShare(resId);
                    return true;
                }
            });
        }


        public void musicPlay(int sound){
            if (mp.isPlaying()) {
                mp.stop();
                mp = MediaPlayer.create(getApplicationContext(),sound);
                mp.start();
            }
            else {
                mp = MediaPlayer.create(getApplicationContext(),sound);
                mp.start();
            }
        }


        @Override
        public void onClick(View v) {
            if (mp.isPlaying()) {
                mp.stop();
            }
        }

        private void CopyRAWtoSDCard(int id, String path) throws IOException {
            String directory = Environment.getExternalStorageDirectory() + "/branco/";
            File mydir = new File(directory);
            if(!mydir.exists())
                mydir.mkdirs();
            else{
                Log.d("error", "dir. already exists");
            }
            File file = new File(path);
            if(file.exists()) {
                return;
            }
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

            public void musicShare(int sound) {
            String path = Environment.getExternalStorageDirectory() + "/branco";
            String name = getResources().getResourceEntryName(sound);
            String filePath = path + File.separator + name + ".mp3";
            try {
                CopyRAWtoSDCard(sound,filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.parse(filePath);
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
            sharingIntent.setType("audio/mp3");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holder holder = new holder();
        holder.tvName.setText(holder.name);
        holder.imageView.setImageResource(getIntent().getIntExtra("Foto",0));
    }
}
