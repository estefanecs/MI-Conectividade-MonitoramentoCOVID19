

/**
 * Componente Curricular: Módulo Integrado de Concorrência e Conectividade
 * Autor: Messias Júnior Lira da Silva
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


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Messias Júnior Lira da Silva
 */
public class ControladorComunicacao implements Runnable{
    
    private static ControladorComunicacao instancia;
    private Socket cliente;
    private String ip;
    private int porta=0;
    private BufferedWriter escritor;
    private BufferedReader leitor;
    private LinkedList<String> respostas = new LinkedList();

    public ControladorComunicacao(){
    }
    /**
     * Método que retorna a única instancia do ControladorComunicacao. Caso não exista, cria o mesmo.
     * @return 
     */
    public static synchronized ControladorComunicacao getInstance() {
        if (instancia == null) {
            instancia= new ControladorComunicacao();
        }
        return instancia;
    }

    /**
     * Método que retorna o cliente
     * @return 
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Metodo que altera o cliente
     * @param cliente 
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo que retorna o IP
     * @return 
     */
    public String getIp() {
        return ip;
    }

    /**
     * Metodo que altera o IP
     * @param ip 
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Metodo que retorna a porta
     * @return 
     */
    public int getPorta() {
        return porta;
    }

    /**
     * Metodo que altera a porta
     * @param porta 
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }
    
    /**
     * 
     * @return 
     */
    public synchronized LinkedList getResposta(){
        return this.respostas;
    }
    
    /**
     *Método para conectar o cliente ao servidor. 
     * @param ip
     * @param porta
     * @throws java.io.IOException
     */
    public void conexao(String ip, int porta) throws IOException{
        this.setIp(ip);
        this.setPorta(porta);
        this.cliente = new Socket(ip, porta);
        escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
        leitor = new BufferedReader (new InputStreamReader(cliente.getInputStream())); 
        System.out.println("O cliente se conectou ao servidor!");
        Thread t = new Thread(this);
      
    }
    
    /**
     * Método responsável por fechar a conexão com o servidor. 
     * @throws java.io.IOException
     */
    public void fecharConexão() throws IOException{
        cliente.close();
    }
    
    /**
     * Metodo que envia dados para o servidor
     * @param dados 
     */
    public void enviaDados(String dados){
        
        try {
            this.conexao(ip, porta);//conecta com o servidor
            
            //envia dados
            escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            escritor.write(dados);
            escritor.flush();
            this.fecharConexão();//fecha conexao
            System.out.println("Fechou a conexão");
        } catch (IOException ex) {
            Logger.getLogger(ControladorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Enviou dados");
    }
    
    /**
     * Metodo que solicita dados do servidor
     * @param dados 
     */
  /*  public void solicitaDados(String dados){
        try {
            this.conexao(ip, porta);//conecta com o servidor
            
            //envia solicitaca
            escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            escritor.write(dados);
            escritor.flush();
            
            //aguarda resposta
            String resposta = null;
            while (resposta == null) {
                resposta = leitor.readLine();
            }
            this.trataResposta(resposta);//trata resposta
            leitor.close();//fecha leitor
            this.fecharConexão();//fecha conexao
            System.out.println("Fechou a conexão");
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ControladorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Solicita dados da corrida/qualificacao do servidor. Esse metodo nao fecha a conexao
     * ja que recebe os dados da corrida até o fim
     * @param dados 
     */
  /*  public void solicitaDadosVolta(String dados){
        try {
            this.conexao(ip, porta);//conecta com o srvidor
            
            //envia splicitacao
            escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            escritor.write(dados);
            escritor.flush();
            
            //inicia a thread que recebe respostas do servidor
            Thread t = new Thread(this);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(ControladorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    

    /**
     * Metodo que trata a resposta e envia para a acao especifica, de acordo com
     * o caminho e a acao enviada
     * @param resposta
     * @throws ParseException
     * @throws IOException 
     */
  /*  public void trataResposta(String resposta) throws ParseException, IOException{
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(resposta);
        if(json.containsValue("PUT::configurar/carro")){//se o caminho for configurar/carro
            ControladorConfig configurador = ControladorConfig.getInstance();
            configurador.getFilaDeTags().add((String) json.get("TAG"));//adiciona o objeto na fila de tags do configurador
        } else if(json.containsValue("PUT::atualizar/corrrida")){//se o caminho for atualizar/corrrida
            ControladorCorrida corrida = ControladorCorrida.getInstance();
            corrida.getFilaDeLeituras().add(json);//;//adiciona o objeto na fila de leituras da corrida
        }
    }

    /**
     * Metodo que envia os dados do leitor para o servidor
     * @param dados 
     */
  /*  public void writeLeitor(String dados){
        JSONObject leitorJson = new JSONObject();
        String[] conteudo = dados.split(";");
        //altera o ip e porta
        this.setIp(conteudo[0]);
        this.setPorta(Integer.parseInt(conteudo[1]));
        //configura JSON
        leitorJson.put("ip", conteudo[0]);
        leitorJson.put("porta_rede", conteudo[1]);
        leitorJson.put("porta_serial", conteudo[2]);
        leitorJson.put("baud", Integer.parseInt(conteudo[3]));
        leitorJson.put("potencia", Integer.parseInt(conteudo[4]));
        leitorJson.put("regiao", conteudo[5]);
        
        this.enviaDados("POST::configurar/leitor::" + leitorJson.toString());//envia os dados
    }
    
    @Override
    public void run() {
        String resposta = null;
        while (true) {//Fica sempre esperando uma resposta vinda do servidor
            try {
                resposta = leitor.readLine();  //recebe mensagem
                if(resposta != null){//se a resposta nao for null
                    System.out.println("Recebido: "+ resposta);
                    this.trataResposta(resposta);//trata a resposta
                }
                resposta = null;
            } catch (IOException | ParseException ex) {
                Logger.getLogger(ControladorComunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
}
