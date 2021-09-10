/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author casa
 */
public class Comunicador implements Runnable {

    private static Comunicador instancia;
    private ObjectInputStream leitor;
    private ObjectOutputStream escritor;
    private Socket cliente;
    private String ip="localhost";
    private int porta=5023;

    public Comunicador() {
    }
    
    public static synchronized Comunicador getInstancia(){
        if(instancia == null){
            instancia = new Comunicador();
        }
        return instancia;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    
    public void estabelecerConexao(/*int porta, String ip*/) throws IOException{
        /*this.porta= porta;
        this.ip=ip;*/
        cliente= new Socket(this.ip,this.porta);
        escritor= new ObjectOutputStream((cliente.getOutputStream()));
        leitor= new ObjectInputStream((cliente.getInputStream()));
        System.out.println("C- aconteceu a conexão");
        Thread conectado = new Thread(this);
    
    }
    public void encerrarConexao() throws IOException{
        cliente.close();
    }
    
     public void postDados(String dados){
        try {
            this.estabelecerConexao(/*porta, ip*/);//estabelece a conexão com o servidor
            //Envia os dados para o servidor
            escritor.writeUTF(dados);
            escritor.flush();
            this.encerrarConexao();//fecha conexao
            escritor.close();
            System.out.println("Fechou a conexão");
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Os dados foram enviados");
    }
     
    public void getDados(String requisicao) throws IOException, JSONException, ClassNotFoundException{
        this.estabelecerConexao(/*porta, ip*/); //estabelece a conexão com o servidor
        //Envia a requisão do dado que deseja receber
        escritor.writeUTF(requisicao);
        escritor.flush();
        System.out.println("C- Enviou dados");
        //Recebe o dado enviado pelo servidor.
        String leitura = leitor.readUTF();
      /*  while(leitura==null){ //permanece no loop até o dado ser recebido
             leitura= leitor.readLine();
              System.out.println("S- Recebi: " +leitura);
         }*/
        System.out.println("c- recebi os dados: "+leitura);
        this.analisaDados(leitura); 
        //Fecha o buffer de escrita e leitura
        leitor.close();
        escritor.close();
        //encerra a conexão com o servidor
        this.encerrarConexao();
        System.out.println("C- Fechou a conexão"); 
     }
     
     public void analisaDados(String dados) throws JSONException{
        System.out.println("S- irei analisar os dados");
        
        JSONObject dadoRecebido= new JSONObject(dados);
        String metodo= dadoRecebido.getString("Metodo");
        String dado= dadoRecebido.getString("Dado");
        System.out.println("metodo " +metodo+" e dado "+ dado);
        
        if(metodo.contains("POST/cadastrarPaciente")){
            System.out.println("S- entrei em cadastrarPaciente");
            String[] informacao= dado.split(":");
            ControladorInterface controlador= ControladorInterface.getInstancia();
            controlador.cadastrarPaciente(informacao[0],informacao[1]);
        }
        else if(metodo.contains("DELETE/removerPaciente")){
            System.out.println("S- entrei no delete");
            ControladorInterface controlador= ControladorInterface.getInstancia();
            controlador.removerPaciente(dado);
        }
        else if(dadoRecebido.getString("Metodo").contains("PUT/atualizarSinais")){
             String[] informacao= dado.split(":");
             String nome = informacao[0];
             double temperatura = Double.parseDouble(informacao[1]);
             float freqCardiaca = Float.parseFloat(informacao[2]);
             float freqRespiratoria = Float.parseFloat(informacao[3]);
             float pressao = Float.parseFloat(informacao[4]);
             float saturacao = Float.parseFloat(informacao[5]);
             ControladorInterface controlador= ControladorInterface.getInstancia();
             controlador.atualizarDados(nome,temperatura,freqCardiaca,freqRespiratoria,pressao,saturacao);
                     
         }
             
     }
     
    @Override
    public void run() {
       String dadosRecebidos = null;
        while (true) {//Fica sempre esperando dados do servidor
            try {
                dadosRecebidos = leitor.readLine(); //recebe o que foi enviado
                if(dadosRecebidos != null){//se a string não for nula
                    System.out.println("C- Recebido do servidor: "+ dadosRecebidos);
                    this.analisaDados(dadosRecebidos);//analisa o tipo de dado recebido
                }
                dadosRecebidos = null;
            } catch (IOException ex) {
                Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
               Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        
    }
    
}


         //Metodo pra tratar leitura...///
         /*String[] dados= leitura.split("/");
         System.out.println("C- vetor 1: " +dados[0]+"vetor 2: "+dados[1]);
         /*String informcao =dados[2];
         String[] informacao= informcao.split(":");
         ControladorInterface controlador= ControladorInterface.getInstancia();
         controlador.cadastrarPaciente(informacao[0],informacao[1]);*/