package cronometro;

import cronometro.vistasl.VistaCronometro;
import cronometro.vistasl.VistaPrincipal;
import cronometro.vistasl.VistaTemporizador;

public class Main {

    public static void main(String[] args) {
        VistaPrincipal miVistaPrincipal = new VistaPrincipal();
        //miVistaPrincipal.setVisible(true);
        VistaTemporizador v = new VistaTemporizador(miVistaPrincipal);
        v.setVisible(true);
    }

}
