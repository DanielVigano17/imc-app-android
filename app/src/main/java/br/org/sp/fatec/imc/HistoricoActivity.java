package br.org.sp.fatec.imc;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HistoricoActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listView;
    private List<Map<String, String>> listaHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listViewHistorico);
        Button btnVoltar = findViewById(R.id.btnVoltarHistorico);

        btnVoltar.setOnClickListener(v -> finish());

        carregarDados();
    }

    private void carregarDados() {
        Cursor cursor = dbHelper.getAllData();
        listaHistorico = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Map<String, String> item = new HashMap<>();
                    item.put("nome", cursor.getString(1));
                    item.put("idade", cursor.getString(2));
                    item.put("altura", cursor.getString(3));
                    item.put("peso", cursor.getString(4));
                    item.put("imc", String.format(Locale.getDefault(), "%.2f", cursor.getDouble(5)));
                    item.put("categoria", cursor.getString(6));
                    listaHistorico.add(item);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        HistoricoAdapter adapter = new HistoricoAdapter();
        listView.setAdapter(adapter);
    }

    private class HistoricoAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return listaHistorico.size();
        }

        @Override
        public Object getItem(int position) {
            return listaHistorico.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(HistoricoActivity.this).inflate(R.layout.item_historico, parent, false);
            }

            Map<String, String> item = listaHistorico.get(position);

            TextView txtNome = convertView.findViewById(R.id.txtNome);
            TextView txtDetalhes = convertView.findViewById(R.id.txtDetalhes);
            TextView txtCategoria = convertView.findViewById(R.id.txtCategoria);

            txtNome.setText(item.get("nome"));
            txtDetalhes.setText("Idade: " + item.get("idade") + " | IMC: " + item.get("imc") + " (Peso: " + item.get("peso") + "kg)");
            txtCategoria.setText("Categoria: " + item.get("categoria"));

            return convertView;
        }
    }
}
