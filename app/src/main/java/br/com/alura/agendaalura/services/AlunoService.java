package br.com.alura.agendaalura.services;

import java.util.List;

import br.com.alura.agendaalura.dto.AlunoSync;
import br.com.alura.agendaalura.modelo.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlunoService {

    @POST("aluno")
    Call<Void> insere(@Body Aluno aluno);

    @GET("aluno")
    Call<AlunoSync> lista();
}
