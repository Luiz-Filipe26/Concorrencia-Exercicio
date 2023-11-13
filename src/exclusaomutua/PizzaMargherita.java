/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exclusaomutua;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author Luiz
 */
public class PizzaMargherita extends Pizza  {

    public PizzaMargherita() {
        tipo = "margherita";
        pedacosRestantes = Mesa.TAMPIZZA;
    }

    @Override
    public void entregar(int pedacos) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(PizzaMargherita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        pedacosRestantes -= pedacos;
        InteracaoUsuario.getInstancia().entregarAoUsuario(tipo, pedacos);
    }
    
}
