package br.org.sp.fatec.imc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DBHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mostrarResultado(View view) {
        TextInputEditText inputNome = findViewById(R.id.inputNome);
        TextInputEditText inputIdade = findViewById(R.id.inputIdade);
        TextInputEditText inputPeso = findViewById(R.id.inputPeso);
        TextInputEditText inputAltura = findViewById(R.id.inputAltura);

        String nome = inputNome.getText().toString();
        String idadeStr = inputIdade.getText().toString();
        String pesoStr = inputPeso.getText().toString();
        String alturaStr = inputAltura.getText().toString();

        if (nome.isEmpty() || idadeStr.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int idade = Integer.parseInt(idadeStr);
        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);

        double valorIMC = calcularIMC(peso, altura);
        String categoria = obterCategoria(valorIMC);

        // Salvar no Banco de Dados
        boolean inseriu = dbHelper.insertData(nome, idade, altura, peso, valorIMC, categoria);
        if (inseriu) {
            Toast.makeText(this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
        }

        chamarTelaDeResultado(categoria, valorIMC, nome);
    }

    public void verHistorico(View view) {
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);
    }

    public double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private String obterCategoria(double valorIMC) {
        if (valorIMC < 18.5) return "MAGREZA";
        if (valorIMC < 24.9) return "NORMAL";
        if (valorIMC < 29.9) return "SOBREPESO";
        if (valorIMC < 39.9) return "OBESIDADE";
        return "OBESIDADE_GRAVE";
    }

    private void chamarTelaDeResultado(String categoria, double imc, String nome) {
        Class<?> activityClass;
        String descCategoria;

        switch (categoria) {
            case "MAGREZA":
                activityClass = Magreza.class;
                descCategoria = "Magreza";
                break;
            case "NORMAL":
                activityClass = Normal.class;
                descCategoria = "Peso normal";
                break;
            case "SOBREPESO":
                activityClass = Sobrepeso.class;
                descCategoria = "Sobrepeso";
                break;
            case "OBESIDADE":
                activityClass = Obesidade.class;
                descCategoria = "Obesidade";
                break;
            default:
                activityClass = ObesidadeGrave.class;
                descCategoria = "Obesidade grave";
                break;
        }

        Intent intent = new Intent(this, activityClass);
        intent.putExtra("IMC", imc);
        intent.putExtra("NOME", nome);
        intent.putExtra("CATEGORIA", descCategoria);
        startActivity(intent);
    }
}