/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exclusaomutua;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiz
 */
public class PizzaCalabresa extends Pizza {

    public PizzaCalabresa() {
        tipo = "calabresa";
        pedacosRestantes = Mesa.TAMPIZZA;
    }

    @Override
    public void entregar(int pedacos) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PizzaCalabresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        pedacosRestantes -= pedacos;
        InteracaoUsuario.getInstancia().entregarAoUsuario(tipo, pedacos);
    }
    
}
