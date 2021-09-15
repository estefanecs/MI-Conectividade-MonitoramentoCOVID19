/**
 * Componente Curricular: Módulo Integrado de Concorrência e Conectividade
 * Autor: Estéfane Carmo de Souza
 * Data: 13/09/2021
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FilaPaciente;

/**
 * Classe para realizar a leitura e escrita em arquivo
 * 
 * Exemplo de uso:
 * 
 * ArmazenamentoDados arquivo= new ArmazenamentoDados();
 */
public class ArmazenamentoDados {
    
    /**
     * Método para a leitura de arquivo, que salva os dados lidos em uma lista
     * e retorna a mesma.
     * @param nomeArquivo - nome do arquivo a ser lido
     * @return ArrayList - lista com as informacoes lidas no arquivo
     * @throws IOException 
     */
    public ArrayList<String> read(String nomeArquivo) throws IOException{
        BufferedReader ler = null;
        ArrayList<String> leitura = new ArrayList();
        try{
            //Cria o buffer de leitura
            ler = new BufferedReader(new FileReader("Dados/"+nomeArquivo));
            //Ler a primeira linha
            String linha = ler.readLine();
            while(linha!=null){ //Ler até encontrar uma linha em branco
                leitura.add(linha); //Adiciona a linha lida na lista
                linha= ler.readLine(); //Vai para a próxima linha
            }  
            ler.close(); //Fecha o buffer de leitura
        }catch (FileNotFoundException exception) {
            throw new IOException();
        }
        return leitura; //Retorna a  lista com as leituras
    }

    /**
     * Método para a escrita em arquivo, que escreve uma linha por vez
     * @param nomeArquivo - nome do arquivo que realizará a escrita
     * @param informacao - informacao que será escrita
     */
    public void write(String nomeArquivo, String informacao){
        BufferedWriter escrever = null;
        try{
            //Cria o buffer de escrita
            escrever= new BufferedWriter (new FileWriter("Dados/"+nomeArquivo,true));
            if(!this.isEmpty(nomeArquivo)){//Se o arquivo não estiver vazio
                escrever.newLine(); //Salta uma linha, para poder escrever na proxima linha
            }
            escrever.append(informacao); //Escreve a informacao
            escrever.close(); //Fecha o buffer de escrita
        } catch (IOException ex) {
            Logger.getLogger(ArmazenamentoDados.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Método para a escrita de todos os pacientes cadastrados na fila, de uma
     * única vez. Sempre apaga o arquivo existente e cria um novo para a escrita.
     * @param nomeArquivo - nome do arquivo que realizará a escrita
     * @param pacientes - fila de pacientes
     */
    public void write(String nomeArquivo,FilaPaciente pacientes){
        BufferedWriter escrever = null;
        try{
            //Cria o buffer de escrita
            escrever= new BufferedWriter (new FileWriter("Dados/"+nomeArquivo,false));
            //int count=0;
            if(!pacientes.isEmpty()){ //Se a fila de pacientes não estiver vazia
                Paciente paciente = pacientes.get(0); //Salva o primeiro paciente
                String dado =paciente.getNome()+":"+paciente.getCpf(); //Concatena o nome e o cpf do paciente
                dado= dado.replace ("\n", ""); //remove \n se existir
                escrever.append(dado);//Escreve no arquivo
            }
            int count=1; //Variável para controle do loop
            while(count<pacientes.size()){ //Até chegar ao fim da fila
                escrever.newLine();//Vai para a proxima linha do arquivo
                Paciente paciente = pacientes.get(count); //Salva o paciente
                String dado =paciente.getNome()+":"+paciente.getCpf(); //Concatena o nome e o cpf do paciente
                dado= dado.replace ("\n", ""); //remove \n se existir
                escrever.append(dado);//Escreve no arquivo
                count++;//Incrementa a variável
            }
            escrever.close();//Fecha o buffer de escrita
        }catch (IOException ex) {
            Logger.getLogger(ArmazenamentoDados.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * Método que verifica se o arquivo está vazio. Retorna verdade se estiver
     * vazio e falso caso não esteja.
     * @param nomeArquivo - nome do arquivo
     * @return true - se o arquivo estiver vazio
     * @throws IOException 
     */
    public boolean isEmpty(String nomeArquivo) throws IOException{
        try{
            //Cria o buffer de leitura
            BufferedReader ler= new BufferedReader(new FileReader("Dados/"+nomeArquivo));
            return ler.readLine()==null;   //Ler a primeira linha e retorna o booleano se ela está vazia
        }catch(IOException e){
           throw new IOException();
        }
    }
    
}