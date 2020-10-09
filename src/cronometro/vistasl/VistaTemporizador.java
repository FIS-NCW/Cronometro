/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro.vistasl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author asus
 */
public class VistaTemporizador extends JFrame implements ActionListener {

    private JButton btnMenu;
    private VistaPrincipal miVistaPrincipal;

    public VistaTemporizador(VistaPrincipal miVistaPrincipal) {
        this.miVistaPrincipal=miVistaPrincipal;
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Temporizador");
        setLayout(null);
        
        btnMenu = new JButton("Menú");
        add(btnMenu);
        btnMenu.setBounds(10, 300, 100, 50);
        btnMenu.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnMenu)) {
            this.hide();
            this.miVistaPrincipal.setVisible(true);
        }
    }
}
