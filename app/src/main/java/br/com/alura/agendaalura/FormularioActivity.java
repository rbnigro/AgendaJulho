package br.com.alura.agendaalura;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.alura.agendaalura.dao.AlunoDAO;
import br.com.alura.agendaalura.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:

              Aluno aluno = helper.getAluno();

              AlunoDAO alunoDAO = new AlunoDAO(this);
              alunoDAO.insere(aluno);
              alunoDAO.close();

              Toast.makeText(FormularioActivity.this,  aluno.getNome() + " Salvo", Toast.LENGTH_SHORT).show();

              finish();
              break;
        }

        return super.onOptionsItemSelected(item);
    }
}