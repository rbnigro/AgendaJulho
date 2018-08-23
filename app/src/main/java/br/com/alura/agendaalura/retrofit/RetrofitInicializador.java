package br.com.alura.agendaalura.retrofit;

import br.com.alura.agendaalura.services.AlunoService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador() {

        // com.squareup.retrofit2:converter-jackson

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.5.120:8080/api/").addConverterFactory(JacksonConverterFactory.create()).build();
    }

    public AlunoService getAlunoService() {
        return retrofit.create(AlunoService.class);
    }
}
