package br.com.alura.agendaalura;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    private String endereco;

    public void insere(String json) {
        endereco = "http://10.0.5.120:8080/api/aluno";
        realizaConexao(json, endereco);
    }

    public String post(String json)  {

        endereco = "https://www.caelum.com.br/mobile";
        return realizaConexao(json, endereco);
    }

    @Nullable
    private String realizaConexao(String json, String endereco) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-type", "application/json"); // envia
            connection.setRequestProperty("Accept", "application/json"); // recebe
            connection.setDoOutput(true); // escreve na saida padrao método post

            PrintStream output = new PrintStream(connection.getOutputStream()); // escreve
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.next();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
