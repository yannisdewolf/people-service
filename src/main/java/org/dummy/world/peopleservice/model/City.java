package org.dummy.world.peopleservice.model;

public class City {

    private String name;
    private Long amount;

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
