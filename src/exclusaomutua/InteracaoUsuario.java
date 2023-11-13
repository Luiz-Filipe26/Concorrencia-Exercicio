/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exclusaomutua;

import java.util.Scanner;

/**
 *
 * @author Luiz
 */
public class InteracaoUsuario {
    public static InteracaoUsuario interacaoUsuario;
    private Mesa mesa;
    
    public synchronized static InteracaoUsuario getInstancia() {
        if(interacaoUsuario == null) {
            interacaoUsuario = new InteracaoUsuario();
        }
        return interacaoUsuario;
    }
    
    private InteracaoUsuario() {
        mesa = new Mesa();
    }
    
    public void comecar() {
        String pizza;
        String sair = "N";
        int quantidade;
        Scanner teclado = new Scanner(System.in);
        while(!sair.equals("S")) {
            System.out.print("Digite a pizza que voce quer: ");
            pizza = teclado.nextLine();
            
            System.out.print("Digite a quantidade que voce quer: ");
            quantidade = teclado.nextInt();
            teclado.nextLine();
            
            mesa.solicitarPizza(quantidade, pizza);
            
            System.out.print("Digite S para sair e qualquer tecla para continuar: ");
            sair = teclado.nextLine();
        }
    }
    
    public void entregarAoUsuario(String pizza, int quantidade) {
        System.out.println("Pizza " + pizza + " de " + quantidade + " pedacos entregue!");
    }
}
