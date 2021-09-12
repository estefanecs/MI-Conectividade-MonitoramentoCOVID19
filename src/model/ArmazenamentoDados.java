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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FilaPaciente;


public class ArmazenamentoDados {
    
    
    public ArrayList<String> read(String nomeArquivo) throws IOException{
        BufferedReader ler = null;
        ArrayList<String> leitura = new ArrayList();
        try{
            int i=0;
            ler = new BufferedReader(new FileReader("Dados/"+nomeArquivo));
            String linha = ler.readLine();
            System.out.println("linha = "+linha);
            while(linha!=null){
               System.out.println(i+" linha = "+linha);
                leitura.add(linha);
                i++;
                linha= ler.readLine();
            }  
            ler.close();
        }catch (FileNotFoundException exception) {
            throw new IOException();
        }
        return leitura;
    }

    public void write(String nomeArquivo, String informacao){
        BufferedWriter escrever = null;
        try{
            escrever= new BufferedWriter (new FileWriter("Dados/"+nomeArquivo,true));
            if(!this.isEmpty(nomeArquivo)){
                escrever.newLine();
            }
            escrever.append(informacao);
            escrever.close();
        } catch (IOException ex) {
            Logger.getLogger(ArmazenamentoDados.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void write(String nomeArquivo,FilaPaciente pacientes){
        BufferedWriter escrever = null;
        try{
            escrever= new BufferedWriter (new FileWriter("Dados/"+nomeArquivo,false));
            int count=0;
            if(!pacientes.isEmpty()){
                Paciente paciente = pacientes.get(0);
                escrever.append(paciente.getNome()+":"+paciente.getCpf());
            }
            count=1;
            while(count<pacientes.size()){
                escrever.newLine();
                Paciente paciente = pacientes.get(count);
                escrever.append(paciente.getNome()+":"+paciente.getCpf());
                count++;
                System.out.println("count = "+count+ "size = "+pacientes.size());
            }
            escrever.close();
        }catch (IOException ex) {
            Logger.getLogger(ArmazenamentoDados.class.getName()).log(Level.SEVERE, null, ex);
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