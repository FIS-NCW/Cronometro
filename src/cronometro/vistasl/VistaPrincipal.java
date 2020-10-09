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
public class VistaPrincipal extends JFrame implements ActionListener{
    private VistaCronometro miVistaCronometro;
    private VistaTemporizador miVistaTemporizador;
    private JButton btnCronometro;
    private JButton btnTemporizador;
    
    public VistaPrincipal(){
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Menú principal");
        setLayout(null);
        
        miVistaCronometro=new VistaCronometro(this);
        miVistaTemporizador=new VistaTemporizador(this);
        
        btnCronometro=new JButton("Cronómetro");
        add(btnCronometro);
        btnCronometro.setBounds(10,80,265,50);
        
        btnTemporizador=new JButton("Temporizador");
        add(btnTemporizador);
        btnTemporizador.setBounds(10,180,265,50);
        
        btnCronometro.addActionListener(this);
        btnTemporizador.addActionListener(this);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnCronometro)){
            abrirCronometro();
        }else if(e.getSource().equals(btnTemporizador)){
            abrirTemporizador();
        }
    }

    private void abrirCronometro() {
        this.hide();
        this.miVistaCronometro.setVisible(true);
    }

    private void abrirTemporizador() {
        this.hide();
        this.miVistaTemporizador.setVisible(true);
    }
    
    
    
}
