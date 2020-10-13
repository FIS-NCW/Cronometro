package cronometro.vistasl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author asus
 */
public class VistaTemporizador extends JFrame implements ActionListener, Runnable {

    private JButton btnMenu;
    private VistaPrincipal miVistaPrincipal;
    private JButton iniciar;
    private JButton pausar;
    private JButton reiniciar;

    private JLabel hh;
    private JLabel mm;
    private JLabel ss;
    private int horas = 0;
    private int minutos = 0;
    private int segundos = 0;

    private JTextField txtHora;
    private JTextField txtMinuto;
    private JTextField txtSegundo;

    private boolean inirea = false;

    Thread hilo;

    public VistaTemporizador(VistaPrincipal miVistaPrincipal) {
        this.miVistaPrincipal = miVistaPrincipal;
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Temporizador");
        setLayout(null);

        btnMenu = new JButton("Menú");
        add(btnMenu);
        btnMenu.setBounds(10, 300, 100, 50);
        btnMenu.addActionListener(this);

        iniciar = new JButton("Iniciar");
        pausar = new JButton("Pausar");
        reiniciar = new JButton("Reiniciar");

        hh = new JLabel(horas + " : ");
        mm = new JLabel(minutos + " : ");
        ss = new JLabel(segundos + "");

        txtHora = new JTextField("hh");
        txtMinuto = new JTextField("mm");
        txtSegundo = new JTextField("ss");

        add(hh);
        add(mm);
        add(ss);

        add(txtHora);
        add(txtMinuto);
        add(txtSegundo);

        add(iniciar);
        add(pausar);
        add(reiniciar);

        iniciar.addActionListener(this);
        pausar.addActionListener(this);
        reiniciar.addActionListener(this);

        hh.setBounds(60, 10, 50, 20);
        mm.setBounds(90, 10, 50, 20);
        ss.setBounds(120, 10, 50, 20);

        txtHora.setBounds(80, 50, 50, 30);
        txtMinuto.setBounds(140, 50, 50, 30);
        txtSegundo.setBounds(200, 50, 50, 30);

        iniciar.setBounds(10, 250, 100, 30);
        pausar.setBounds(115, 250, 100, 30);
        reiniciar.setBounds(220, 250, 100, 30);

        hilo = new Thread(this);
        hilo.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnMenu)) {
            this.hide();
            this.miVistaPrincipal.setVisible(true);
        } else if (e.getSource().equals(iniciar)) {
            inirea = true;
            iniciar.setEnabled(false);
            horas = Integer.parseInt(txtHora.getText());
            minutos = Integer.parseInt(txtMinuto.getText());
            segundos = Integer.parseInt(txtSegundo.getText());
        } else if (e.getSource().equals(pausar)) {
            inirea = false;
            iniciar.setEnabled(true);
        } else if (e.getSource().equals(reiniciar)) {
            inirea = false;
            segundos = 0;
            minutos = 0;
            horas = 0;
            iniciar.setEnabled(true);

        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                hilo.sleep(1);
                if (inirea) {
                    if(segundos==0 && minutos==0 && horas==0){
                        JOptionPane.showMessageDialog(null, "Aviso de fin temporizador");
                    }else{
                        segundos--;
                    }
                    
                }

                ss.setText(segundos + "");
                mm.setText(minutos + "  : ");
                hh.setText(horas + "  : ");

                if (segundos < 0) {
                    segundos = 59;
                    minutos--;
                    if (minutos < 0) {
                        minutos = 59;
                        horas--;
                    }

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(VistaCronometro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
