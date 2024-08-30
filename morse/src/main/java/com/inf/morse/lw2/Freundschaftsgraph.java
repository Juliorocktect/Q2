package com.inf.morse.lw2;
import com.inf.morse.include.*;

public class Freundschaftsgraph{
   
    private Graph personenGraph;
    
    public Freundschaftsgraph(){
        personenGraph = new Graph();
        initialisiereMitBeispielgraph();
    }

    public void initialisiereMitBeispielgraph(){        
        personenGraph.addVertex(new Person("Anton",'m'));
        personenGraph.addVertex(new Person("Berta",'w'));
        personenGraph.addVertex(new Person("Carla",'w'));
        personenGraph.addVertex(new Person("Doris",'w'));
        personenGraph.addVertex(new Person("Emil",'m'));
        personenGraph.addVertex(new Person("Franz",'m'));
        personenGraph.addVertex(new Person("Gustav",'m'));
        personenGraph.addVertex(new Person("Helmut",'m'));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Anton"),personenGraph.getVertex("Berta"),6.1));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Anton"),personenGraph.getVertex("Carla"),10.0));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Anton"),personenGraph.getVertex("Doris"),2.4));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Berta"),personenGraph.getVertex("Carla"),5.3));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Emil"),personenGraph.getVertex("Franz"),4.6));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Emil"),personenGraph.getVertex("Gustav"),2.2));
        personenGraph.addEdge(new Edge(personenGraph.getVertex("Franz"),personenGraph.getVertex("Gustav"),1.0));
    }
    
    
    public void testGibGruppenmitglieder(String pName){
        personenGraph.setAllVertexMarks(false);
        Person p = (Person) personenGraph.getVertex(pName);
        List<Person> gruppe = gibGruppenmitglieder(p);
        System.out.println("Gruppe von " + p.getID() + ": ");
        for(gruppe.toFirst(); gruppe.hasAccess(); gruppe.next()){
             System.out.println(" - " + gruppe.getContent().getID());
        }
    }
    
    public List<Person> gibGruppenmitglieder(Person pPerson){
        pPerson.setMark(true);
        List<Person> ergebnis = new List<Person>();
        ergebnis.append(pPerson);
        List<Vertex> freundeListe = personenGraph.getNeighbours(pPerson);        
        freundeListe.toFirst();
        while (freundeListe.hasAccess()){
            Person freund = (Person)freundeListe.getContent();
            if(!freund.isMarked()){
                List<Person> gruppenmitglieder = gibGruppenmitglieder(freund);
                ergebnis.concat(gruppenmitglieder);
            }
            freundeListe.next();
        }
        return ergebnis;
    }
    
    public boolean existierenDickeFreunde() {
        List<Edge> edges = personenGraph.getEdges();
        edges.toFirst();
        while (edges.hasAccess()) {
            if (edges.getContent().getWeight() >= 0 && edges.getContent().getWeight() <= 1){
                return true;
            }
            edges.next();
        }
        return false;
    }
    
}
