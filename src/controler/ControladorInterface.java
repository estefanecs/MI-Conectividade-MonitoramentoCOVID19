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
package controler;

import java.util.ArrayList;
import model.Paciente;
import util.FilaPaciente;

public class ControladorInterface {
    
    private static ControladorInterface instancia;
    private FilaPaciente pacientes;

    public ControladorInterface(){
        pacientes = new FilaPaciente();
    }
 
     /**
     * Método que retorna a única instancia do Controlador. Caso não exista, cria a
     * mesma.
     *
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
    
    public void cadastrarPaciente(String nome, String cpf){
        System.out.println("NOME "+nome+" CPF "+cpf);
        Paciente paciente = new Paciente(nome, cpf);
        pacientes.add(paciente);
        System.out.println("LISTA DE PACIENTE size: "+pacientes.size());
    }
    
    public Paciente removerPaciente(String nome){
        if(!pacientes.isEmpty()){
            return pacientes.remove(nome);
        }
        else{
            return null;
        }
    }
    
    /**
     * Método que adiciona em uma lista todos os pacientes cadastrados
     *
     * @return ArrayList - lista com os pacientes
     */
    public ArrayList addPacientesComboBox() {
        ArrayList listaDePacientes = pacientes.listarTodosPacientes();
        return listaDePacientes;
    }

    /**
     * Método que adiciona em uma lista os 7 pacientes mais graves
     *
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
    
    public void atualizarDados(String nome,double temperatura,float freqCardiaca,float freqRespiratoria,float pressao, float saturacao){
        Paciente paciente = pacientes.remove(nome);
        System.out.println("LISTA DE PACIENTE TAMANHO: "+pacientes.size());
        paciente.setTemperatura(temperatura);
        paciente.setFreqCardiaca(freqCardiaca);
        paciente.setFreqRespiratoria(freqRespiratoria);
        paciente.setPressao(pressao);
        paciente.setSatOxigenio(saturacao);
        pacientes.add(paciente);
        System.out.println("LISTA DE PACIENTE TAMANHO: "+pacientes.size());
    }
}
