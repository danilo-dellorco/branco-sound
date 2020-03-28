package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {
    private int STORAGE_PERMISSION_CODE = 1;

    public class Holder implements View.OnClickListener {
        ImageButton btnLuca,btnDiup,btnMarasma,btnPica,btnPasonzio,btnConca,btnQaro,btnTrulli,
                    btnPres,btnZombi,btnDoc;
        Context context;

        public Holder(){

            context = getApplicationContext();
            btnLuca = findViewById(R.id.btnLuca);
            btnLuca.setOnClickListener(this);

            btnDiup = findViewById(R.id.btnDiup);
            btnDiup.setOnClickListener(this);

            btnMarasma = findViewById(R.id.btnMarasma);
            btnMarasma.setOnClickListener(this);

            btnPica = findViewById(R.id.btnPica);
            btnPica.setOnClickListener(this);

            btnPasonzio = findViewById(R.id.btnPasonzio);
            btnPasonzio.setOnClickListener(this);

            btnConca = findViewById(R.id.btnConca);
            btnConca.setOnClickListener(this);

            btnQaro = findViewById(R.id.btnQaro);
            btnQaro.setOnClickListener(this);

            btnTrulli = findViewById(R.id.btnTrulli);
            btnTrulli.setOnClickListener(this);

            btnPres = findViewById(R.id.btnPres);
            btnPres.setOnClickListener(this);

            btnZombi = findViewById(R.id.btnZombi);
            btnZombi.setOnClickListener(this);

            btnDoc = findViewById(R.id.btnDoc);
            btnDoc.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent( Homepage.this , MainActivity.class);
            switch (v.getId()){
                case R.id.btnLuca:
                    i.putExtra("Nome","Panilallo");
                    i.putExtra("Array",R.array.Panilallo);
                    i.putExtra("Foto",R.drawable.panilallo);
                    break;

                case R.id.btnDiup:
                    i.putExtra("Nome","Diup");
                    i.putExtra("Array",R.array.Diup);
                    i.putExtra("Foto",R.drawable.diup);
                    break;

                case R.id.btnMarasma:
                    i.putExtra("Nome","Marasma");
                    i.putExtra("Array",R.array.Marasma);
                    i.putExtra("Foto",R.drawable.marasma);
                    break;

                case R.id.btnPica:
                    i.putExtra("Nome","Pixinho");
                    i.putExtra("Array",R.array.Pixinho);
                    i.putExtra("Foto",R.drawable.pix);
                    break;

                case R.id.btnPasonzio:
                    i.putExtra("Nome","Pasonzio");
                    i.putExtra("Array",R.array.Pasonzio);
                    i.putExtra("Foto",R.drawable.pasonzio);
                    break;

                case R.id.btnConca:
                    i.putExtra("Nome","Conca");
                    i.putExtra("Array",R.array.Conca);
                    i.putExtra("Foto",R.drawable.conca);
                    break;

                case R.id.btnQaro:
                    i.putExtra("Nome","Qarocochi");
                    i.putExtra("Array",R.array.Qarocochi);
                    i.putExtra("Foto",R.drawable.qaro);
                    break;

                case R.id.btnTrulli:
                    i.putExtra("Nome","Trulli");
                    i.putExtra("Array",R.array.Trulli);
                    i.putExtra("Foto",R.drawable.trulli);
                    break;

                case R.id.btnPres:
                    i.putExtra("Nome","Presidente");
                    i.putExtra("Array",R.array.Presidente);
                    i.putExtra("Foto",R.drawable.pres);
                    break;

                case R.id.btnZombi:
                    i.putExtra("Nome","Zombi");
                    i.putExtra("Array",R.array.Zombi);
                    i.putExtra("Foto",R.drawable.zombi);
                    break;

                case R.id.btnDoc:
                    i.putExtra("Nome","Dottore");
                    i.putExtra("Array",R.array.Dottore);
                    i.putExtra("Foto",R.drawable.doc);
                    break;

            }
            startActivity (i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        checkPermissions();
        Holder holder = new Holder();

    }

    public void checkPermissions(){
        if (ContextCompat.checkSelfPermission(Homepage.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Homepage.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
}
