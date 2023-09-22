package net.javaguides.springboot.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;

}
