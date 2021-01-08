package com.company;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebScraping {
    // Classe simples para pegar preços das Ações
    // Pega o código fonte do link que é a pesquisa no google do preço da ação e retira o preço desse código

    public static String pull(String url_google) throws IOException{
        // Instancia objeto URL com a url da pesquisa do preço da ação no google (url da ação guardada em Enum Acoes)
        URL url = new URL(url_google);
        URLConnection urlCon = url.openConnection();
        urlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)"); // Evita erros de conexão
        InputStreamReader inStream = new InputStreamReader(urlCon.getInputStream());
        BufferedReader buff = new BufferedReader(inStream); // Texto do código-fonte da página

        String preco; // Inicializa a String
        String linha = buff.readLine(); // Transforma a linha do código em String
        while(linha != null){ // Percorre todas as linhas do código-fonte
            String precedePreco = "<span class=\"qXLe6d epoveb\">  <span class=\"fYyStc\">"; // Pedaço de código-fonte que sempre antecede o preço da ação

            if(linha.contains(precedePreco)){ // Encontrou a linha que contém o valor do preço
                int alvo = linha.indexOf(precedePreco);
                int inicio = alvo + precedePreco.length(); // Encontra o índice do valor do preço
                int fim = inicio;

                while(linha.charAt(fim) != '<'){
                    // Encontra final do preço
                    fim++;
                }

                preco = linha.substring(inicio, fim); // Retira valor do preço da linha de código-fonte

                return preco;
            }
            linha = buff.readLine(); // Lê a próxima linha do código-fonte
        }
        return "Preço não encontrado!"; // Não detectou precePreco em nenhuma linha do código-fonte
    }
}
