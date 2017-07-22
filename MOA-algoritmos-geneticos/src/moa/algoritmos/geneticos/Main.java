/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.algoritmos.geneticos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mayza Hirose
 */
public class Main {
    
    static final int TAM_POPULACAO_INICIAL = 1;
    static int id = 0;//Para fins de teste

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
 
            vert.setId(id);
            vert.setCoordenada(coord);
            vert.setCapacidade(in.nextInt());
            vert.setDemanda(in.nextInt());      
            id++;
            
            verticesIniciais.add(vert);
        }
        algoritmoGenetico();
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
        Solucao solucao;
        for(int i=0;i<TAM_POPULACAO_INICIAL;i++){
            solucao = new Solucao();
            for(int j=0;j<qtdMedianas;j++){
                Vertice v = new Vertice(randomMediana());
                v.setIsMediana(true);
                solucao.getMedianas().add(v); 
            }
            for(Vertice v: verticesIniciais){
                if(!v.isIsMediana()){
                    
                }
            }
        }
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
    
    static Vertice randomMediana(){
        Random aleatorio = new Random();
        Vertice mediana = verticesIniciais.get(aleatorio.nextInt(verticesIniciais.size()-1));
        System.out.println("ID: "+ mediana.getId());
        return mediana;
    }
}

class Vertice {
    private int id = 0; //para fins de teste
    private boolean isMediana = false;
    private Double somaDistancias;
    private Coordenada coordenada;
    private int capacidade;
    private int demanda;
    
    private List<Vertice> grupoAssociado = new ArrayList<>();
    
    //Construtor padrão
    public Vertice(){
        
    }
    
    //Construtor de cópia
    public Vertice(Vertice copiar){
        this.coordenada = copiar.coordenada;
        this.capacidade = copiar.capacidade;
        this.demanda = copiar.demanda;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSomaDistancias() {
        return somaDistancias;
    }

    public void adicionaDistancia(Double somaDistancias) {
        this.somaDistancias = this.somaDistancias + somaDistancias;
    }
    
    //</editor-fold>
    
    @Override
    public String toString(){
        return "x: " + this.coordenada.x() + " y: " + this.coordenada.y() + " Capacidade: " + this.capacidade + " Demanda: " + this.demanda;
    }
}

class Coordenada{
    private int x;
    private int y;
    
    public Coordenada(){       
    }

    public Coordenada(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int x(){ return x; }
    public int y(){ return y; }
}

class Solucao{
    private List<Vertice> medianas = new ArrayList<>();
    private Double distanciaGeral;

    public List<Vertice> getMedianas() {
        return medianas;
    }

    public void setMedianas(List<Vertice> medianas) {
        this.medianas = medianas;
    }

    public Double getDistancia() {
        return distanciaGeral;
    }

    public void setDistancia(Double distanciaGeral) {
        this.distanciaGeral = this.distanciaGeral + distanciaGeral;
    }
    
}