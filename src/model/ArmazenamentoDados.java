/**
 * Componente Curricular: Módulo Integrado de Concorrência e Conectividade
 * Autor: Estéfane Carmo de Souza
 * Data: /09/2021
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor,
 * tais como provindos de livros e apostilas, e páginas ou documentos
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;


public class ArmazenamentoDados {
    
    
    public String read(String nomeArquivo) throws IOException{
        BufferedReader ler = null;
        String leitura="";
        if(!this.isEmpty(nomeArquivo)){
            try{
                ler = new BufferedReader(new FileReader("Dados/"+nomeArquivo));
                String linha = ler.readLine();
                while(linha!=null){
                    leitura+=linha+"\n";
                    linha= ler.readLine();
                }       
            }catch (FileNotFoundException exception) {
                leitura= "Arquivo não encontrado";
                throw new IOException();
            } finally {
                if (ler != null) {
                    ler.close(); //fecha o arquivo
                }
            }
        }
        return leitura;
    }
        
    public String write(String nomeArquivo, String informacao){
        BufferedWriter escrever = null;
        try{
            escrever= new BufferedWriter (new FileWriter("Dados/"+nomeArquivo));
            escrever.write(informacao);
            escrever.close();
            return "Escrita efetivada";
        }catch(IOException e){
            return e.getMessage();
        }    
    }
    public boolean isEmpty(String nomeArquivo) throws IOException{
        try{
            BufferedReader ler= new BufferedReader(new FileReader("Dados/"+nomeArquivo));
            return ler.readLine()==null;   
        }catch(IOException e){
           throw new IOException();
        }
    }

}