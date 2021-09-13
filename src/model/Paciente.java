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
package model;

/**
 * Esta classe é para objetos do tipo Paciente, contendo seus atributos como nome,
 * cpf, temperatura, frequencia cardiaca, frequencia respiratória, pressão arterial,
 * saturação do oxigênio e o estado de gravidade do paciente.
 *
 * Exemplo de uso:
 *
 * Paciente paciente= new Paciente("Nome do paciente","cpf");
 *
 */
public class Paciente {
    private String nome;
    private String cpf;
    private double temperatura;
    private float freqCardiaca;
    private float freqRespiratoria;
    private float pressao;
    private float satOxigenio;
    private int gravidade;

    /**
     * Construtor da classe
     * @param nome nome do paciente
     * @param cpf cpf do paciente
     */
    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.gravidade=0;
    }
    
    /**
     * Método que retorna o nome do paciente
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo que retorna o cpf do paciente
     * @return cpf
     */
    public String getCpf() {
        return cpf;
    }
    
    /**
     * Método que retorna a temperatua do paciente
     * @return temperatura
     */
    public double getTemperatura() {
        return temperatura;
    }
    
    /**
     * Método que retorna a frequencia cardiaca do paciente
     * @return freqCardiaca
     */
    public float getFreqCardiaca() {
        return freqCardiaca;
    }
    
    /**
     * Método que retorna a frequencia respiratoria do paciente
     * @return freqRespiratoria
     */
    public float getFreqRespiratoria() {
        return freqRespiratoria;
    }
    
    /**
     * Método que retorna a pressao do paciente
     * @return pressao
     */
    public float getPressao() {
        return pressao;
    }

    /**
     * Método que retorna a saturacao do oxigenio do paciente
     * @return satOxigenio
     */
    public float getSatOxigenio() {
        return satOxigenio;
    }
    
    /**
     * Método que retorna a gravidade do paciente
     * @return gravidade
     */
    public int getGravidade(){
        return gravidade;
    }
    
    /**
     * Método que altera o nome do paciente
     * @param nome - novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método que altera o cpf do paciente
     * @param cpf - novo cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    /**
     * Método que altera a temperatura do paciente, e altera o valor da gravidade
     * baseando-se no nível de risco que o valor da temperatura apresenta.
     * @param temperatura - nova temperatura
     */
    public void setTemperatura(double temperatura) {
       //Confere a temperatura antes da mudança e subtrai o valor na gravidade
       if(this.temperatura!=0){
            if(this.temperatura>38 && this.temperatura<=38.5){
                this.gravidade-=1; //Subrai 1
            }
            else if (this.temperatura>=38.6){
                this.gravidade-=3;//Subtrai 3
            }   
       } 
       //Altera a temparatura para a nova
        this.temperatura = temperatura;
        //Define a nova gravidade
        if(this.temperatura>38 && this.temperatura<=38.5){
            this.gravidade+=1; //soma 1 na gravidade
        }
        else if (this.temperatura>=38.6){
            this.gravidade+=3; //soma 3 na gravidade
        }
        else{
            this.gravidade+=0; //Não acrescenta nenhum valor
        }
    }

    /**
     * Método que altera a frequencia cardiaca do paciente,
     * baseando-se no nível de risco que o valor da frequencia cardiaca apresenta.
     * @param freqCardiaca - a nova frequencia cardiaca
     */
    public void setFreqCardiaca(float freqCardiaca) {
       //Confere a frequencia cardiaca antes da mudança e subtrai o valor na gravidade
        if(this.freqCardiaca!=0){
            if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
                this.gravidade-=1;//Subtrai 1 na gravidade
            }
            else if (this.freqCardiaca>=111){
                this.gravidade-=3; //Subtrai 3 na gravidade
            }
        }
       //Altera a frequencia cardiaca para a nova
        this.freqCardiaca = freqCardiaca;
        //define a nova gravidade
        if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
            this.gravidade+=1; //Soma 1 na gravidade
        }
        else if (this.freqCardiaca>=111){
            this.gravidade+=3; //Soma 3 na gravidade
        }
        else{
            this.gravidade+=0; //Não soma nenhum valor
        }
    }
            
    /**
     * Método que altera a frequencia respiratoria do paciente,
     * baseando-se no nível de risco que o valor da frequencia respiratoria apresenta.
     * @param freqRespiratoria - nova frequencia respiratoria 
     */
    public void setFreqRespiratoria(float freqRespiratoria) {
       //Confere a frequencia respiratória atual antes da mudança e subtrai o valor na gravidade
        if(this.freqRespiratoria!=0){
            if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
                this.gravidade-=1; //Subtrai 1 na gravidade
            }
            else if (this.freqRespiratoria>=21){
                this.gravidade-=3;//Subtrai 3 na gravidade
            }
        }     
       //Altera a frequencia Respiratória para a nova
        this.freqRespiratoria = freqRespiratoria;
        //Define a nova gravidade
        if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
            this.gravidade+=1;//Soma 1 na gravidade
        }
        else if (this.freqRespiratoria>=21){
            this.gravidade+=3;//Soma 3 na gravidade
        }
        else{
            this.gravidade+=0;//Soma nenhuma na gravidade
        }
    }
    
    /**
    * Método que altera a pressao do paciente,
    * baseando-se no nível de risco que o valor da pressao apresenta.
    * @param pressao - a nova pressao
    */
    public void setPressao(float pressao) {
        //Confere a presao antes da mudança e subtrai o valor na gravidade
        if(this.pressao!=0){
            if(this.pressao>=81 && this.pressao<=100){
                this.gravidade-=1; //Subtrai 1 na gravidade
            }
            else if (this.pressao<=80){
                this.gravidade-=3; //Subtrai 3 na gravidade
            }
        }
        //Altera a pressao para a nova
        this.pressao = pressao;
        //Define a nova gravidade
        if(this.pressao>=81 && this.pressao<=100){
            this.gravidade+=1; //Soma 1 na gravidade
        }
        else if (this.pressao>0 && this.pressao<=80){
            this.gravidade+=3; //Soma 3 na gravidade
        }
        else{
            this.gravidade+=0; //Não soma nenhum valor
        }
    }

    /**
    * Método que altera a saturação do oxigenio do paciente,
    * baseando-se no nível de risco que o valor da saturação do oxigenio apresenta
    * @param satOxigenio - a nova saturacao do oxigenio
    */        
    public void setSatOxigenio(float satOxigenio) {
        //Confere a saturação antes da mudança e subtrai o valor na gravidade
        if(this.satOxigenio!=0){
            if(this.satOxigenio>80 && this.satOxigenio<90){
                this.gravidade-=1; //Subtrai 1 na gravidade
            }
            else if (this.satOxigenio<=80){
                this.gravidade-=3; //Subrai 3 na gravidade
            }
        }
        //Altera a saturação para a atual
        this.satOxigenio = satOxigenio;
        //Define a nova gravidade
        if(this.satOxigenio>80 && this.satOxigenio<90){
            this.gravidade+=1; //Soma 1 na gravidade
        }
        else if (this.satOxigenio>0 && this.satOxigenio<=80){
            this.gravidade+=3;//Soma 3 na gravidade
        }
        else{
            this.gravidade+=0; //Soma 0 na gravidade
        }
    }
    
}
