package com.svalero.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Characters {
    private String name;
    private String role;
    private String gender;
    private String height;

}


