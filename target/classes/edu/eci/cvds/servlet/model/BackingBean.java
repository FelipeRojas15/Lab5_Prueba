/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.servlet.model;


import static java.lang.Math.random;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.*;



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
//@ApplicationScoped
public class BackingBean {
    
    private int guessNumber;
    private int attempts;
    private int price;
    private String state;
    private int inputNumber;
    private String attemptFails;


    
    
    
    public BackingBean(){ 
        Random randNumber = new Random();
        guessNumber = 1+randNumber.nextInt(10);
        attempts = 0;
        price=100000;
        state="Keep Guessing"; 
        attemptFails="";
    }
    
    public void guess(int inputNumber){
        
        if (!state.equals("You win")){
            if (inputNumber==guessNumber & price>0){
                attempts +=1;
                setState("You win");

            }
            else if (inputNumber!=guessNumber & price>0){
                attempts +=1;
                price -=10000;
                setState("Numero Incorrecto");
            }
            else if (price<=0){
                setState("no tiene mas intentos");
            }
        }else{
            setState("Ya gano");
        }

       
    }
    public void guess(String inputNumber){
        try{
            
            this.inputNumber = Integer.parseInt(inputNumber);  
            guess(this.inputNumber);
        
        }catch(Exception e){
            setState("INVALID INPUT, TRY ONLY WITH NUMBERS");
        }
    }
    public void restart (){
        Random randNumber = new Random();
        guessNumber = 1+randNumber.nextInt(10);
        attempts = 0;
        price=100000;
        state="Keep Guessing"; 
    }
    public String getattemptFails(){
        return attemptFails;
    }
    
    public void setattemptFails(String intentosFallidos){
        this.attemptFails = intentosFallidos;
    }
    public int getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(int inputNumber) {
        this.inputNumber = inputNumber;
    }    

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
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
