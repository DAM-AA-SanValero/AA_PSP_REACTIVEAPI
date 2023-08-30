package com.svalero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerksCollection {


    private Map<String, Perks> perksCollection;

    public Map<String, Perks> getPerksCollection() {
        return perksCollection;
    }

    public void setPerksCollection(Map<String, Perks> perksCollection) {
        this.perksCollection = perksCollection;
    }
}
