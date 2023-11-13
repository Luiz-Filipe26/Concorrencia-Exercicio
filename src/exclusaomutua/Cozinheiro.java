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
public class Cozinheiro extends Thread {
    private static Cozinheiro cozinheiro;
    private final Object lock = new Object();
    private boolean comecar = false;
    private boolean parar = false;
    private int quantidadeCozinhar;
    private Pizza pizza;
    
    public synchronized static Cozinheiro getInstancia() {
        if(cozinheiro == null) {
            cozinheiro = new Cozinheiro();
        }
        return cozinheiro;
    }
    
    private Cozinheiro() {
    }
    
    public void cozinhar(Pizza pizza, int quantidade) {
        try {
            Mesa.cozinhando.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cozinheiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        comecar = true;
        quantidadeCozinhar = quantidade;
        this.pizza = pizza;
        synchronized(lock) {
            lock.notify();
        }
    }
    
    public void parar() {
        parar = true;
        synchronized(lock) {
            lock.notify();
        }
    }
    
    @Override
    public void run() {
        while(true) {
            esperar();
            if(parar) {
                break;
            }
            
            
            
            try {
                Thread.sleep(1000 * quantidadeCozinhar);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cozinheiro.class.getName()).log(Level.SEVERE, null, ex);
            }
            pizza.adicionarPedacos(quantidadeCozinhar);
            quantidadeCozinhar = 0;
            Mesa.cozinhando.release();
        }
    }
    
    public void esperar() {
        while(!comecar) {
            synchronized(lock) {
                try {
                    lock.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Cozinheiro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        comecar = false;
    }
}
