/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Poblacion {
    
    // lista de individuos 
    private ArrayList<Individuo> individuos;
    private int tamPob;
    private Individuo mejor;
    private Individuo peor;
    private int fitnessPoblacion;
    
    public Poblacion (){
        this.tamPob = 0;
        this.individuos = new ArrayList<>();
    }
    
    // por defecto / aleatorio
    public Poblacion(int tamPob){
        this.tamPob = tamPob;
        this.individuos = new ArrayList<>();
        for (int x=0; x < tamPob;x++){
           this.individuos.add(new Individuo());
        }
   
    }
     public Poblacion(Poblacion pob){
        this.tamPob = pob.getTamPob();
        this.individuos = clonarPoblacion(pob.getIndividuos());
        for (int x=0; x < tamPob;x++){
           this.individuos.add(new Individuo());
        }
    
    }

    /**
     * @return the individuos
     */
    public ArrayList<Individuo> getIndividuos() {
        return individuos;
    }

    /**
     * @return the tamPob
     */
    public int getTamPob() {
        return tamPob;
    }

    /**
     * @return the mejor
     */
    public Individuo getMejor() {
             calcularMejoryPeor();
        return mejor;
    }

    /**
     * @return the peor
     */
    public Individuo getPeor() {
             calcularMejoryPeor();
        return peor;
    }

    private ArrayList<Individuo> clonarPoblacion(ArrayList<Individuo> individuos) {
        ArrayList<Individuo> aux = new ArrayList<>();
        for (Individuo i:individuos){
         aux.add(new Individuo(i));
        }
        return aux;
    }
    
    private void calcularMejoryPeor(){
       
        if (!this.individuos.isEmpty()){
          // buscamos
         this.mejor = this.individuos.get(0);
         this.peor = this.individuos.get(0);
         for (int x=0; x < this.individuos.size();x++){
            if (this.individuos.get(x).getFitness()>mejor.getFitness()){
              this.mejor = this.individuos.get(x);
            }
            if (this.individuos.get(x).getFitness()<peor.getFitness()){
             this.peor = this.individuos.get(x);
            }
         }          
        }
    
    }
    
     public ArrayList<Individuo> generarGrupoAleatorio(int numInd){
        ArrayList<Individuo> grupo = new ArrayList<>();
        Random ran = new Random();
        // seleccionar de forma aleatorio a los ind
        for(int x=0; x<numInd;x++){
         grupo.add(new Individuo
        (this.individuos.get(ran.nextInt(this.individuos.size())).getGenotipo()));
        }
                  
     return grupo;
    }
     
        public void recibirMuestra(ArrayList<Individuo> muestra){
        for (int x=0; x<muestra.size();x++)
            this.individuos.add(muestra.get(x));
     
    }
        
    

    
   public ArrayList<Individuo> generarGrupoMejores(int num){
        Ordenamiento mezcla = new Ordenamiento();
        mezcla.ordenar(this);
         int inicio = this.individuos.size()-1;
        // crear el grupo de mejores
        ArrayList<Individuo> mejores = new ArrayList<>();
        for (int x=inicio;x>(inicio-num);x--){      
         mejores.add(new Individuo(this.individuos.get(x).getGenotipo()));
        
        }
              
        return mejores;
    
    }
    
     public void agregarHabitante(Individuo ind) {
        this.individuos.add(new Individuo(ind));
        this.tamPob++;
    }
      public void calcularMejorPeor(){
        
      if (this.mejor == null || this.peor==null){
       // recorrer la población completa 
      this.mejor = new Individuo(this.individuos.get(0).getGenotipo());
      this.peor = new Individuo(this.individuos.get(0).getGenotipo());
      
      for(int x=1; x<this.individuos.size();x++){
          if(this.individuos.get(x).getFitness()
                  >this.mejor.getFitness()){
           this.mejor = new Individuo(this.individuos.get(x).getGenotipo());
          }
           if(this.individuos.get(x).getFitness()
                  <this.peor.getFitness()){
           this.peor = new Individuo(this.individuos.get(x).getGenotipo());
          }
      }
       
      }  
     
      
    }

    /**
     * @return the fitnessPoblacion
     */
    public int getFitnessPoblacion() {
        int acum =0;
        for (Individuo ind: individuos){
        acum+=ind.getFitness();
        }
        this.fitnessPoblacion = acum;
        return fitnessPoblacion;
    }
    
}
