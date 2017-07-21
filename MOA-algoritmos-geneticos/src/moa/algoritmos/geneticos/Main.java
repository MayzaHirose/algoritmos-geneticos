/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.algoritmos.geneticos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mayza Hirose
 */
public class Main {

    static List<Vertice> verticesIniciais = new ArrayList<>();
    static boolean criterioDeParada = false;
    static int qtdVertices;
    static int qtdMedianas;
    
    static List<Vertice> populacao = new ArrayList<>();
    
    static String caso = "SCJ1.dat";
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(caso));
        qtdVertices = in.nextInt();
        qtdMedianas = in.nextInt();
        
        while (in.hasNextLine()) {
            Vertice vert = new Vertice();
            Coordenada coord = new Coordenada(in.nextInt(), in.nextInt());
            
            vert.setCoordenada(coord);
            vert.setCapacidade(in.nextInt());
            vert.setDemanda(in.nextInt());
            
            verticesIniciais.add(vert);
        }
        System.out.println("Vertices:\t" + qtdVertices);
        System.out.println("Medianas:\t" + qtdMedianas);
        for(Vertice v: verticesIniciais)
            System.out.println(v.toString());
    }
    
    static void algoritmoGenetico(){
        gerarPopulacaoInicial();
        while(!criterioDeParada){
            avaliacao();
            selecao();
            cruzamento();
            mutacao();
            buscaLocal();
            atualizacaoDaPopulacao();
        }
    }

    static void gerarPopulacaoInicial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void avaliacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void selecao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void cruzamento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void mutacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void buscaLocal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static void atualizacaoDaPopulacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class Vertice{
    private boolean isMediana = false;
    private Coordenada coordenada;
    private int capacidade;
    private int demanda;
    
    private List<Vertice> grupoAssociado;

    //<editor-fold defaultstate="collapsed" desc=" Getters e Setters ">
    public boolean isIsMediana() {
        return isMediana;
    }

    public void setIsMediana(boolean isMediana) {
        this.isMediana = isMediana;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getDemanda() {
        return demanda;
    }

    public void setDemanda(int demanda) {
        this.demanda = demanda;
    }

    public List<Vertice> getGrupoAssociado() {
        return grupoAssociado;
    }

    public void setGrupoAssociado(List<Vertice> grupoAssociado) {
        this.grupoAssociado = grupoAssociado;
    }
    //</editor-fold>
}

class Coordenada{
    public int x;
    public int y;
    
    public Coordenada(){       
    }

    public Coordenada(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int x(){ return x; }
    public int y(){ return y; }
}