package br.com.alura.agendaalura;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agendaalura.converter.AlunoConverter;
import br.com.alura.agendaalura.dao.AlunoDAO;
import br.com.alura.agendaalura.modelo.Aluno;

//   parametros                           entrada processamento retorno
public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private ProgressDialog progressDialog;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() { // antes da execução na thread principal
        progressDialog = ProgressDialog.show(context,"Aguarde", "Enviando alunos...", true, true);
    }

    @Override
    protected String doInBackground(Object... params) { // thread secundaria

        AlunoDAO alunoDAO = new AlunoDAO(context);
        List<Aluno> alunos = alunoDAO.buscaAlunos();
        alunoDAO.close();

        AlunoConverter conversor = new AlunoConverter();
        String json = conversor.converterParaJSON(alunos);

        WebClient webClient = new WebClient();
        String resposta = webClient.post(json);

        return resposta; // envia para onPostExecute que será na thread principal
    }

    @Override
    protected void onPostExecute(String resposta) { // apos execução na thread principal
        progressDialog.dismiss();

        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
    }
}
