package com.inf.morse.lw2;

import com.inf.morse.include.Vertex;

public class Person extends Vertex {
    private char geschlecht;
    public Person(String pId,char geschlecht){
        super(pId);
        geschlecht = geschlecht;
    }
    public char gibGeschlecht() {
        return geschlecht;
    }
    
}
