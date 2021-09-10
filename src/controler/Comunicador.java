/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Paciente;
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
        Thread t = new Thread(this);
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
          
     public void analisaDados(String dados) throws JSONException{
        System.out.println("C- irei analisar os dados");
      
        JSONObject dadoRecebido= new JSONObject(dados);
        String metodo= dadoRecebido.getString("Metodo");
        String dado= dadoRecebido.getString("Dado");
        System.out.println("metodo " +metodo+" e dado "+ dado);
        
        if(metodo.contains("POST/cadastrarPaciente")){
            System.out.println("C- entrei em cadastrarPaciente");
            String[] informacao= dado.split(":");
            ControladorInterface controlador= ControladorInterface.getInstancia();
            controlador.cadastrarPaciente(informacao[0],informacao[1]);
            System.out.println("paciente add: "+controlador.getPacientes().get(0).getNome());
        }
        else if(metodo.contains("DELETE/removerPaciente")){
            System.out.println("C- entrei no delete");
            ControladorInterface controlador= ControladorInterface.getInstancia();
            Paciente p= controlador.removerPaciente(dado);
            if(p!=null){
                System.out.println("paciente removido: "+p.getNome());
               
            }
            
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
     
    public String getDados(String requisicao) throws IOException, JSONException, ClassNotFoundException{
        this.estabelecerConexao(/*porta, ip*/); //estabelece a conexão com o servidor
        //Envia a requisão do dado que deseja receber
        
        System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
        escritor.writeUTF(requisicao);
        escritor.flush();
        System.out.println("C- Enviou dados");
        //Recebe o dado enviado pelo servidor.
        String dados = leitor.readUTF();
        System.out.println("c- recebi os dados: "+dados);
        if(!dados.equals("nula")){
          this.analisaDados(dados); 
        }   
        //Fecha o buffer de escrita e leitura
        escritor.close();
        leitor.close();
        //encerra a conexão com o servidor
        this.encerrarConexao();
        System.out.println("C- Fechou a conexão"); 
        return dados;
     }
     
  /*  public static void main(String[] args) {
        try {
            Comunicador cliente= new Comunicador();
            cliente.estabelecerConexao();
            System.out.println("Comunicador conectado: " + cliente.cliente.getInetAddress().getHostAddress());
            cliente.escritor.writeUTF("GET/cadastrarPaciente");
            cliente.escritor.flush();
            System.out.println("C- Enviou dados");
            //Recebe o dado enviado pelo servidor.
            String dados= cliente.leitor.readUTF();
            System.out.println("c- recebi os dados: "+dados);
            cliente.analisaDados(dados);
            
            cliente.escritor.close();
            cliente.leitor.close();
            cliente.encerrarConexao();
       
        }
        catch(Exception e) {
          System.out.println("Erro: " + e.getMessage());
        }

    }*/

    @Override
    public void run() {
        String dadosRecebidos = null;
        while (true) {//Fica sempre esperando dados do servidor
            try {
                dadosRecebidos = leitor.readUTF(); //recebe o que foi enviado
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
