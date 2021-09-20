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
import java.util.ArrayList;
import model.ArmazenamentoDados;
import model.Paciente;
import util.FilaPaciente;

/**
 * Classe que realiza a comunicação da interface, com o comunicador, classe para
 * manipulacao de arquivo e com a classe de paciente
 * 
 * Exemplo de uso:
 * 
 * ControladorInterface controlador = ControladorInterface.getInstancia();
 */
public class ControladorInterface {
    
    private static ControladorInterface instancia; //Instancia do controlador de interface
    private FilaPaciente pacientes; //fila de pacientes
    private ArmazenamentoDados armazenamento; //atributo para manipulação de arquivo

    /**
     * Método construtor para a classe. Instancia a fila de paciente e o atributo
     * para manipulacao de arquivo.
     */
    private ControladorInterface(){
        pacientes = new FilaPaciente();
        armazenamento= new ArmazenamentoDados();
    }
 
     /**
     * Método que retorna a única instancia do Controlador. Caso não exista, cria a
     * mesma.
     * @return ControladorInterface- a instância do controlador
     */
    public static synchronized ControladorInterface getInstancia() {
        if (instancia == null) {
            instancia = new ControladorInterface();
        }
        return instancia;
    }
    
    /**
     * Método que retona a fila de pacientes
     * @return FilaPaciente - fila de paciente
     */
    public FilaPaciente getPacientes() {
        return pacientes;
    }

    /**
     * Método que altera a fila de pacientes
     * @param pacientes - nova fila
     */
    public void setPacientes(FilaPaciente pacientes) {
        this.pacientes = pacientes;
    }
    
    /**
     * Método para cadastrar pacientes na fila e escrever no arquivo o paciente
     * cadastrado.
     * @param nome - nome do paciente
     * @param cpf - cpf do paciente
     */
    public void cadastrarPaciente(String nome, String cpf){
        Paciente paciente = new Paciente(nome, cpf); //cria a instancia de paciente com os dados recebidos
        pacientes.add(paciente);//adiciona o paciente na fila
        String dado= nome.concat(":"+cpf);//concatena o nome do paciente com o cpf 
        dado= dado.replace ("\n", ""); //retira o \n da string dado
        armazenamento.write("pacientes.txt",dado); //Escreve o paciente no arquivo
    }
   
    /**
     * Método para remoção de paciente e que realiza uma escrita no arquivo após
     * a remoção.
     * @param nome - nome do paciente a ser removido
     * @return Paciente - retorna o paciente removido
     */
    public Paciente removerPaciente(String nome){
        if(!pacientes.isEmpty()){ //Se a lista de paciente não estiver vazia
            Paciente paciente = pacientes.remove(nome); //Remove e salva o paciente
                if(paciente!=null){ //Se encontrou o paciente a ser removido
                    armazenamento.write("pacientes.txt",pacientes); //Escreve no arquivo a nova lista de pacientes
                    return paciente; //retorna o paciente removido
                }
        }
        return null;
    }
    
    /**
     * Método para fazer a importação do arquivo, com o nome e cpf de cada paciente
     * @throws IOException 
     */
    public void importarPacientes() throws IOException{
        //Realiza a leitura do arquivo e salva a lista de leitura retornada
        ArrayList<String> leitura= armazenamento.read("pacientes.txt");
        String[] dados;
        int count=0;
        while(!leitura.isEmpty() && count<leitura.size()){ //Realiza a leitura até chegar o fim da lista
            dados = leitura.get(count).split(":"); //Faz a separação da string
            Paciente paciente= new Paciente(dados[0],dados[1]); //Cria a instancia do paciente
            pacientes.add(paciente);//adiciona na fila de pacientes
            count++; 
        }
    }
    
    /**
     * Método que adiciona em uma lista todos os pacientes cadastrados
     * e retona para a interface.
     * @return ArrayList - lista com os pacientes
     */
    public ArrayList addPacientesComboBox() {
        ArrayList listaDePacientes = pacientes.listarTodosPacientes();
        return listaDePacientes;
    }

    /**
     * Método que adiciona em uma lista os 7 pacientes mais graves e retorna para
     * a inteface.
     * @return ArrayList - lista com os 7 pacientes mais graves
     */
    public ArrayList addPacientesGraves() {
        ArrayList listaDePacientes = pacientes.listarPacientesGraves();
        return listaDePacientes;
    }
    
    /**
     * Método que busca um paciente apartir do nome
     * @param nome - nome do paciente
     * @return Paciente - paciente encontrado
     */
    public Paciente buscarPaciente (String nome){
        return pacientes.buscarPaciente(nome);
    }
    
    /**
     * Método para atualizar os dados de um determinado paciente, na fila de 
     * pacientes
     * @param nome - nome do paciente
     * @param temperatura - nova temperatura
     * @param freqCardiaca - nova frequência cardíaca
     * @param freqRespiratoria - nova frequência respiratória
     * @param pressao - nova pressão
     * @param saturacao - nova saturação do oxigênio
     */
    public void atualizarDados(String nome,double temperatura,float freqCardiaca,float freqRespiratoria,float pressao, float saturacao){
        //Remove o paciente e salva. Remove para que a fila possa ficar em ordem de gravidade
        Paciente paciente = pacientes.remove(nome);

        //Faz a alteração de cada sinal final, na instância do paciente se for encontrado
        if(paciente!=null){
            paciente.setTemperatura(temperatura);
            paciente.setFreqCardiaca(freqCardiaca);
            paciente.setFreqRespiratoria(freqRespiratoria);
            paciente.setPressao(pressao);
            paciente.setSatOxigenio(saturacao);
            //Reensere o paciente na fila
            pacientes.add(paciente);
        }
    }
}
