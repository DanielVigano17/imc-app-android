package br.org.sp.fatec.imc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Obesidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_obesidade);

        Intent intent = getIntent();

        double imc = intent.getDoubleExtra("IMC", 0);
        String categoria = intent.getStringExtra("CATEGORIA");
        String nome = intent.getStringExtra("NOME");

        TextView txtResultado = findViewById(R.id.textResultado);
        TextView txtValores = findViewById(R.id.textValores);

        txtValores.setText(nome + " seu IMC é " + String.format("%.2f", imc));
        txtResultado.setText("Sua categoria é : " + categoria);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}