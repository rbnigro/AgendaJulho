package br.com.alura.agendaalura.services;

import br.com.alura.agendaalura.modelo.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AlunoService {

    @POST("aluno")
    Call insere(@Body Aluno aluno);
}
