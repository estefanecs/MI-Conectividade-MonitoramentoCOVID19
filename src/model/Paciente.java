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

    public Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.gravidade=0;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public float getFreqCardiaca() {
        return freqCardiaca;
    }

    public float getFreqRespiratoria() {
        return freqRespiratoria;
    }

    public float getPressao() {
        return pressao;
    }

    public float getSatOxigenio() {
        return satOxigenio;
    }
    
    public int getGravidade(){
        return gravidade;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setTemperatura(double temperatura) {
       //Confere a temperatura antes da mudança e subtrai o valor na gravidade
       if(this.temperatura!=0){
            if(this.temperatura>38 && this.temperatura<=38.5){
                this.gravidade-=1;
            }
            else if (this.temperatura>=38.6){
                this.gravidade-=3;
            }   
       } 
       //Altera a temparatura para a nova, e define a nova gravidade
        this.temperatura = temperatura;
        if(this.temperatura>38 && this.temperatura<=38.5){
            this.gravidade+=1;
        }
        else if (this.temperatura>=38.6){
            this.gravidade+=3;
        }
        else{
            this.gravidade+=0;
        }
    }

    public void setFreqCardiaca(float freqCardiaca) {
       //Confere a frequencia cardiaca antes da mudança e subtrai o valor na gravidade
        if(this.freqCardiaca!=0){
            if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
                this.gravidade-=1;
            }
            else if (this.freqCardiaca>=111){
                this.gravidade-=3;
            }
        }
       //Altera a frequencia cardiaca para a nova, e define a nova gravidade
        this.freqCardiaca = freqCardiaca;
        if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
            this.gravidade+=1;
        }
        else if (this.freqCardiaca>=111){
            this.gravidade+=3;
        }
        else{
            this.gravidade+=0;
        }
    }

    public void setFreqRespiratoria(float freqRespiratoria) {
       //Confere a frequencia respiratória atual antes da mudança e subtrai o valor na gravidade
        if(this.freqRespiratoria!=0){
            if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
                this.gravidade-=1;
            }
            else if (this.freqRespiratoria>=21){
                this.gravidade-=3;
            }
        }     
       //Altera a frequencia Respireatória para a nova, e define a nova gravidade
        this.freqRespiratoria = freqRespiratoria;
        if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
            this.gravidade+=1;
        }
        else if (this.freqRespiratoria>=21){
            this.gravidade+=3;
        }
        else{
            this.gravidade+=0;
        }
    }

    public void setPressao(float pressao) {
        //Confere a presao antes da mudança e subtrai o valor na gravidade
        if(this.pressao!=0){
            if(this.pressao>=81 && this.pressao<=100){
                this.gravidade-=1;
            }
            else if (this.pressao<=80){
                this.gravidade-=3;
            }
        }
        //Altera a pressao para a nova, e define a nova gravidade
        this.pressao = pressao;
        if(this.pressao>=81 && this.pressao<=100){
            this.gravidade+=1;
        }
        else if (this.pressao<=80){
            this.gravidade+=3;
        }
        else{
            this.gravidade+=0;
        }
    }

    public void setSatOxigenio(float satOxigenio) {
        //Confere a saturação antes da mudança e subtrai o valor na gravidade
        if(this.satOxigenio!=0){
            if(this.satOxigenio>80 && this.satOxigenio<90){
                this.gravidade-=1;
            }
            else if (this.satOxigenio<=80){
                this.gravidade-=3;
            }
        }
        //Altera a saturação para a atual, e define a nova gravidade
        this.satOxigenio = satOxigenio;
        if(this.satOxigenio>80 && this.satOxigenio<90){
            this.gravidade+=1;
        }
        else if (this.satOxigenio<=80){
            this.gravidade+=3;
        }
        else{
            this.gravidade+=0;
        }
    }
    
}
