/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.servlet.model;


import static java.lang.Math.random;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;



/**
 *
 *
 * El número que actualmente debe adivinar (debe ser un número aleatorio).
 *
 * El número de intentos realizados.
 *
 * El premio acumulado hasta el momento.
 *
 * El estado del juego, que sería una cadena de texto que indica si ya ganó o
 * no, y si ganó de cuanto es el premio.

 */
@ManagedBean(name = "guessBean")
@ApplicationScoped
public class BackingBean {
    
    private int guessNumber;
    private int attempts;
    private int price;
    private String state;


    
    
    
    public BackingBean(){      
        Random randNumber = new Random();
        guessNumber = 1+randNumber.nextInt(10);
        attempts = 0;
        price=100000;
        state="Keep Guessing";        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    public void guess(int inpiutNumber){
        attempts += 1;
        if (inpiutNumber == guessNumber){
            state="YOU WIN!";    
            
        }                
        else{
            price-=10000;
        }        
        if((price-10000)<0){            
            state="YOU LOSE!";
        }                       
    }    
    
    public void restart (){
        Random randNumber = new Random();
        guessNumber = 1+randNumber.nextInt(10);
        attempts = 0;
        price=100000;
        state="Keep Guessing";  
    }


    public int getGuessNumber() {
        return guessNumber;
    }

    

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
