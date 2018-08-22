package br.com.alura.agendaalura;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClient {

    public String post(String json)  {
        String servidor = "https://www.caelum.com.br/mobile";
        try {
            URL url = new URL(servidor);
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