package com.mugigraphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author Mugi
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private int id;
    private String name;
    private String mobile;
    private String email;
    @ElementCollection
    private List<String> address;
}
