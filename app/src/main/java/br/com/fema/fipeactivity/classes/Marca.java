package br.com.fema.fipeactivity.classes;

import java.io.Serializable;

public class Marca implements Serializable{

    private String id;
    private String fipeName;
    private String name;

    public Marca(String id, String fipeName, String name) {
        this.id = id;
        this.fipeName = fipeName;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getFipeName() {
        return fipeName;
    }

    public String getName() {
        return name;
    }
}
