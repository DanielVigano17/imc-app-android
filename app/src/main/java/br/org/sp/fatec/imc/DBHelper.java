package br.org.sp.fatec.imc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "imc_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "historico_imc";

    private static final String COL_ID = "id";
    private static final String COL_NOME = "nome";
    private static final String COL_IDADE = "idade";
    private static final String COL_ALTURA = "altura";
    private static final String COL_PESO = "peso";
    private static final String COL_IMC = "imc";
    private static final String COL_CATEGORIA = "categoria";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOME + " TEXT, " +
                COL_IDADE + " INTEGER, " +
                COL_ALTURA + " REAL, " +
                COL_PESO + " REAL, " +
                COL_IMC + " REAL, " +
                COL_CATEGORIA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nome, int idade, double altura, double peso, double imc, String categoria) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOME, nome);
        contentValues.put(COL_IDADE, idade);
        contentValues.put(COL_ALTURA, altura);
        contentValues.put(COL_PESO, peso);
        contentValues.put(COL_IMC, imc);
        contentValues.put(COL_CATEGORIA, categoria);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC", null);
    }
}