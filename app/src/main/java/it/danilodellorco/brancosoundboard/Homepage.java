package it.danilodellorco.brancosoundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Homepage extends AppCompatActivity {

    public class Holder implements View.OnClickListener {
        ImageButton btnLuca;
        Context context;

        public void Holder(){
            context = getApplicationContext();
            btnLuca = findViewById(R.id.btnLuca);
            btnLuca.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnLuca){
                Intent i = new Intent( Homepage.this , MainActivity.class);

                startActivity (i);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }
}
