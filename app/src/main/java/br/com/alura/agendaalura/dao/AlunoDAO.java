package br.com.alura.agendaalura.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agendaalura.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql ="CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone, site TEXT, nota REAL, foto TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //  deletaTabela();
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAluno(aluno);

        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("foto", aluno.getFoto());

        return dados;
    }

    public List<Aluno>  buscaAlunos() {
        String sql = "SELECT id, nome, endereco, telefone, site, nota, foto FROM Alunos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Aluno> alunos = new ArrayList<Aluno>();
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setFoto(cursor.getString(cursor.getColumnIndex("foto")));

            alunos.add(aluno);
        }
        cursor.close();

        return alunos;
    }
    public void deleta(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params);
    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosAluno(aluno);
        String[] params = {aluno.getId().toString()};
        db.update("Alunos", dados, "id = ?", params);
    }

    public boolean isAluno(String telefone) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor  = db.rawQuery("SELECT NOME FROM Alunos WHERE TELEFONE = ?", new String[]{telefone});
        int resultado = cursor.getCount();
        cursor.close();
        return resultado > 0;
    }

    public void deletaTabela() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DROP TABLE IF EXISTS Alunos;";
        db.execSQL(sql);
    }
}
