/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.servlet.model;

import java.util.Random;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * La clase ABean sirve para administrar nuestra aplicacion para adivinar un numero 
 */
@ManagedBean(name="guessBean")
@SessionScoped
public class BackingBean{
   private Random r = new Random();
   private int numeroAdivinar;
   private int premio;
   private int numeroIntentos=0;
   private String mensajeActual;
   private List<Integer> intentos;
   private boolean esGanador;

    /**
     *Constructor
     */
    public BackingBean(){
       this.numeroAdivinar = r.nextInt(30)+1;
       this.premio = 100000;
       this.numeroIntentos = 0;
       this.mensajeActual = "Aun no ha ganado el juego.";
       this.intentos = new ArrayList<Integer>();
       this.esGanador = false;
   }

    /**
     * Retorna el numero que se debe adivinar
     * @return
     */
    public int getNumeroAdivinar(){
       return numeroAdivinar;
   }

    /**
     * Asigna un nuevo numero a adivinar
     * @param numeroAdivinar
     */
    public void setNumeroAdivinar(int numeroAdivinar){
       this.numeroAdivinar = numeroAdivinar;
   }

    /**
     * Retorna el valor actual del premio.
     * @return
     */
    public int getPremio(){
       return premio;
   }

    /**
     * Asigna un nuevo valor al premio
     * @param premio
     */
    public void setPremio(int premio){
       this.premio = premio;
   }

    /**
     * Se retorna el numero de intentos incorrectos que se han realizado hasta el momento 
     * @return
     */
    public int getNumeroIntentos(){
       return numeroIntentos;
   }

    /**
     * Asigna un valor para el numero de intentos incorrectos que se han realizado hasta el momento
     * @param numeroIntentos
     */
    public void setNumeroIntentos(int numeroIntentos){
       this.numeroIntentos = numeroIntentos;
   }

    /**
     * Retorna el mensaje actual del juego
     * @return
     */
    public String getMensajeActual(){
       return mensajeActual;
   }

    /**
     * Asigna el mensaje actual del juego
     * @param mensajeActual
     */
    public void setMensajeActual(String mensajeActual){
       this.mensajeActual = mensajeActual;
   }

    /**
     * Retorna los intentos incorrectos hasta el momento del juego 
     * @return
     */
    public List<Integer> getIntentos() {
	   return intentos;
   }

    /**
     * Asigna los intentos incorrectos hasta el momento del juego 
     * @param intentos
     */
    public void setIntentos(List<Integer> intentos) {
	   this.intentos = intentos;
   }

    /**
     * Metodo que verifica si se esta colocando un entero en el formulario
     * @param intento
     */
    public void guess(String intento){
	   try {
		   int intentow = Integer.parseInt(intento);
		   guess(intentow);
	   }catch(java.lang.NumberFormatException e) {	
		   if(!esGanador & premio > 0) {
			   setMensajeActual("La entrada debe ser un entero...");
		   }
	   }
   }

    /**
     * Metodo que se encarga de verificar si un intento es correcto o no
     * @param intento
     */
    public void guess(int intento){
	       if(!esGanador & (intento > 30 || intento <= 0)  ) {
	    	   setMensajeActual("El numero debe estar entre 1 y 30!...");		    	   
	       }
	       else if (!esGanador & intento!=numeroAdivinar & premio>0){
	    	   numeroIntentos +=1;
	    	   setMensajeActual("Incorrecto, Sigue intentando.");
	           premio -= 10000;
	           intentos.add(intento);
	       }
	       else if (premio<=0){	    	   
	           setMensajeActual("Ya no puedes realizar mas intentos, por favor reinicie el juego.");	         
	       }
	       else if(!esGanador & intento==numeroAdivinar & premio>=0){
	    	   numeroIntentos +=1;
	           setMensajeActual("Ganaste, tu premio es: "+premio+".");
	           this.esGanador = true;
	       }
	       else {
	    	   setMensajeActual("Premio: "+premio+". Pulse reiniciar para jugar con nosotros de nuevo.");
	       }

       
   }

    /**
     * Reinicia los valores del juego
     */
    public void reStart(){
       this.numeroAdivinar = r.nextInt(30);
       this.premio = 100000;
       this.mensajeActual = "Aun no ha ganado el juego.";
       this.numeroIntentos = 0;
       this.intentos.clear();
       this.esGanador = false;
   }
   
   
}