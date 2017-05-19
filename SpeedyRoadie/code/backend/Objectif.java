/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 * Interface représentant la capacité a être une nécessité de victoire d'un élément. La méthode isCompleted() décrira plus concrètement la condition de victoire d'un object concret implémentant Objectif
 * @author Corentin Dachy
 */
public abstract class Objectif {  //En faire une classe abstraite plutot qu'une interface me semblait avoir plus de sens
    abstract boolean isCompleted(); //même si d'un point de vue syntaxe on dirait une interface plutot qu'une classe abstraite
}
