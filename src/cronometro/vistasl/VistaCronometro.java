package cronometro.vistasl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class VistaCronometro extends JFrame implements ActionListener, Runnable {

    private JButton btnMenu;
    private JButton iniciar;
    private JButton pausar;
    private JButton registrar;
    private JButton reiniciar;

    private DefaultTableModel modelo;
    private JTable tabla;
    private JScrollPane scroll;

    private VistaPrincipal miVistaPrincipal;
    private JLabel hh;
    private JLabel mm;
    private JLabel ss;
    private int horas = 12;
    private int minutos = 59;
    private int segundos = 50;

    private boolean inirea = false;

    Thread hilo;

    public VistaCronometro(VistaPrincipal miVistaPrincipal) {
        this.miVistaPrincipal = miVistaPrincipal;
        setSize(460, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cronómetro");
        setLayout(null);

        btnMenu = new JButton("Menú");
        add(btnMenu);
        btnMenu.setBounds(10, 300, 100, 50);
        btnMenu.addActionListener(this);

        iniciar = new JButton("Iniciar");
        pausar = new JButton("Pausar");
        registrar = new JButton("Registrar");
        reiniciar = new JButton("Reiniciar");

        hh = new JLabel(horas + " : ");
        mm = new JLabel(minutos + " : ");
        ss = new JLabel(segundos + "");

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);

        modelo.addColumn("Horas");
        modelo.addColumn("Minutos");
        modelo.addColumn("Segundos");
        scroll = new JScrollPane(tabla);
        add(scroll);
        scroll.setBounds(10, 40, 420, 200);

        add(hh);
        add(mm);
        add(ss);

        add(iniciar);
        add(pausar);
        add(registrar);
        add(reiniciar);

        iniciar.addActionListener(this);
        pausar.addActionListener(this);
        registrar.addActionListener(this);
        reiniciar.addActionListener(this);
        reiniciar.setEnabled(false);

        hh.setBounds(60, 10, 50, 20);
        mm.setBounds(90, 10, 50, 20);
        ss.setBounds(120, 10, 50, 20);

        iniciar.setBounds(10, 250, 100, 30);
        pausar.setBounds(115, 250, 100, 30);
        registrar.setBounds(220, 250, 100, 30);
        reiniciar.setBounds(325, 250, 100, 30);

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
            reiniciar.setEnabled(false);
        } else if (e.getSource().equals(pausar)) {
            inirea = false;
            iniciar.setEnabled(true);
            reiniciar.setEnabled(true);
        } else if (e.getSource().equals(reiniciar)) {
            inirea = false;
            segundos = 0;
            minutos = 0;
            horas = 0;

            int filas = modelo.getRowCount();
            for (int i = 0; i < filas; i++) {
                modelo.removeRow(0);
            }

        } else if (e.getSource().equals(registrar)) {
            Object registro[] = {horas, minutos, segundos};
            modelo.addRow(registro);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                hilo.sleep(1000);

                ss.setText(segundos + "");
                mm.setText(minutos + "  : ");
                hh.setText(horas + "  : ");

                if (segundos == 59) {
                    segundos = -1;
                    minutos++;
                    if (minutos == 60) {
                        minutos = 0;
                        horas++;
                    }

                }
                if (inirea) {
                    segundos++;
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(VistaCronometro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
