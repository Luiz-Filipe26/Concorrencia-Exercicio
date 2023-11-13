/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exclusaomutua;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiz
 */
public class Mesa {
    private Map<String, Pizza> pizzas = new HashMap<>();
    private Cozinheiro cozinheiro;
    public static Semaphore cozinhando = new Semaphore(1);
    public static final int TAMPIZZA = 8;
    
    public Mesa() {
        pizzas.put("calabresa", new PizzaCalabresa());
        pizzas.put("frango catupiry", new PizzaFrangoCatupiry());
        pizzas.put("margherita", new PizzaMargherita());
        pizzas.put("portuguesa", new PizzaPortuguesa());
        cozinheiro = Cozinheiro.getInstancia();
    }
    
    public void solicitarPizza(int quantidadeSolicitada, String tipoPizza) {
        Pizza pizzaSolicitada = pizzas.get(tipoPizza);
        int quantidadeAtual = pizzaSolicitada.getPedaçosRestantes();
        
        if(quantidadeAtual < quantidadeSolicitada) {
            int quantidadeCozinhar = (quantidadeSolicitada - quantidadeAtual) + 8;
            
            cozinheiro.cozinhar(pizzaSolicitada, quantidadeCozinhar);
            try {
                cozinhando.acquire();
                cozinhando.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
            }
            pizzaSolicitada.entregar(quantidadeSolicitada);
            
        }
        else if(quantidadeAtual == quantidadeSolicitada) {
            pizzaSolicitada.entregar(quantidadeSolicitada);
            cozinheiro.cozinhar(pizzaSolicitada, TAMPIZZA);
        }
        else {
            pizzaSolicitada.entregar(quantidadeSolicitada);
        }
    }
}
