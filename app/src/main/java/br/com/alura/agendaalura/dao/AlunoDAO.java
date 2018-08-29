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
        super(context, "Agenda", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql ="CREATE TABLE Alunos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone, site TEXT, nota REAL, caminhoFoto TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                String sqlCriaTabela ="CREATE TABLE Alunos_novo (id CHAR(36) PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone, site TEXT, nota REAL, caminhoFoto TEXT)";
                db.execSQL(sqlCriaTabela);

                String sqlInsere = "INSERT INTO Alunos_novo (id, nome, endereco, telefone, site, nota, caminhoFoto) " +
                        "SELECT id, nome, endereco, telefone, site, nota, caminhoFoto FROM Alunos";
                db.execSQL(sqlInsere);

                String sqlApagaTabela = "DROP TABLE Alunos";
                db.execSQL(sqlApagaTabela);

                String sqlRenomeia = "ALTER TABLE Alunos_novo RENAME TO Alunos";
                db.execSQL(sqlRenomeia);
        }
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAluno(aluno);

        long id = db.insert("Alunos", null, dados);
        aluno.setId(id);
    }

    @NonNull
    private ContentValues pegaDadosAluno(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());
        dados.put("caminhoFoto", aluno.getCaminhoFoto());

        return dados;
    }

    public List<Aluno>  buscaAlunos() {
        String sql = "SELECT id, nome, endereco, telefone, site, nota, caminhoFoto FROM Alunos";
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
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));

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
