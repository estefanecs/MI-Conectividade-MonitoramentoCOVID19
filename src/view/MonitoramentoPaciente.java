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
package view;

import controler.Comunicador;
import controler.ControladorInterface;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Paciente;
import org.json.JSONException;

public class MonitoramentoPaciente extends javax.swing.JFrame implements Runnable{
    
    private ControladorInterface controlador; //controlador da interfaces
    private String pacienteMonitorado; //nome do paciente
    private Semaphore semaforo; //Semaforo
    private Comunicador comunicador; //comunicador
    
    /**
     * Cria uma nova tela de MonitoramentoPaciente
     * @param nome - nome do paciente a ser monitorado
     */
    public MonitoramentoPaciente(String nome) {
        initComponents();
        this.setLocationRelativeTo(null);
        controlador = ControladorInterface.getInstancia(); //Obtem a instancia do controlador
        comunicador = Comunicador.getInstancia();
        this.pacienteMonitorado = nome; //Salva o nome do paciente
        //Busca na lista, o paciente a ser monitorado
        Paciente paciente = controlador.buscarPaciente(pacienteMonitorado);
        if(paciente!=null){
            //Exibe na tela os dados do paciente
            nomePaciente.setText(paciente.getNome());
            cpfPaciente.setText(paciente.getCpf());
            gravPaciente.setText(" " +paciente.getGravidade());
            pressaoPaciente.setText(" " +paciente.getPressao()+" mmHg");
            fcPaciente.setText(" "+paciente.getFreqCardiaca()+" bpm");
            respPaciente.setText(" " +paciente.getFreqRespiratoria()+" mpm");
            saturPaciente.setText(" "+paciente.getSatOxigenio()+"%");
            tempPaciente.setText(" "+paciente.getTemperatura() + "ºC");
            //Cria a thread e inicializa
            Thread t =new Thread(this);
            t.start();
        }
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nomePaciente = new javax.swing.JLabel();
        cpfPaciente = new javax.swing.JLabel();
        paciente = new javax.swing.JLabel();
        cpf = new javax.swing.JLabel();
        Titulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        temperatura = new javax.swing.JLabel();
        tempPaciente = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        freqCardiaca = new javax.swing.JLabel();
        fcPaciente = new javax.swing.JLabel();
        freqCardiaca1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pressao = new javax.swing.JLabel();
        pressaoPaciente = new javax.swing.JLabel();
        pressao1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        saturacao = new javax.swing.JLabel();
        saturPaciente = new javax.swing.JLabel();
        saturacao1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        respiracao = new javax.swing.JLabel();
        respPaciente = new javax.swing.JLabel();
        respiracao1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        gravidade = new javax.swing.JLabel();
        gravPaciente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 0));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(675, 500));

        nomePaciente.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        nomePaciente.setText("jLabel1");

        cpfPaciente.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cpfPaciente.setText("jLabel1");

        paciente.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        paciente.setText("PACIENTE:");

        cpf.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        cpf.setText("CPF:");

        Titulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("SINAIS VITAIS");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));
        jPanel2.setPreferredSize(new java.awt.Dimension(213, 109));

        temperatura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        temperatura.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        temperatura.setText("TEMPERATURA:");

        tempPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        tempPaciente.setForeground(new java.awt.Color(255, 102, 0));
        tempPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tempPaciente.setText("0ºC");
        tempPaciente.setPreferredSize(new java.awt.Dimension(213, 193));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(temperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(tempPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(temperatura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tempPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));
        jPanel3.setPreferredSize(new java.awt.Dimension(213, 193));

        freqCardiaca.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        freqCardiaca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        freqCardiaca.setText("FREQUÊNCIA ");

        fcPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        fcPaciente.setForeground(new java.awt.Color(255, 102, 0));
        fcPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fcPaciente.setText("0 bpm");

        freqCardiaca1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        freqCardiaca1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        freqCardiaca1.setText("CARDÍACA:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(freqCardiaca1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(freqCardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fcPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(freqCardiaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(freqCardiaca1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fcPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));
        jPanel4.setPreferredSize(new java.awt.Dimension(213, 193));

        pressao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        pressao.setText("PRESSÃO ARTERIAL");

        pressaoPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        pressaoPaciente.setForeground(new java.awt.Color(255, 102, 0));
        pressaoPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pressaoPaciente.setText("0 mmHg");

        pressao1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        pressao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pressao1.setText("SISTÓLICA:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pressao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pressao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(pressaoPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pressao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pressao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pressaoPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));
        jPanel5.setPreferredSize(new java.awt.Dimension(213, 193));

        saturacao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saturacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saturacao.setText(" DE OXIGÊNIO:");

        saturPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        saturPaciente.setForeground(new java.awt.Color(255, 102, 0));
        saturPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saturPaciente.setText("0%");

        saturacao1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saturacao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saturacao1.setText("SATURAÇÃO");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saturacao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saturacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(saturPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saturacao1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saturacao)
                .addGap(18, 18, 18)
                .addComponent(saturPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));

        respiracao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        respiracao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        respiracao.setText("FREQUÊNCIA ");

        respPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        respPaciente.setForeground(new java.awt.Color(255, 102, 0));
        respPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        respPaciente.setText("0 mpm");

        respiracao1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        respiracao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        respiracao1.setText(" RESPIRATÓRIA:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(respPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(respiracao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(respiracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(respiracao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(respiracao1)
                .addGap(18, 18, 18)
                .addComponent(respPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        respiracao.getAccessibleContext().setAccessibleName("FREQUÊNCIA\n RESPIRATÓRIA:");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 153, 0), new java.awt.Color(255, 153, 0), null, new java.awt.Color(204, 204, 204)));

        gravidade.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        gravidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gravidade.setText("GRAVIDADE");

        gravPaciente.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        gravPaciente.setForeground(new java.awt.Color(255, 102, 0));
        gravPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gravPaciente.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gravidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(gravPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(gravidade)
                .addGap(18, 18, 18)
                .addComponent(gravPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(paciente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(cpf)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cpfPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paciente)
                    .addComponent(nomePaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cpf)
                    .addComponent(cpfPaciente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel cpf;
    private javax.swing.JLabel cpfPaciente;
    private javax.swing.JLabel fcPaciente;
    private javax.swing.JLabel freqCardiaca;
    private javax.swing.JLabel freqCardiaca1;
    private javax.swing.JLabel gravPaciente;
    private javax.swing.JLabel gravidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel nomePaciente;
    private javax.swing.JLabel paciente;
    private javax.swing.JLabel pressao;
    private javax.swing.JLabel pressao1;
    private javax.swing.JLabel pressaoPaciente;
    private javax.swing.JLabel respPaciente;
    private javax.swing.JLabel respiracao;
    private javax.swing.JLabel respiracao1;
    private javax.swing.JLabel saturPaciente;
    private javax.swing.JLabel saturacao;
    private javax.swing.JLabel saturacao1;
    private javax.swing.JLabel tempPaciente;
    private javax.swing.JLabel temperatura;
    // End of variables declaration//GEN-END:variables

    /**
     * Método para a cada intervalo de tempo, fazer a requisicao da atualizacao 
     * dos dados do paciente. Após receber os dados, atualiza na tela e verifica 
     * o nível de gravidade, para enviar uma requisao POST com a mensagem de alerta
     * ao paciente.
     */
    @Override
    public void run() {
        int delay = 3000;   // delay de 3 seg.
        int interval = 1000;  // intervalo de 1 seg.
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String retorno;
                try {
                    //Envia a requisicao para atualizar os sinais vitais
                    retorno = comunicador.getDados("GET/atualizarSinais");
                    if (!retorno.equals("nula")) { //Se forem devolvidos dados
                        //Busca o paciente na lista, o paciente que está sendo monitorado
                        Paciente paciente = controlador.buscarPaciente(pacienteMonitorado);
                        if (paciente != null) {//Se encontrou o paciente na lista
                            //Atualiza as informacoes na tela
                            gravPaciente.setText(" " + paciente.getGravidade());
                            pressaoPaciente.setText(" " + paciente.getPressao() + " mmHg");
                            fcPaciente.setText(" " + paciente.getFreqCardiaca() + " bpm");
                            respPaciente.setText(" " + paciente.getFreqRespiratoria() + " mpm");
                            saturPaciente.setText(" " + paciente.getSatOxigenio() + "%");
                            tempPaciente.setText(" " + paciente.getTemperatura() + "ºC");
                            System.out.println("temperatura: "+paciente.getTemperatura());
                            System.out.println("Interface: Informações atualizadas");

                            if (paciente.getGravidade() >= 3 && paciente.getGravidade() < 5) {
                                //Cria a string com o nome do paciente e mensagem de alerta
                                String informacao = pacienteMonitorado + ":Você está em sinal de alerta.\nProcure uma unidade de saúde e realize o teste";
                                //Envia a requisição para o servidor
                                comunicador.postDados("POST/notificarPaciente/" + informacao);
                            } else if (paciente.getGravidade() >= 5) {
                                //Cria a string com o nome do paciente e mensagem de alerta
                                String informacao = pacienteMonitorado + ":Seu estado de saúde é grave para a covid.\nProcure uma unidade de saúde urgentemente";
                                //Envia a requisição para o servidor
                                comunicador.postDados("POST/notificarPaciente/" + informacao);
                            }
                        } else {
                            System.out.println("Paciente n encontrado");
                        }
                        Thread.sleep(3000);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MonitoramentoPaciente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(MonitoramentoPaciente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MonitoramentoPaciente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MonitoramentoPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, delay, interval);

    }
}
