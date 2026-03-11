package br.org.sp.fatec.imc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarResultado(View view)
    {
        TextInputEditText inputPeso = findViewById(R.id.inputPeso);
        TextInputEditText inputAltura = findViewById(R.id.inputAltura);

        String pesoStr = inputPeso.getText().toString();
        String alturaStr = inputAltura.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) return;

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);

        double valorIMC = calcularIMC(peso,altura);

        String categoria;

        if (valorIMC < 18.5)
            categoria = "MAGREZA";
        else if (valorIMC < 24.9)
            categoria = "NORMAL";
        else if (valorIMC < 29.9)
            categoria = "SOBREPESO";
        else if (valorIMC < 39.9)
            categoria = "OBESIDADE";
        else
            categoria = "OBESIDADE_GRAVE";

        chamarTelaDeResultado(categoria, valorIMC);
    }

    public double calcularIMC(double peso, double altura) {
        if (altura <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que zero.");
        }

        return peso / (altura * altura);
    }

    private void chamarTelaDeResultado(String categoria, double imc)
    {
        switch (categoria) {
            case "MAGREZA":
                abrirTela(Magreza.class, imc, "Magreza");
                break;

            case "NORMAL":
                abrirTela(Normal.class, imc, "Peso normal");
                break;

            case "SOBREPESO":
                abrirTela(Sobrepeso.class, imc, "Sobrepeso");
                break;

            case "OBESIDADE":
                abrirTela(Obesidade.class, imc, "Obesidade");
                break;

            case "OBESIDADE_GRAVE":
                abrirTela(ObesidadeGrave.class, imc, "Obesidade grave");
                break;
        }
    }

    private void abrirTela(Class<?> activityClass, double imc, String categoria) {

        TextInputEditText inputNome = findViewById(R.id.inputNome);

        Intent intent = new Intent(MainActivity.this, activityClass);

        intent.putExtra("IMC", imc);
        intent.putExtra("NOME", inputNome.getText().toString());
        intent.putExtra("CATEGORIA", categoria);

        startActivity(intent);
    }
}