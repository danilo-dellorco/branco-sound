package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {

    public class Holder implements View.OnClickListener {
        ImageButton btnLuca;
        Context context;

        public Holder(){

            context = getApplicationContext();
            btnLuca = findViewById(R.id.btnLuca);
            btnLuca.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent( Homepage.this , MainActivity.class);
            i.putExtra("Nome","Panilallo");
            i.putExtra("Array",R.array.Panilallo);
            i.putExtra("Foto",R.drawable.panilallo);

            startActivity (i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Holder holder = new Holder();
    }
}
