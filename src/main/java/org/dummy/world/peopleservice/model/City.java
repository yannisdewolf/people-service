package org.dummy.world.peopleservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private Long amount;

    public City() {
    }

    public City(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public Long getAmount() {
        return amount;
    }
}
