package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public class holder{
        ListView listView;
        MediaPlayer mp;

        public holder(){
            mp = MediaPlayer.create(getApplicationContext(),R.raw.Incoerenza);
            listView = findViewById(R.id.listView);
            /// Getting list of Strings from your resource
            String[] testArray = getResources().getStringArray(R.array.Luca);
            List<String> testList = Arrays.asList(testArray);

            // Instanciating Adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_list_item_1, testList);

            // setting adapter on listview
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, final View componente, int pos, long id){
                        // recupero il titolo memorizzato nella riga tramite l'ArrayAdapter
                        final String titoloriga = (String) adapter.getItemAtPosition(pos);
                        Log.d("List", "Ho cliccato sull'elemento con titolo " + titoloriga);
                        if (titoloriga == "1"){

                            mp.start();
                        }
                    }
                });
            }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        holder holder = new holder();



    }
}
