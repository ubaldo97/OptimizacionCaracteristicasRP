/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Random;

/**
 *
 * @author ruben
 */
public class Seleccion {
     public static Individuo seleccionAleatoria(Poblacion pob) {
        Random ran = new Random();
        return pob.getIndividuos().get(ran.nextInt(pob.getIndividuos().size()));

    }
    
    public static Individuo seleccionTorneoTSP(Poblacion pob){
       pob.calcularMejorPeor();
       return pob.getMejor();
    }
    
      public static Individuo seleccionRuleta(Poblacion pob){
      
        double pos = pob.getFitnessPoblacion()*Math.random();
       
        double suma=0;
        // ahora costruimos la ruleta
        for (Individuo ind: pob.getIndividuos()){
          suma += ind.getFitness();
          if(suma>=pos){
            return new Individuo(ind);
          }
        }
        return new Individuo(pob.getIndividuos().get(0));
    }

    public static Individuo seleccionTorneoMax(Poblacion pob){
      if (pob.getMejor() ==null || pob.getPeor()==null){
       pob.calcularMejorPeor();
      }
      return pob.getMejor();
    }
    
    public static Individuo seleccionTorneoMin(Poblacion pob){
      if (pob.getMejor() ==null || pob.getPeor()==null){
       pob.calcularMejorPeor();
      }
      return pob.getPeor();
    }
    
    
}
