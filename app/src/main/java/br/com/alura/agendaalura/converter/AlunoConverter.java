package br.com.alura.agendaalura.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.agendaalura.modelo.Aluno;

public class AlunoConverter {

    public String converterParaJSON(List<Aluno> alunos) {
            JSONStringer js = new JSONStringer();

            try {
                //    {         chsve     [          chave        [
                js.object().key("list").array().object().key("aluno").array();
                for (Aluno aluno : alunos) {
                    //   {
                    js.object();
                    //     chave         valor
                    js.key("nome").value(aluno.getNome());
                    js.key("nota").value(aluno.getNota());
                    js.endObject();
                    //     }
                }
                js.endArray().endObject().endArray().endObject();
                //    ]           }           ]          }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return js.toString();
    }

    public String converterParaJSONCompleto(Aluno aluno) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("list").array().object().key("aluno").array();
            //for (Aluno aluno : alunos) {
                //   {
                js.object();
                //     chave         valor
                js.key("nome").value(aluno.getNome());
                js.key("endereco").value(aluno.getEndereco());
                js.key("site").value(aluno.getSite());
                js.key("telefone").value(aluno.getTelefone());
                js.key("nota").value(aluno.getNota());
                js.endObject();
                //     }
            //}
            js.endArray().endObject().endArray().endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
