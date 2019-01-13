package model;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    private String name;
    private List<String> allies = new ArrayList<>();

    public Kingdom(String name, List<String> allies) {
        this.name = name;
        this.allies = allies;
    }

    public String getName() {
        return name;
    }

    public List<String> getAllies() {
        return allies;
    }
}
