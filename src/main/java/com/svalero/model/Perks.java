package com.svalero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perks {

    private String name;
    private String description;
    private String role;
    private int character;
}
