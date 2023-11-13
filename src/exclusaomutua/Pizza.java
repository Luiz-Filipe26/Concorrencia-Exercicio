/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exclusaomutua;

/**
 *
 * @author Luiz
 */
public abstract class Pizza {
    protected int pedacosRestantes;
    protected String tipo;
    protected final Object lock = new Object();
    
    public String getTipo() {
        return tipo;
    }
    public int getPedaçosRestantes() {
        return pedacosRestantes;
    }
    public void adicionarPedacos(int pedacos) {
        pedacosRestantes += pedacos;
    }
    
    public abstract void entregar(int pedaços);
}