package com.Q2.lw1;

import com.Q2.include.*;

public class Karte{

    private Graph karte;
    
    public Karte(){
        karte = new Graph();        
        neuerOrt("L");
        neuerOrt("A");
        neuerOrt("B");
        neuerOrt("C");
        neuerOrt("D");
        neuerOrt("E");
        neueStrasse("L", "A", 14.0);
        neueStrasse("L", "B", 69.0);
        neueStrasse("L", "C", 26.0);
        neueStrasse("L", "D", 31.0);
        neueStrasse("L", "E", 16.0);
        neueStrasse("A", "B", 47.0);
        neueStrasse("A", "C", 66.0);
        neueStrasse("A", "D", 33.0);
        neueStrasse("A", "E", 52.0);        
        neueStrasse("B", "C", 58.0);
        neueStrasse("B", "D", 52.0);
        neueStrasse("B", "E", 23.0);
        neueStrasse("C", "D", 45.0);
        neueStrasse("C", "E", 27.0);
        neueStrasse("D", "E", 59.0);
    }

    
    public void starteRundreise(String startId){
        karte.setAllVertexMarks(false);
        Vertex k = karte.getVertex(startId); 
        System.out.print(k.getID() + " - ");
        rundreise(k, k);        
    }
    
    private void rundreise(Vertex k, Vertex ziel){
        List<Vertex> n =  karte.getNeighbours(k);
        while (ziel != k || !karte.allVerticesMarked())
        {
            n.toFirst();
            while (n.hasAccess()) {
                if (n.getContent().isMarked()) {
                    n.remove();
                }
                n.next();
            }
            double minD = 9999999.0;
            Vertex min = null;
            n.toFirst();
            while (n.hasAccess()){
                if (karte.getEdge(k, n.getContent()).getWeight() < minD){
                    minD = karte.getEdge(k, n.getContent()).getWeight();
                    min = n.getContent();
                }                
                n.next();
            }
            min.setMark(true);
            k = min;
        }
        // while ( k != ziel || karte.allVerticesMarked()){
        //     n.toFirst();
        //     Vertex min = null;
        //     double minInt  = (double) Integer.MAX_VALUE;
        //     while(n.hasAccess()){
        //         if (karte.getEdge(k,n.getContent()).getWeight() < minInt && !n.getContent().isMarked()){
        //             min = n.getContent();
        //             minInt = n.getContent().getWeight();
        //         }
        //         n.getContent();
        //         n.next();
        //     }
        //     min.setMark(true);
        //     k = min;
        //     }
        }
    
    
    private void neuerOrt(String pId){
        karte.addVertex(new Vertex(pId));
    }
    
    private void neueStrasse(String vonId, String nachId, double laenge){
        Vertex v1 = karte.getVertex(vonId);
        Vertex v2 = karte.getVertex(nachId);
        karte.addEdge(new Edge(v1, v2, laenge));
    }
    

}
