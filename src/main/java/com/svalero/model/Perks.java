package com.svalero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perks {

    int characterPerk;
    String name;
    String description;
    String role;

}
