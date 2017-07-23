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
    
    static final int TAM_POPULACAO_INICIAL = 2;
    static int id = 0;//Para fins de teste

    static List<Vertice> verticesIniciais = new ArrayList<>();
    static boolean criterioDeParada = false;
    static int qtdVertices;
    static int qtdMedianas;
    
    static List<Solucao> populacao = new ArrayList<>();
    
    static String caso = "SCJ1.dat";
    
    public static void main(String[] args) throws FileNotFoundException {
        lerArquivo();
        
        //Apenas para mostrar se o arquivo foi lido corretamente
        for(Vertice v: verticesIniciais)
            System.out.println(v.toString());
        algoritmoGenetico();
    }
    
    static void algoritmoGenetico(){
        gerarPopulacaoInicial();
        for(Solucao s: populacao){
            System.out.println("\nDistancia solucao: " + s.getDistancia());
            for(Vertice v: s.getMedianas()){
                System.out.println("Distancia geral à mediana " + v.getId() + ": " + v.getSomaDistancias());
                System.out.println("Demanda: " + v.getSomaDemanda());
            }
        }
//        while(!criterioDeParada){
//            avaliacao();
//            selecao();
//            cruzamento();
//            mutacao();
//            buscaLocal();
//            atualizacaoDaPopulacao();
//        }
    }

    static void gerarPopulacaoInicial() {
        Solucao solucao;
        Vertice medianaMaisProxima;
        for(int i=0;i<TAM_POPULACAO_INICIAL;i++){
            solucao = new Solucao();
            for(int j=0;j<qtdMedianas;j++){
                Vertice v = new Vertice(randomMediana());
                v.setIsMediana(true);
                v.setId(id);
                solucao.getMedianas().add(v); 
                id++;
            }
            for(Vertice v: verticesIniciais){
                if(!v.isIsMediana()){
                    medianaMaisProxima = calculaMedianaMaisProximaDisponivel(solucao, v);
                    medianaMaisProxima.adicionaDemanda(v.getDemanda());
                    medianaMaisProxima.adicionaDistancia(calculaDistanciaVertices(medianaMaisProxima, v));
                    medianaMaisProxima.setDependente(v);
                }
            }
            for(Vertice v: solucao.getMedianas()){
                solucao.setDistancia(v.getSomaDistancias());
            }
            populacao.add(solucao);
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

    static Vertice calculaMedianaMaisProximaDisponivel(Solucao solucao, Vertice v) {
        int j=0;
        Double dist;
        Double menorDistancia = null;
        Vertice medianaMaisProxima = null;
       
        while(!temCapacidade(solucao.getMedianas().get(j), v.getDemanda())){
            j++;
        }
        
        menorDistancia = calculaDistanciaVertices(solucao.getMedianas().get(j), v);
        medianaMaisProxima = solucao.getMedianas().get(j);
        j++;
        
        //Verifica entre as medianas, qual a mais próxima ao vértice
        for(int i=j;i<qtdMedianas;i++){           
            dist = calculaDistanciaVertices(solucao.getMedianas().get(i), v);
            if((dist < menorDistancia) && temCapacidade(solucao.getMedianas().get(i), v.getDemanda())){
                menorDistancia = dist;
                medianaMaisProxima = solucao.getMedianas().get(i);
            }          
        }
        return medianaMaisProxima;
    }

    static Double calculaDistanciaVertices(Vertice mediana, Vertice v) {
        Double distancia;
        distancia = Math.sqrt((Math.pow((v.getCoordenada().x() - mediana.getCoordenada().x()), 2)) + (Math.pow((v.getCoordenada().y() - mediana.getCoordenada().y()), 2)));
        return distancia;
    }

    static boolean temCapacidade(Vertice mediana, Double demanda) {
        Double demandaSeAdicionar = mediana.getSomaDemanda() + demanda;
        if(demandaSeAdicionar > mediana.getCapacidade()){
            return false;
        }
        return true;
    }

    private static void lerArquivo() throws FileNotFoundException {
        Vertice vert;
        Scanner in = new Scanner(new File(caso));
        qtdVertices = in.nextInt();
        qtdMedianas = in.nextInt();

        while (in.hasNextLine()) {
            vert = new Vertice();
            Coordenada coord = new Coordenada(in.nextInt(), in.nextInt());
 
            vert.setId(id);
            vert.setCoordenada(coord);
            vert.setCapacidade(in.nextDouble());
            vert.setDemanda(in.nextDouble());      
            id++;
            
            verticesIniciais.add(vert);
        }
    }
}

class Vertice {
    private int id = 0; //para fins de teste
    private Coordenada coordenada = new Coordenada();
    private boolean isMediana = false;
    private Double somaDistancias = new Double(0);
    private Double somaDemanda = new Double(0);
    private Double capacidade = new Double(0);
    private Double demanda = new Double(0);
    
    private List<Vertice> dependentes = new ArrayList<>();
    
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

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Double getDemanda() {
        return demanda;
    }

    public void setDemanda(Double demanda) {
        this.demanda = demanda;
        somaDemanda = demanda; //a propria demanda
    }

    public List<Vertice> getDependentes() {
        return dependentes;
    }

    public void setDependente(Vertice dependente) {
        this.dependentes.add(dependente);
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
     
    public Double getSomaDemanda() {
        return somaDemanda;
    }

    public void adicionaDemanda(Double somaDemanda) {
        this.somaDemanda = this.somaDemanda + somaDemanda;
    }
    //</editor-fold>
    
    @Override
    public String toString(){
        return "x: " + this.coordenada.x() + " y: " + this.coordenada.y() + " Capacidade: " + this.capacidade + " Demanda: " + this.demanda;
    }
}

class Solucao{
    private List<Vertice> medianas = new ArrayList<>();
    private Double distanciaGeral = new Double(0);

    //<editor-fold defaultstate="collapsed" desc=" Getters e Setters ">
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
    //</editor-fold>
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
