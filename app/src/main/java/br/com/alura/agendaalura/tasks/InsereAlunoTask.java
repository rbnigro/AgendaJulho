package br.com.alura.agendaalura.tasks;

import android.os.AsyncTask;

import br.com.alura.agendaalura.WebClient;
import br.com.alura.agendaalura.converter.AlunoConverter;
import br.com.alura.agendaalura.modelo.Aluno;

public class InsereAlunoTask extends AsyncTask {

    private final Aluno aluno;

    public InsereAlunoTask(Aluno aluno) {

        this.aluno = aluno;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String json = new AlunoConverter().converterParaJSONCompleto(aluno);
        new WebClient().insere(json);
        return null;
    }
}
