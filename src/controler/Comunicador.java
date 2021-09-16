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
 * Classe que realiza a comunicação com o servidor
 * 
 * Exemplo de uso:
 * 
 * Comunicador comunicador = Comunicador.getInstancia();
 */
public class Comunicador implements Runnable {
    private static Comunicador instancia; //Instancia da classe
    private ObjectInputStream leitor; //Buffer de leitura
    private ObjectOutputStream escritor; //Buffer de escrita
    private Socket cliente; //socket para a comunicação
    private String ip="localhost"; //É necessário alterar esse campo para o ip do servidor
    private int porta=5023; //É necessário alterar esse campo para a porta em que o servidor está
    
    
    /**
     * Método construtor da classe
     */
    public Comunicador() {
    }
    
    /**
     * Método que retorna a única instancia do Comunicador. Caso não exista, cria a
     * mesma.
     * @return Comunicador- a instância do comunicador
     */
    public static synchronized Comunicador getInstancia(){
        if(instancia == null){
            instancia = new Comunicador();
        }
        return instancia;
    }

    /**
     * Método que retorna o socket do cliente
     * @return Socket
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Método que altera o socket do cliente
     * @param cliente - novo socket
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Método que retorna o Ip da comunicação
     * @return String - o ip
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * Método que altera o ip da comunicação
     * @param ip - novo ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    /**
     * Método que retorna a porta da comunicação
     * @return porta - a porta
     */
    public int getPorta() {
        return porta;
    }

    /**
     * Método que altera a porta da comunicação
     * @param porta - nova porta
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }
    
    /**
     * Método para realizar a conexão entre o comunicador e o servidor
     * @throws IOException 
     */
    
    public void estabelecerConexao() throws IOException{
        cliente= new Socket(this.ip,this.porta); //cria o socket do cliente
        //Cria o buffer de escrita e leitura para conversar com o servidor
        escritor= new ObjectOutputStream((cliente.getOutputStream()));
        leitor= new ObjectInputStream((cliente.getInputStream()));
        System.out.println("\n\n****************C- aconteceu a conexão******************");
        Thread t = new Thread(this); //cria a thread
    }
     public void encerrarConexao() throws IOException{
        cliente.close();
    }
    
    /**
     * Método para enviar dados para o servidor
     * @param dados 
     */
    public void postDados(String dados){
        try {
            this.estabelecerConexao();//estabelece a conexão com o servidor
            //Envia os dados para o servidor
            escritor.writeUTF(dados);
            escritor.flush();
            System.out.println("Os dados foram enviados");
            this.encerrarConexao();//fecha conexao
            escritor.close(); //fecha o buffer de escrita
            System.out.println("Fechou a conexão");
        } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método que analisa o tipo de dados recebido, com base no método da requisição
     * e executa uma determinada ação.
     * @param dados - string com os dados recebidos
     * @throws JSONException 
     */
    public void analisaDados(String dados) throws JSONException{
        //Transforma a string recebida para JSON
        JSONObject dadoRecebido= new JSONObject(dados);
        String metodo= dadoRecebido.getString("Metodo"); //obtém o método
        String dado= dadoRecebido.getString("Dado"); //obtém o dado enviado
        
        //Se o método da requisição foi cadastrar pacientes
        if(metodo.contains("POST/cadastrarPaciente")){
            String[] informacao= dado.split(":"); //Separa a string de dados
            ControladorInterface controlador= ControladorInterface.getInstancia(); //obtém a instância do controlador
            controlador.cadastrarPaciente(informacao[0],informacao[1]);//cadastra o paciente, com o nome e cpf enviado
        }
        //Se o método da requisição foi remover paciente
        else if(metodo.contains("DELETE/removerPaciente")){
            ControladorInterface controlador= ControladorInterface.getInstancia(); //obtém a instancia do controlador
            controlador.removerPaciente(dado); //remove o paciente, com o nome enviado
        }
        //Se o método da requisição foi atualizar sinais
        else if(dadoRecebido.getString("Metodo").contains("PUT/atualizarSinais")){
             String[] informacao= dado.split(":"); //separa a string de dados
             String nome = informacao[0]; //nome do paciente
             double temperatura = Double.parseDouble(informacao[1]); //temperatura do paciente
             float freqCardiaca = Float.parseFloat(informacao[2]); //frequencia cardiaca do paciente
             float freqRespiratoria = Float.parseFloat(informacao[3]); //frequencia respiratoria do paciente
             float pressao = Float.parseFloat(informacao[4]); //pressao do paciente
             float saturacao = Float.parseFloat(informacao[5]); //saturação do oxigênio do paciente
             ControladorInterface controlador= ControladorInterface.getInstancia();//obtém a instância do controlador
             //Envia os dados de cada sinal vital do paciente recebido, para ser atualizado pelo controlador
             controlador.atualizarDados(nome,temperatura,freqCardiaca,freqRespiratoria,pressao,saturacao);
                     
         }     
     }
     
    /**
     * Método para o recebimento e dados do servidor. Faz uma solicitação ao servidor
     * e este devolve o dado que foi pedido.
     * @param requisicao - requisição a ser enviada para o servidor
     * @return dado - o dado recebido
     * @throws IOException
     * @throws JSONException
     * @throws ClassNotFoundException 
     */
    public String getDados(String requisicao) throws IOException, JSONException, ClassNotFoundException{
        this.estabelecerConexao(/*porta, ip*/); //estabelece a conexão com o servidor   
        System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
        //Envia a requisão do dado que deseja receber
        escritor.writeUTF(requisicao);
        escritor.flush();
        System.out.println("C- Enviou dados");
        //Recebe o dado enviado pelo servidor.
        String dados = null;
        while(dados==null){ //Aguarda até que uma resposta pelo servidor seja enviada
            dados=leitor.readUTF();
        }        
        System.out.println("C- recebi os dados: "+dados);
        if(!dados.equals("nula")){ //Se recebeu dados válidos do servidor
          this.analisaDados(dados); //Analisa o tipo de dados recebidos
        }   
        //Fecha o buffer de escrita e leitura
        escritor.close();
        leitor.close();
        //encerra a conexão com o servidor
        this.encerrarConexao();
        System.out.println("C- Fechou a conexão"); 
        //Retorna o dado recebido
        return dados;
     }
     
    @Override
    public void run() {
        String dadosRecebidos = null;
        while (true) {//Fica sempre esperando dados do servidor
            try {
                dadosRecebidos = leitor.readUTF(); //recebe o que foi enviado
                if(dadosRecebidos != null){//se a string não for nula
                    System.out.println("C- Recebido do servidor: "+ dadosRecebidos); //imprime o que recebeu
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
