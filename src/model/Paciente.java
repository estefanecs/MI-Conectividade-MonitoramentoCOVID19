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
        System.out.println("TEMPERATURA RECEBIDA "+temperatura);
       //Confere a temperatura antes da mudança e subtrai o valor na gravidade
       if(this.temperatura!=0){
            if(this.temperatura>38 && this.temperatura<=38.5){
                this.gravidade-=1;
                System.out.println("Subtrai 1 na tem");
            }
            else if (this.temperatura>=38.6){
                this.gravidade-=3;
                System.out.println("Subtrai 3 na temp");
            }   
       } 
       //Altera a temparatura para a nova, e define a nova gravidade
        this.temperatura = temperatura;
        if(this.temperatura>38 && this.temperatura<=38.5){
            this.gravidade+=1;
            System.out.println("Adicionei 1 na tem");
        }
        else if (this.temperatura>=38.6){
            this.gravidade+=3;
            System.out.println("Adicionei 3 na tem");
        }
        else{
            this.gravidade+=0;
            System.out.println("adicionei 0 na tem");
        }
    }

    public void setFreqCardiaca(float freqCardiaca) {
        System.out.println("CARDIACA RECEBIDA "+freqCardiaca);
       //Confere a frequencia cardiaca antes da mudança e subtrai o valor na gravidade
        if(this.freqCardiaca!=0){
            if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
                this.gravidade-=1;
                System.out.println("Subtrai 1 em card");
            }
            else if (this.freqCardiaca>=111){
                this.gravidade-=3;
                System.out.println("Subtrai 3 em card");
            }
        }
       //Altera a frequencia cardiaca para a nova, e define a nova gravidade
        this.freqCardiaca = freqCardiaca;
        if(this.freqCardiaca>=101 && this.freqCardiaca<=110){
            this.gravidade+=1;
            System.out.println("ADD 1 na card");
        }
        else if (this.freqCardiaca>=111){
            this.gravidade+=3;
            System.out.println("add 3 na card");
        }
        else{
            this.gravidade+=0;
            System.out.println("add 0 na card");
        }
    }

    public void setFreqRespiratoria(float freqRespiratoria) {
        System.out.println("RESP RECEBIDA "+freqRespiratoria);
       //Confere a frequencia respiratória atual antes da mudança e subtrai o valor na gravidade
        if(this.freqRespiratoria!=0){
            if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
                this.gravidade-=1;
                System.out.println("Subtrai 1 na resp");
            }
            else if (this.freqRespiratoria>=21){
                this.gravidade-=3;
                System.out.println("subtrai 3 na resp");
            }
        }     
       //Altera a frequencia Respireatória para a nova, e define a nova gravidade
        this.freqRespiratoria = freqRespiratoria;
        if(this.freqRespiratoria>=15 && this.freqRespiratoria<=20){
            this.gravidade+=1;
            System.out.println("add 1 na resp");
        }
        else if (this.freqRespiratoria>=21){
            this.gravidade+=3;
            System.out.println("add 3 na resp");
        }
        else{
            this.gravidade+=0;
            System.out.println("add 0 na resp");
        }
    }

    public void setPressao(float pressao) {
        System.out.println("PRESSAO RECEBIDA "+pressao);
        //Confere a presao antes da mudança e subtrai o valor na gravidade
        if(this.pressao!=0){
            if(this.pressao>=81 && this.pressao<=100){
                this.gravidade-=1;
                System.out.println("subtrai 1 na pressao");
            }
            else if (this.pressao<=80){
                this.gravidade-=3;
                System.out.println("subtrai 3 na pressao");
            }
        }
        //Altera a pressao para a nova, e define a nova gravidade
        this.pressao = pressao;
        if(this.pressao>=81 && this.pressao<=100){
            this.gravidade+=1;
            System.out.println("add 1 na pressao");
        }
        else if (this.pressao>0 && this.pressao<=80){
            this.gravidade+=3;
            System.out.println("add 3 na pressao");
        }
        else{
            this.gravidade+=0;
            System.out.println("add 0 na pressao");
        }
    }

    public void setSatOxigenio(float satOxigenio) {
        System.out.println("OXIGENIO RECEBIDA "+satOxigenio);
        //Confere a saturação antes da mudança e subtrai o valor na gravidade
        if(this.satOxigenio!=0){
            if(this.satOxigenio>80 && this.satOxigenio<90){
                this.gravidade-=1;
                System.out.println("subtrai 1 no oxi");
            }
            else if (this.satOxigenio<=80){
                this.gravidade-=3;
                System.out.println("subtrai 3 no oxi");
            }
        }
        //Altera a saturação para a atual, e define a nova gravidade
        this.satOxigenio = satOxigenio;
        if(this.satOxigenio>80 && this.satOxigenio<90){
            this.gravidade+=1;
            System.out.println("add 1 no oxi");
        }
        else if (this.satOxigenio>0 && this.satOxigenio<=80){
            this.gravidade+=3;
            System.out.println("add 3 no oxi");
        }
        else{
            this.gravidade+=0;
            System.out.println("add 0 no oxi");
        }
    }
    
}
