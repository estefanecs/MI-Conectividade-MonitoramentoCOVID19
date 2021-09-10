/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author casa
 */
public class Servidor implements Runnable {
    private ServerSocket servidor;
    private Thread t;
    private ControladorPaciente controlador;
    /*private BufferedReader leitor;
    private BufferedWriter escritor;*/
    private ObjectOutputStream escritor;
    private ObjectInputStream leitor;

    public Servidor() throws IOException {
        this.servidor = new ServerSocket(5023);
        System.out.println("servidor criado");
        t = new Thread(this);
        t.start();
    }
    
    public void cadastrar() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
       //Cria um objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: metodos e dado
        dado.put("Metodo", "POST/cadastrarPaciente");
        dado.put("Dado", controlador.getListaCadastrar().getFirst()+"\n");
        String jsonString = dado.toString ();
        //envia os dados para o cliente
        escritor.writeUTF(jsonString);
        escritor.flush();
        
        System.out.println("S- envio de dados concluida");
        //retira da lista o dado que foi enviado para cadastro
        controlador.getListaCadastrar().removeFirst();
        System.out.println("Lista de cadastro is empty? "+controlador.getListaCadastrar().isEmpty());
    }
    
    public void remover() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
        //Cria um objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: metodos e dado
        dado.put("Metodo", "DELETE/removerPaciente");
        dado.put("Dado", controlador.getListaRemover().getFirst());
        String resposta = dado.toString ();
        System.out.println("dado da resposta "+resposta);
        //envia os dados para o cliente

        escritor.writeUTF(resposta);
        escritor.flush();
        escritor.close();
        System.out.println("S- enviado dado");
        //Remove da lista o dado que foi enviado para remocao
        controlador.getListaRemover().removeFirst();
        System.out.println("Lista de remover is empty? "+controlador.getListaRemover().isEmpty());
    }
    
    public void atualizar() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
        //Cria um objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: metodos e dado
        dado.put("Metodo", "PUT/atualizarSinais");
        dado.put("Dado", controlador.getAtualizacoes().getFirst());
        String resposta = dado.toString ();
        System.out.println("dado da resposta "+resposta);
        //envia os dados para o cliente
        escritor.writeUTF(resposta);
        escritor.flush();
        escritor.close();
        System.out.println("S- enviado dado");
        //Remove da lista o dado que foi enviado
        controlador.getAtualizacoes().removeFirst();
    }
    
    
    
    @Override
    public void run() {
        try {
                this.servidor = new ServerSocket(5023);
        System.out.println("servidor criado");
                //conecta com cliente
                Socket cliente= servidor.accept();
                System.out.println("S- ouvindo "+servidor.getLocalPort()+ " Cliente: "+cliente.getInetAddress() );
                //cria o leitor e escritor para o envio e recebimento de dados
                /*escritor= new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
                leitor= new BufferedReader(new InputStreamReader(cliente.getInputStream()));*/
                escritor= new ObjectOutputStream((cliente.getOutputStream()));
                leitor= new ObjectInputStream((cliente.getInputStream()));
                        
                        
                //Ler a requisicao enviada pelo cliente
                String requisicao = leitor.readUTF();
                System.out.println("S- recebido do cliente "+ requisicao);
                escritor.writeUTF("Hello World");
                escritor.flush();
                /*leitor.close();
                //separa a requisicao para analisar o método e a ação a ser realizada
                String[] dados= requisicao.split("/");
                System.out.println("vetor 1 " +dados[0] +" vetor 2 "+dados[1]);
                controlador = ControladorPaciente.getInstancia();
                if(dados[0].equals("GET")){
                    System.out.println("s- entrei no get");
                    
                    if(dados[1].equals("cadastrarPaciente")){
                        this.cadastrar();
                    }
                    
                    else if(dados[1].equals("removerPaciente")){
                        this.remover();
                    }
                    else if(dados[1].equals("GET/atualizarSinais")){
                        this.atualizar();
                    }
                }
                /*else if (dados[0].equals("POST")){
                }
                cliente.close();*/
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }/* catch (JSONException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
}
//***************************cadastro

       /* String respostaEnviada = "POST/cadastrarPaciente/"+ controlador.getListaCadastrar().getFirst();
        System.out.println("dado da resposta "+respostaEnviada);
        escritor.write(respostaEnviada);
        escritor.flush();
        System.out.println("S- enviado dado");
        controlador.getListaCadastrar().removeFirst();
        System.out.println("Lista de cadastro is empty? "+controlador.getListaCadastrar().isEmpty());
 
        JSONObject dado2= new JSONObject(jsonString);
        String metodo= dado2.getString("Metodo");
        String dado1= dado2.getString("Dado");
        System.out.println("metodo " +metodo+" e dado "+ dado1);*/
        
        /* dado={"ACAO":"PUT::configurar/carro", "TAG":str(reader.read()[0])}
    objeto = json.dumps(dado)
    cliente.sendall(bytes(objeto.encode('utf8')))
    print("Enviado")*/


        /* REMOVER ------------------------------------------------------------------
        String resposta = "DELETE/removerPaciente/"+ controlador.getListaRemover().getFirst();
        System.out.println("dado da resposta "+resposta);
        escritor.write(resposta);
        escritor.flush();
        System.out.println("S- enviado dado");
        controlador.getListaRemover().removeFirst();
        System.out.println("Lista de remover is empty? "+controlador.getListaRemover().isEmpty());*/