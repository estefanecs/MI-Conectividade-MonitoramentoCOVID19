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


import controler.ControladorComunicacao;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import model.Equipe;
import model.Piloto;
import model.Carro;
import util.Arquivo;
import model.Leitor;
import model.Partida;
import model.Pista;
import model.Volta;

/**
 *
 * @author Messias Júnior Lira da Silva
 */
public class ControladorConfig {
    private static ControladorConfig instancia;
    private final ControladorComunicacao comunicador;
    private Leitor leitor;
    private LinkedList<Carro> carros;
    private LinkedList<JCheckBox> carrosBox;
    private LinkedList<Equipe> equipes;
    private LinkedList<Piloto> pilotos;
    private LinkedList<JCheckBox> pilotosBox;
    private LinkedList<JCheckBox> pilotosPartidaBox;
    private LinkedList<Pista> pistas;
    private Partida partida;
    private Arquivo arquivo;
    private LinkedList<String> filaDeTags =new LinkedList();
    
    public ControladorConfig() throws IOException {
        this.comunicador = ControladorComunicacao.getInstance();
        this.carros = new LinkedList();
        this.pilotos = new LinkedList();
        this.equipes = new LinkedList();
        this.pistas = new LinkedList();
        this.arquivo = new Arquivo();
        this.carrosBox = new LinkedList();
        this.pilotosBox = new LinkedList();
        this.pilotosPartidaBox = new LinkedList();
        this.carregarDados();
    }
    
    /**
     * Metodo que retorna uma unica instancia do controlador de configuracao
     * @return
     * @throws IOException 
     */
    public static synchronized ControladorConfig getInstance() throws IOException {
        if (instancia == null) {
            instancia= new ControladorConfig();
        }
        return instancia;
    }
    
    /**
     * Metodo que retorna uma lista de carros
     * @return 
     */
    public LinkedList<Carro> getCarros() {
        return carros;
    }

    /**
     * Metodos que altera a lista de carros
     * @param carros 
     */
    public void setCarros(LinkedList<Carro> carros) {
        this.carros = carros;
    }

    /**
     * Metodo que retorna lista de equipes
     * @return 
     */
    public LinkedList<Equipe> getEquipes() {
        return equipes;
    }

    /**
     * Metodo que altera lista de equipes
     * @param equipes 
     */
    public void setEquipes(LinkedList<Equipe> equipes) {
        this.equipes = equipes;
    }

    /**
     * Metodo que retorna lista de pilotos
     * @return 
     */
    public LinkedList<Piloto> getPilotos() {
        return pilotos;
    }

    /**
     * Metodos que altera a lista de pilotos
     * @param pilotos 
     */
    public void setPilotos(LinkedList<Piloto> pilotos) {
        this.pilotos = pilotos;
    }
    
    /**
     * Metodo que retorna lista de pistas
     * @return 
     */
    public LinkedList<Pista> getPistas() {
        return pistas;
    }

    /**
     * Metodo que altera a lista de pistas
     * @param pistas 
     */
    public void setPistas(LinkedList<Pista> pistas) {
        this.pistas = pistas;
    }
    
    /**
     * Metodos que retornam o leitor
     * @return 
     */
    public Leitor getLeitor(){
        return this.leitor;
    }
    
    /**
     * Metodo que retorna uma partida
     * @return 
     */
    public Partida getPartida(){
        return this.partida;
    }
    
    /**
     * Metodo que altera uma partida
     * @param partida 
     */
    public void setPartida(Partida partida){
        this.partida = partida;
    }

    /**
     * Metodo que retorna lista de JCheckBox de carros
     * @return 
     */
    public LinkedList<JCheckBox> getCarrosBox() {
        return carrosBox;
    }

    /**
     * Metodo que altera a lista de JCheckBox de carro
     * @param carrosBox 
     */
    public void setCarrosBox(LinkedList<JCheckBox> carrosBox) {
        this.carrosBox = carrosBox;
    }

    /**
     * Metodo que retorna lista de JCheckBox de pilotos
     * @return 
     */
    public LinkedList<JCheckBox> getPilotosBox() {
        return pilotosBox;
    }

    /**
     * Metodo que altera lista de JCheckBox de pilotos
     * @return 
     */
    public void setPilotosBox(LinkedList<JCheckBox> pilotosBox) {
        this.pilotosBox = pilotosBox;
    }
    
    /**
     * Metodo que retorna a lista de checkbox de pilotos de uma partida
     * @return 
     */
    public LinkedList<JCheckBox> getPilotosPartidaBox() {
        return pilotosPartidaBox;
    }
    
    /**
     * Metodos que altera a lista de pilotos de uma partida
     * @param pilotosPartidaBox 
     */
    public void setPilotosPartidaBox(LinkedList<JCheckBox> pilotosPartidaBox) {
        this.pilotosPartidaBox = pilotosPartidaBox;
    }

    /**
     * Metodo que retorna fila de tags
     * @return 
     */
    public LinkedList<String> getFilaDeTags() {
        return filaDeTags;
    }
    
    /**
     * Metodo que configura o leitor
     * @param ip
     * @param porta_rede
     * @param porta_serial
     * @param baud
     * @param potencia
     * @param regiao
     * @throws IOException 
     */
    public void configLeitor(String ip, int porta_rede, String porta_serial, int baud, int potencia, String regiao) throws IOException{
        leitor = new Leitor(ip, porta_rede, porta_serial, baud, potencia, regiao) ;
        String dados = ip +";"+ porta_rede +";"+ porta_serial +";"+ baud +";"+ potencia +";"+ regiao;
        arquivo.write("leitor.txt", dados);//guarda no arquivo
        comunicador.writeLeitor(dados);
    }
    
    /**
     * Metodo que chama o metodo solicita tag e retorna para a tela de cadastro
     * @return 
     */
    public String lerTag(){
        
        comunicador.solicitaDados("GET::configurar/carro");
        String tag =null;
        while(tag==null){//enquanto nao receper a tag
            if(!filaDeTags.isEmpty()){
                tag = filaDeTags.getFirst();
                filaDeTags.removeFirst();
            }
        }
        return tag;
    }
    
    /**
     * Metodo que cadastra carro
     * @param id
     * @param tag
     * @param modelo
     * @param marca
     * @param cor
     * @param numero
     * @param nomePiloto
     * @param nomeEquipe
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public boolean cadastrarCarro(int id, String tag, String modelo, String marca, String cor, int numero, String nomePiloto, String nomeEquipe) throws IOException, ClassNotFoundException{
        Equipe equipe;
        Piloto piloto;
        Carro carro;
        if(nomePiloto.equals("Nenhum") && nomeEquipe.equals("Nenhum") ){//se não tem piloto nem equipe
            carro = new Carro(id, tag, modelo, marca, cor, numero);
        }else if(nomePiloto.equals("Nenhum") && !nomeEquipe.equals("Nenhum") ){//se nao tem piloto, mas tem equipe
            equipe = encontrarEquipe(nomeEquipe);
            carro = new Carro(id, tag, modelo, marca, cor, numero, equipe); 
            equipe.getCarros().add(carro);
            
        } else if(!nomePiloto.equals("Nenhum") && nomeEquipe.equals("Nenhum") ){//se tem piloto mas não tem equipe
            piloto = encontrarPiloto(nomePiloto);
            carro = new Carro(id, tag, modelo, marca, cor, numero, encontrarPiloto(nomePiloto)); 
            piloto.setCarro(carro);
        } else{// se tem piloto e equipe
            equipe = encontrarEquipe(nomeEquipe);
            piloto = encontrarPiloto(nomePiloto);
            carro = new Carro(id, tag, modelo, marca, cor, numero, encontrarPiloto(nomePiloto), equipe); 
            equipe.getCarros().add(carro);
            piloto.setCarro(carro);
        }
        
        this.carros.add(carro);
        String dados = id + ";" + tag + ";" + modelo + ";" + marca + ";" + cor + ";" + numero + ";" + nomePiloto + ";" + nomeEquipe;
        arquivo.writeApp("carros.txt", dados+"\n");//guarda no arquivo
        return true;
    }
    
    /**
     * Metodo que cadastra piloto
     * @param id
     * @param nome
     * @param apelido
     * @param nascimento
     * @param nacionalidade
     * @param numCarro
     * @param nomeEquipe
     * @param pontos
     * @return 
     */
    public boolean cadastrarPiloto(int id, String nome, String apelido, int nascimento, String nacionalidade, int numCarro, String nomeEquipe, int pontos){
        Piloto piloto;
        Equipe equipe;
        Carro carro;
        String foto = "fotoDoPiloto";
        if(numCarro == 0 && nomeEquipe.equalsIgnoreCase("Nenhum") ){//se não tem carro nem equipe
            piloto = new Piloto(id, nome, apelido, foto, nascimento, nacionalidade, false, pontos);
        }else if(numCarro == 0 && !nomeEquipe.equalsIgnoreCase("Nenhum") ){//se nao tem carro, mas tem equipe
            equipe = encontrarEquipe(nomeEquipe);
            piloto = new Piloto(id, nome, apelido, foto, nascimento, nacionalidade, equipe, false, pontos);
            equipe.getPilotos().add(piloto);
        } else if(numCarro !=0 && nomeEquipe.equalsIgnoreCase("Nenhum") ){//se tem carro mas não tem equipe
            carro =encontrarCarro(numCarro);
            piloto = new Piloto(id, nome, apelido, foto, nascimento, nacionalidade, carro, false, pontos);
            carro.setPiloto(piloto);
        } else{// se tem carro e equipe
            equipe = encontrarEquipe(nomeEquipe);
            carro =encontrarCarro(numCarro);
            piloto = new Piloto(id, nome, apelido, foto, nascimento, nacionalidade, carro, equipe, true, pontos);
            equipe.getPilotos().add(piloto);
            carro.setPiloto(piloto);    
        }
        
        this.pilotos.add(piloto);
        String dados = id + ";" + nome + ";" + apelido + ";" + foto + ";" + nascimento + ";" + nacionalidade + ";" + numCarro + ";" + nomeEquipe + ";" + false + ";" + pontos;
        arquivo.writeApp("Pilotos.txt", dados+"\n");//guarda no arquivo
        return true;
    }
    
    /**
     * Metodo que cadastra equipe
     * @param id
     * @param nome
     * @param apelido
     * @param nacionalidade
     * @param ano
     * @param pontos
     * @return 
     */
    public boolean cadastrarEquipe(int id, String nome, String apelido, String nacionalidade, int ano, int pontos){
        String logo = "logoDaEquipe";
        Equipe equipe = new Equipe(id, nome, apelido, nacionalidade, ano, logo, pontos);
        equipes.add(equipe);
        String dados = id + ";" + nome + ";" + apelido + ";" + nacionalidade + ";" + ano +";" + logo + ";" + pontos;
        return true;
    }
    
    /**
     * Metodo que cadastra pista
     * @param id
     * @param nome
     * @param pais
     * @param tempoMinimo
     * @return 
     */
    public boolean cadastrarPista(int id, String nome, String pais, int tempoMinimo){
        String imagem = "imagemPista";
        Pista pista = new Pista(id, nome, pais, imagem, tempoMinimo);
        String dados = id + ";" + nome + ";" + pais + ";" + imagem +";"+ tempoMinimo;
        this.pistas.add(pista);
        arquivo.writeApp("pistas.txt", dados+"\n");//guarda no arquivo
        return true;
    }
    
    /**
     * Metodo que configura partida
     * @param duracao
     * @param voltas
     * @param pista
     * @return 
     */
    public boolean configurarPartida(int duracao, int voltas, String pista){
        String dados = duracao + ";" + voltas + ";" + pista;
        
        Partida partida = new Partida(duracao, voltas, encontrarPista(pista));
        arquivo.write("partidas.txt", dados+"\n");
        this.setPartida(partida);
        partida.setPilotosAtivos(this.getPilotosSelecionadosPartida());
        return true;
    }
    
    /**
     * Metodo que associa um piloto a um carro
     * @param nomePiloto
     * @param numCarro 
     */
    public void cadastrarPilotoCarro(String nomePiloto, int numCarro){
        Piloto piloto = encontrarPiloto(nomePiloto);
        Carro carro = encontrarCarro(numCarro);
        piloto.setCarro(carro);
        carro.setPiloto(piloto);
    }
    
    /**
     * Metodo que associa um piloto a uma equipe
     * @param nomePiloto
     * @param nomeEquipe 
     */
    public void cadastrarPilotoEquipe(String nomePiloto, String nomeEquipe){
        Piloto piloto = encontrarPiloto(nomePiloto);
        Equipe equipe = encontrarEquipe(nomeEquipe);
        piloto.setEquipe(equipe);
       
        if(piloto.getCarro() != null){
            piloto.getCarro().setEquipe(equipe);
            equipe.getCarros().add(piloto.getCarro());
        }
        equipe.getPilotos().add(piloto);
        
    }
    
    /**
     * Metodo que associa um carro a uma equipe
     * @param numCarro
     * @param nomeEquipe 
     */
    public void cadastrarCarroEquipe(int numCarro, String nomeEquipe){
        Carro carro = encontrarCarro(numCarro);
        Equipe equipe = encontrarEquipe(nomeEquipe);
        carro.setEquipe(equipe);
        equipe.getCarros().add(carro);
    }
    
    /**
     * Metodo que retorna um carro de acordo com o numero
     * @param numCarro
     * @return 
     */
    public Carro encontrarCarro(int numCarro){
        int count = 0;
        while (count < this.carros.size()) {
            if (this.carros.get(count).getNumero() == numCarro){
                return this.carros.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna um piloto de acordo com o nome
     * @param nomePiloto
     * @return 
     */
    public Piloto encontrarPiloto(String nomePiloto){
        int count = 0;
        while (count < this.pilotos.size()) {
            if (this.pilotos.get(count).getNome().equals(nomePiloto)) {
                return this.pilotos.get(count);
            }
            count++;
        }
        return null;
    }
    
    
    /**
     * Metodo que retorna um piloto de acordo com o nome
     * @param nomePiloto
     * @return 
     */
    public Piloto encontrarPiloto(LinkedList<Piloto> pilotos, String nomePiloto){
        int count = 0;
        while (count < pilotos.size()) {
            if (pilotos.get(count).getNome().equals(nomePiloto)) {
                return pilotos.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna uma equipe de acordo com o nome
     * @param nomeEquipe
     * @return 
     */
    public Equipe encontrarEquipe(String nomeEquipe){
        int count = 0;
        while (count < this.equipes.size()) {
            if (this.equipes.get(count).getNome().equals(nomeEquipe)) {
                return this.equipes.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna uma pista de acordo com o nome
     * @param nomePista
     * @return 
     */
    public Pista encontrarPista(String nomePista){
        int count = 0;
        while (count < this.pistas.size()) {
            if (this.pistas.get(count).getNome().equals(nomePista)) {
                return this.pistas.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna um piloto de acordo com o numero do carro
     * @param numCarro
     * @return 
     */
    public Piloto encontrarPilotoPeloCarro(int numCarro){
        int count = 0;
        while (count < this.pilotos.size()) {
            if (this.pilotos.get(count).getCarro().getNumero()==numCarro) {
                return this.pilotos.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna uma equipe pelo nome do piloto
     * @param nomePiloto
     * @return 
     */
    public Equipe encontrarEquipePeloPiloto(String nomePiloto){
        int count = 0;
        while (count < this.equipes.size()) {
            if (this.equipes.get(count).encontrarPiloto(nomePiloto) != null) {
                return this.equipes.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna uma equipe´pelo numero do carro
     * @param numCarro
     * @return 
     */
    public Equipe encontrarEquipePeloCarro(int numCarro){
        int count = 0;
        while (count < this.equipes.size()) {
            if (this.equipes.get(count).encontrarCarro(numCarro) != null) {
                return this.equipes.get(count);
            }
            count++;
        }
        return null;
    }
    
    /**
     * Metodo que retorna os carros selecionados
     * @return 
     */
    public LinkedList<Carro> getCarrosSelecionados(){
        LinkedList<Carro> carrosSelecionados = new LinkedList();
        Carro carro;
        for(int i=0; i<carrosBox.size(); i++){
            if(carrosBox.get(i).isSelected()){
                carro = encontrarCarro(Integer.parseInt(carrosBox.get(i).getText()));
                if(carro != null){
                    carrosSelecionados.add(carro);
                }
            }
        }
        return carrosSelecionados;
    }
    
    /**
     *Metodo que retorna os pilotos selecionados 
     * @return 
     */
    public LinkedList<Piloto> getPilotosSelecionados(){
        LinkedList<Piloto> pilotosSelecionados = new LinkedList();
        Piloto piloto;
        for(int i=0; i<pilotosBox.size(); i++){
            if(pilotosBox.get(i).isSelected()){
                piloto = encontrarPiloto(pilotosBox.get(i).getText());
                if(piloto != null && encontrarPiloto(pilotosSelecionados, piloto.getNome())!=null){
                    pilotosSelecionados.add(piloto);
                }
            }
        }
        return pilotosSelecionados;
    }
    
    /**
     * Metodo que retorna os pilotos selecionados para a partida
     * @return 
     */
    public LinkedList<Piloto> getPilotosSelecionadosPartida(){
        LinkedList<Piloto> pilotosSelecionadosPartida = new LinkedList();
        Piloto piloto;
        for(int i=0; i<pilotosPartidaBox.size(); i++){
            if(pilotosPartidaBox.get(i).isSelected()){
                piloto = encontrarPiloto(pilotosPartidaBox.get(i).getText());
                if(piloto != null){
                    pilotosSelecionadosPartida.add(piloto);
                }
            }
        }
        return pilotosSelecionadosPartida;
    }
    
    /**
     * Metodo que lê os dados do arquivo e armazena na memoria
     * @throws IOException 
     */
    public void carregarDados() throws IOException{
        
        String[] conteudoLeitor, conteudoCarros, conteudoPilotos, conteudoEquipes, conteudoPistas, conteudoPartidas;
        
        //lê dados do leitor
        if(!arquivo.isEmpty("leitor.txt")){
            conteudoLeitor = arquivo.read("leitor.txt").split("\n");
            String[] leitorDados = conteudoLeitor[conteudoLeitor.length-1].split(";");
            this.leitor = new Leitor(leitorDados[0], Integer.parseInt(leitorDados[1]), leitorDados[2], Integer.parseInt(leitorDados[3]),Integer.parseInt(leitorDados[4]),leitorDados[5]);
        }
        
        //lê dados dos carros
        if(!arquivo.isEmpty("carros.txt")){
            conteudoCarros = arquivo.read("carros.txt").split("\n");
            for (String conteudoCarro : conteudoCarros) {
                String[] carroDados = conteudoCarro.split(";");
                Carro carro;
                carro = new Carro(Integer.parseInt(carroDados[0]), carroDados[1], carroDados[2], carroDados[3], carroDados[4], Integer.parseInt(carroDados[5]), encontrarPiloto(carroDados[6]),encontrarEquipe(carroDados[7]));
                this.getCarros().add(carro);
            }  
        }
        
        //lê dados dos pilotos
        if(!arquivo.isEmpty("pilotos.txt")){
            conteudoPilotos = arquivo.read("pilotos.txt").split("\n");
            for (String conteudoPiloto : conteudoPilotos) {
                String[] pilotosDados = conteudoPiloto.split(";");
                Piloto piloto;
                piloto = new Piloto(Integer.parseInt(pilotosDados[0]), pilotosDados[1], pilotosDados[2], pilotosDados[3], Integer.parseInt(pilotosDados[4]), pilotosDados[5], encontrarCarro(Integer.parseInt(pilotosDados[6])), encontrarEquipe(pilotosDados[7]), pilotosDados[8].equalsIgnoreCase("true"), Integer.parseInt(pilotosDados[9]));
                this.getPilotos().add(piloto);
            }
        }
        
        //lê dados das equipes
        if(!arquivo.isEmpty("equipes.txt")){
            conteudoEquipes = arquivo.read("equipes.txt").split("\n");
            for (String conteudoEquipe : conteudoEquipes) {
                String[] equipesDados = conteudoEquipe.split(";");
                Equipe equipe;
                equipe = new Equipe(Integer.parseInt(equipesDados[0]), equipesDados[1], equipesDados[2], equipesDados[3],  Integer.parseInt(equipesDados[4]), equipesDados[5], Integer.parseInt(equipesDados[8]));
                
                String[] pilotos= equipesDados[6].split(",");
                for(String piloto: pilotos){
                    if(!piloto.equals("Nenhum")){
                        Piloto p =encontrarPiloto(piloto);
                        equipe.getPilotos().add(p);
                        p.setEquipe(equipe);
                    }
                }
                
                String[] carros= equipesDados[7].split(",");
               
                for(String carro: carros){
                    if(!carro.equals("0")){
                        Carro c =encontrarCarro(Integer.parseInt(carro));
                        equipe.getCarros().add(c);
                        c.setEquipe(equipe);
                    }
                }                
                this.getEquipes().add(equipe);
            }
        }
        
        //lê dados das pistas
        if(!arquivo.isEmpty("pistas.txt")){
            conteudoPistas = arquivo.read("pistas.txt").split("\n");
            for (String conteudoPista : conteudoPistas) {
                String[] pistasDados = conteudoPista.split(";");
                Pista pista;
                pista = new Pista(Integer.parseInt(pistasDados[0]), pistasDados[1], pistasDados[2], pistasDados[3], Integer.parseInt(pistasDados[4]));
                pista.setRecord(pistasDados[5]);
                Piloto p = encontrarPiloto(pistasDados[6]);
                pista.setRecordista(p);
                this.getPistas().add(pista);
            }
        }
        
        //le dados da partida
        if(!arquivo.isEmpty("partidas.txt")){
            conteudoPartidas = arquivo.read("partidas.txt").split("\n");
            String[] partidaDados = conteudoPartidas[conteudoPartidas.length-1].split(";");
            this.partida = new Partida(Integer.parseInt(partidaDados[0]), Integer.parseInt(partidaDados[1]), encontrarPista(partidaDados[2]));
        }
        conecta();
    }
    
    /**
     * Metodo que relaciona pilotos - carros - equipes
     */
    public void conecta(){
        
        //relaciona carro com piloto e equipe
        for (Carro carro : this.getCarros()) {
            if(carro.getPiloto() == null){//se nao tem piloto
                Piloto piloto = encontrarPilotoPeloCarro(carro.getNumero());//busca piloto
                carro.setPiloto(piloto);
            }
            if(carro.getEquipe() ==null){//se não tem equipe busca equipe
                Equipe equipe = encontrarEquipePeloCarro(carro.getNumero());
                carro.setEquipe(equipe);
                
            }
        }
        
        //relaciona piloto com a equipe
        for (Piloto piloto : this.getPilotos()) {
            if(piloto.getEquipe() == null){// se o piloto nao tem equipe
                Equipe equipe = encontrarEquipePeloPiloto(piloto.getNome());//busca a equipe
                piloto.setEquipe(equipe);
                
            }
        }
        
        //adiciona a volta recorde na pista
        for(Pista pista: this.getPistas()){
            if(pista.temRecorde()){
                Volta volta = new Volta(pista.getRecordista().getCarro(), 1);
                volta.setTempoVolta(pista.getRecord());
                pista.setVoltaRecorde(volta);
            }
        }
    }
    
    /**
     * Metodos que salva os dados atualizados no arquivo
     */
    public void salvaAtualizacoes(){
        String dados="", pilotos="", carros="";
        
        //salva os dados dos carros
        for(Carro carro: this.getCarros()){
            if(carro.getPiloto()==null && carro.getEquipe()==null){
                dados += carro.getId() + ";" + carro.getTag() + ";" + carro.getModelo() + ";" + carro.getMarca() + ";" + carro.getCor() + ";" + carro.getNumero() + ";" + "Nenhum" + ";" + "Nenhum" +"\n";  
            } else if(carro.getEquipe()==null){
                dados += carro.getId() + ";" + carro.getTag() + ";" + carro.getModelo() + ";" + carro.getMarca() + ";" + carro.getCor() + ";" + carro.getNumero() + ";" + carro.getPiloto().getNome() + ";" + "Nenhum" +"\n";  
            } else if(carro.getPiloto()==null){
                dados += carro.getId() + ";" + carro.getTag() + ";" + carro.getModelo() + ";" + carro.getMarca() + ";" + carro.getCor() + ";" + carro.getNumero() + ";" + "Nenhum" + ";" + carro.getEquipe().getNome()+"\n";  
            }
            else{    
                dados += carro.getId() + ";" + carro.getTag() + ";" + carro.getModelo() + ";" + carro.getMarca() + ";" + carro.getCor() + ";" + carro.getNumero() + ";" + carro.getPiloto().getNome() + ";" + carro.getEquipe().getNome()+"\n";  
            }
        }
        arquivo.write("carros.txt", dados);
        
        dados="";
        //salva os dados dos pilotos
        for(Piloto piloto: this.getPilotos()){
            if(piloto.getCarro()==null && piloto.getEquipe()==null){
                dados += piloto.getId() + ";" + piloto.getNome() + ";" + piloto.getApelido() + ";" + piloto.getFoto() + ";" + piloto.getNascimento() + ";" + piloto.getNacionalidade() + ";" + "0" + ";" + "Nenhum" + ";" + piloto.isAtivo() + ";" + piloto.getPontos()+"\n";
            }else if(piloto.getEquipe()==null){
                dados += piloto.getId() + ";" + piloto.getNome() + ";" + piloto.getApelido() + ";" + piloto.getFoto() + ";" + piloto.getNascimento() + ";" + piloto.getNacionalidade() + ";" + piloto.getCarro().getNumero() + ";" + "Nenhum" + ";" + piloto.isAtivo() + ";" + piloto.getPontos()+"\n";
            } else if(piloto.getCarro()==null){
                dados += piloto.getId() + ";" + piloto.getNome() + ";" + piloto.getApelido() + ";" + piloto.getFoto() + ";" + piloto.getNascimento() + ";" + piloto.getNacionalidade() + ";" + "0" + ";" + piloto.getEquipe().getNome() + ";" + piloto.isAtivo() + ";" + piloto.getPontos()+"\n";
            }
            else{    
                dados += piloto.getId() + ";" + piloto.getNome() + ";" + piloto.getApelido() + ";" + piloto.getFoto() + ";" + piloto.getNascimento() + ";" + piloto.getNacionalidade() + ";" + piloto.getCarro().getNumero() + ";" + piloto.getEquipe().getNome() + ";" + piloto.isAtivo() + ";" + piloto.getPontos()+"\n";            
            } 
        }
        arquivo.write("Pilotos.txt", dados);
        
        dados="";
        //salva os dados das equipes
        for(Equipe equipe: this.equipes){
            if(equipe.getPilotos().isEmpty()){
                pilotos="Nenhum";
            } else if(equipe.getPilotos().size()==1){
                pilotos= equipe.getPilotos().get(0).getNome();
            } else{
                for(int i=0; i<equipe.getPilotos().size(); i++){
                    if(i==equipe.getPilotos().size()-1){
                        pilotos += equipe.getPilotos().get(i).getNome();
                    } else{
                        pilotos += equipe.getPilotos().get(i).getNome()+",";
                    }
                }
            }
            if(equipe.getCarros().isEmpty()){
                carros="0";
            } else if(equipe.getCarros().size()==1){
                carros = Integer.toString(equipe.getCarros().get(0).getNumero());
            } else{
                for(int i=0; i<equipe.getCarros().size(); i++){
                    if(i==equipe.getCarros().size()-1){
                        carros += equipe.getCarros().get(i).getNumero();
                    } else{
                        carros += equipe.getCarros().get(i).getNumero()+",";
                    }
                }
            }
            
            dados += equipe.getId()+";"+ equipe.getNome() +";"+ equipe.getApelido()+";"+equipe.getNacionalidade()+";"+equipe.getAno()+";"+equipe.getLogo()+";"+ pilotos +";"+ carros +";"+ equipe.getPontos()+"\n";
            carros="";
            pilotos="";
        }
        arquivo.write("equipes.txt", dados);
        
        dados="";
        //salva os dados das pistas
        for(Pista pista: this.pistas){
            String recordista="Nenhum";
            if(pista.getRecordista()!=null){
                recordista = pista.getRecordista().getNome();
            }
            dados+=pista.getId()+";"+pista.getNome()+";"+pista.getPais()+";"+pista.getImagem()+";"+pista.getTempoMin()+";"+pista.getRecord()+";"+recordista+"\n";
        }
        arquivo.write("pistas.txt", dados);
    }
    
    /**
     * Metodo que atualiza o estado ativo dos pilotos
     */
    public void atualizaAtividade(){
        for(Piloto piloto: this.getPilotos()){
            if(!piloto.isAtivo()){
                if(piloto.getCarro()!= null && piloto.getEquipe() != null){
                    piloto.setAtivo(true);
                }
            }
        }
    }
}
