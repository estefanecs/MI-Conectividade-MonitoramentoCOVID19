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
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import model.Carro;
import model.Partida;
import model.Piloto;
import model.SortByMelhorVolta;
import model.Volta;
import org.json.simple.JSONObject;

/**
 *
 * @author Messias Júnior Lira da Silva
 */
public class ControladorCorrida{
    private LinkedList<Piloto> pilotos;
    private LinkedList<JSONObject> dadosVolta;
    private ControladorComunicacao comunicador;
    private static ControladorCorrida instancia;
    private LinkedList<Piloto> rankingQualificacao;
    private LinkedList<Carro> rankingCorrida;
    private LinkedList<Piloto> pilotosParticipantes;
    private LinkedList<JSONObject> filaDeLeituras;
    private boolean rodando = false, ordenando =false;
    private Partida partida;
    

    public ControladorCorrida() {
        this.pilotos = pilotos;
        comunicador = ControladorComunicacao.getInstance();
        pilotosParticipantes = new LinkedList();
        filaDeLeituras = new LinkedList();
        rankingQualificacao = new LinkedList();
        rankingCorrida = new LinkedList();
    }
    
    /**
     * Método que retorna a única instancia do ControladorComunicacao. Caso não exista, cria o mesmo.
     * @return 
     */
    public static synchronized ControladorCorrida getInstance() {
        if (instancia == null) {
            instancia= new ControladorCorrida();
        }
        return instancia;
    }
    
    /**
     * Metodo que retorna lista de pilotos
     * @return 
     */
    public LinkedList getPilotos() {
        return pilotos;
    }

    /**
     * Metodo que retorna o rankin da qualificação
     * @return 
     */
    public LinkedList<Piloto> getRankingQualificacao() {
        return rankingQualificacao;
    }

    /**
     * Metodo que retorna o rankin da corrida
     * @return 
     */
    public LinkedList<Carro> getRankingCorrida() {
        return rankingCorrida;
    }
    
    /**
     * Metodo que altera lista de pilotos
     * @param pilotos 
     */
    public void setPilotos(LinkedList pilotos) {
        this.pilotos = pilotos;
    }

    /**
     * Metodo que retorna lista de dados da volta
     * @return 
     */
    public LinkedList getDadosVolta() {
        return dadosVolta;
    }

    /**
     * Metodos que altera lista de dados da volta
     * @param dadosVolta 
     */
    public void setDadosVolta(LinkedList dadosVolta) {
        this.dadosVolta = dadosVolta;
    }
    
    /**
     * Metodo que encontra carro pela tag
     * @param tag
     * @return 
     */
    public Carro encontrarCarroTag(String tag){
        for(Piloto piloto: this.pilotos){
            if(piloto.getCarro().getTag().equalsIgnoreCase(tag)){
                return piloto.getCarro();
            }
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
     * Metodo que retorna fila de leituras
     * @return 
     */
    public LinkedList<JSONObject> getFilaDeLeituras() {
        return filaDeLeituras;
    }
    
    /**
     * Metodo que retorna ranking da qualificacao
     * @return 
     */
    public LinkedList<Piloto> getAtualizacaoQualificacao(){
        return this.rankingQualificacao;
    }
    
    /**
     * Metodo que retorna ranking da corrida
     * @return 
     */
    public LinkedList<Carro> getAtualizacaoCorrida(){
        return this.rankingCorrida;
    }
    
    /**
     * Metodo que inicia ranking da qualificacao
     * @param pilotos 
     */
    public void iniciarRankingQualificacao(LinkedList<Piloto> pilotos){
        this.rankingQualificacao = pilotos;
    }

    /**
     * Metodo que inicia ranking da qualificacao
     * @param pilotos 
     */
    public void iniciarRankingCorrida(){
        for(Piloto p: rankingQualificacao){
            this.rankingCorrida.add(p.getCarro());
        }
    }
    
    /**
     * Metodo que ordena o ranking da qualificacao
     * @param obj 
     */
    public synchronized void atualizaQualificacao(JSONObject obj) {
        Volta volta = this.getDadoVolta(obj);
        this.ordenando=true;
        Collections.sort(rankingQualificacao);//oredena lista de pilotos de acordo com o menor tempo da ultima volta
        this.ordenando=false;
    }
    
    /**
     * Metodo que ordena o ranking da corrida
     * @param obj 
     */
    public void atualizaCorrida(JSONObject obj){
        Volta volta = this.getDadoVolta(obj);
        this.ordenando=true;
        Collections.sort(rankingCorrida);//oredena lista de pilotos de acordo com a quantidade de voltas
        this.ordenando=false;
    }
    
    /**
     * Método que atualiza o recorde e o recordista da pista
     * @param carro
     * @param volta 
     */
    public void atualizaRecorde(Carro carro, Volta volta){
            if(partida.getPista().getRecordista()==null){
                Piloto p = encontrarPilotoPeloCarro(carro.getNumero());
                if(p!=null){
                    partida.getPista().setRecordista(p);
                    partida.getPista().setVoltaRecorde(volta);
                }
            } else{
                if(volta.compareTo(partida.getPista().getVoltaRecorde())<0){
                    Piloto p = encontrarPilotoPeloCarro(carro.getNumero());
                    partida.getPista().setRecordista(p);
                    partida.getPista().setVoltaRecorde(volta);
                }
            }   
    }
    
    /**
     * Metodo que retorna uma volta
     * @param obj
     * @return 
     */
    private Volta getDadoVolta(JSONObject obj) {
        String tag = (String) obj.get("TAG");
        double tempo = (double) obj.get("TEMPO");
        String tempoS = (String) obj.get("TEMPO_S");
        Carro carro = this.encontrarCarroTag(tag);
        if(carro != null){
            Volta volta = new Volta(carro, tempo);
            volta.setTempoVolta(tempoS);
            carro.getVoltas().add(volta);

            if(volta != null){
                atualizaRecorde(carro, volta);
            }
            return volta;
        } else{
            return null;
        }
    }
    
    /**
     * Metodo que retorna a diferença de tempo de dois pilotos
     * @param p1
     * @param p2
     * @return 
     */
    public String getDiferenca(Piloto p1, Piloto p2){
        if(!p1.getCarro().getVoltas().isEmpty()&& !p2.getCarro().getVoltas().isEmpty()){
            Volta voltaP1 = p1.getCarro().getVoltas().getLast();
            Volta voltaP2 = p2.getCarro().getVoltas().getLast();

            //pega o tempo hr:min:seg da volta atual
            String[] tempoP1 = voltaP1.getTempoVolta().split(":");
            int minutosP1 = Integer.parseInt(tempoP1[0]);
            float segundosP1 = Float.valueOf(tempoP1[1]).floatValue();

            //pega o tempo da volta a ser comparada
            String[] tempoP2 = voltaP2.getTempoVolta().split(":");
            int minutosP2 = Integer.parseInt(tempoP2[0]);
            float segundosP2 = Float.valueOf(tempoP2[1]).floatValue();

            DecimalFormat df = new DecimalFormat("#,###,##0.000");
            if(minutosP1-minutosP2==0){
                return "+"+df.format(segundosP1-segundosP2);
            } else{
                return "+"+(minutosP1-minutosP2)+":"+df.format(segundosP1-segundosP2);
            }
        }
        return "0";
    }
    
    /**
     * Metodo que retorna a diferença de tempo de dois pilotos
     * @param p1
     * @param p2
     * @return 
     */
    public String getMelhorDiferenca(Piloto p1, Piloto p2){
        if(!p1.getCarro().getVoltas().isEmpty()&& !p2.getCarro().getVoltas().isEmpty()){
            Volta voltaP1 = p1.getCarro().getMelhorVolta();
            Volta voltaP2 = p2.getCarro().getMelhorVolta();

            //pega o tempo hr:min:seg da volta atual
            String[] tempoP1 = voltaP1.getTempoVolta().split(":");
            int minutosP1 = Integer.parseInt(tempoP1[0]);
            float segundosP1 = Float.valueOf(tempoP1[1]).floatValue();

            //pega o tempo da volta a ser comparada
            String[] tempoP2 = voltaP2.getTempoVolta().split(":");
            int minutosP2 = Integer.parseInt(tempoP2[0]);
            float segundosP2 = Float.valueOf(tempoP2[1]).floatValue();

            DecimalFormat df = new DecimalFormat("#,###,##0.000");
            if(minutosP1-minutosP2==0){
                return "+"+df.format(segundosP1-segundosP2);
            } else{
                return "+"+(minutosP1-minutosP2)+":"+df.format(segundosP1-segundosP2);
            }
        }
        return "0";
    }
    
    /**
     * Metodo que retorta se a corrida esta rodando
     * @return 
     */
    public boolean getRodando(){
        return this.rodando;
    }
    
    /**
     * Metodo que altera o estado ca corrida
     * @param rodando 
     */
    public void setRodando(boolean rodando){
        this.rodando = rodando;
    }

    /**
     * Método que retorna a partida
     * @return 
     */
    public Partida getPartida() {
        return partida;
    }
    
    /**
     * Metodo que altera a partida
     * @param p 
     */
    public void setPartida(Partida p){
        this.partida = p;
    }
    
    /**
     * Metodo que retorna o estado de ordenacao
     * @return 
     */
    public boolean getOrdenando(){
        return this.ordenando;
    }
    
    /**
     * Metodo que altera o estado de ordenacao
     * @param ordenando 
     */
    public void setOrdenando(boolean ordenando){
        this.ordenando = ordenando;
    }
    
    /**
     * Metodo que verifica se é o fim da corrida
     * @return 
     */
    public boolean fimCorrida(){
        boolean fimCorrida =false;
        for(Piloto piloto: rankingQualificacao){
            if(!piloto.getCarro().getVoltas().isEmpty() ){
                if(piloto.getCarro().getVoltas().size()>=partida.getVoltas()){
                    fimCorrida =true;
                } else{
                    fimCorrida = false;
                }
            }
        }
        return fimCorrida;
    }
    
    /**
     * Metodo que zera a lista de voltas dos pilotos da qualificação
     */
    public void resetaPilotos(){
        if(!this.rankingQualificacao.isEmpty()){
            for(Piloto p: this.rankingQualificacao){
                p.getCarro().getVoltas().clear();
            }
        }
    }
    
    /**
     * Metodo que zera a lista de voltas dos pilotos da corrida
     */
    public void resetaPilotosCorrida(){
        if(!this.rankingCorrida.isEmpty()){
            for(Carro c: this.rankingCorrida){
                c.getVoltas().clear();
                c.setMelhorVolta(null);
            }
            this.rankingCorrida.clear();
        }
    }
    
    /**
     * Metodo que retorna a quantidade de voltas
     * @return 
     */
    public int getNumVoltas(){
        int maior=0;
        for(int i=0; i<this.rankingCorrida.size(); i++){
            if(i==0){
                maior = this.rankingCorrida.get(i).getVoltas().size();
            }
            if(maior<this.rankingCorrida.get(i).getVoltas().size()){
                maior = this.rankingCorrida.get(i).getVoltas().size();
            }
        }
        return maior;
    }
}
