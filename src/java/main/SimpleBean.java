package main;

import main.annotation.RandomNumber;

public class SimpleBean {
    private String description;
    @RandomNumber(min = 1, max = 100)
    private double randomNr;

    public String getDescription() {
        return description + ". Random Nr: " + randomNr;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
