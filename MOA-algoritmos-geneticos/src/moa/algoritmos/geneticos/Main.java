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
 * @author mayza
 */
public class Main {

    static boolean criterioDeParada = false;
    static List<Vertice> populacao = new ArrayList<>();
    static String caso = "SCJ1.dat";
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner in = new Scanner(new File(caso));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(line);
        }
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