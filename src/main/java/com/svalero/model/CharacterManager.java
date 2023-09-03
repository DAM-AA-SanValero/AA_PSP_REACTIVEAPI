package com.svalero.model;

import java.util.HashMap;
import java.util.Map;

public class CharacterManager {
    private Map<Integer, String> characterIdToNameMap;

    public CharacterManager() {
        this.characterIdToNameMap = new HashMap<>();
        this.characterIdToNameMap.put(0, "Dwight Fairfield");
        this.characterIdToNameMap.put(1, "Meg Thomas");
        this.characterIdToNameMap.put(2, "Claudette Morel");
        this.characterIdToNameMap.put(3, "Jake Park");
        this.characterIdToNameMap.put(4, "Nea Karlsson");
        this.characterIdToNameMap.put(5, "Laurie Strode");
        this.characterIdToNameMap.put(6, "Ace Visconti");
        this.characterIdToNameMap.put(7, "Bill William");
        this.characterIdToNameMap.put(8, "Feng Min");
        this.characterIdToNameMap.put(9, "David King");
        this.characterIdToNameMap.put(10, "Kate Denson");
        this.characterIdToNameMap.put(11, "Quentin Smith");
        //Aquí añado manualmente el id y el nombre del personaje para mostrar el nombre
        //entre parentesis al listar las PERKS
        this.characterIdToNameMap.put(null, "General Perk");
    }

    public String getNameById(Integer id) {
        return this.characterIdToNameMap.get(id);
    }
}

