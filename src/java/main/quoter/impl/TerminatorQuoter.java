package main.quoter.impl;

import main.annotation.DeprecatedClass;
import main.annotation.Profiling;
import main.annotation.RandomNumber;
import main.quoter.Quoter;


//@DeprecatedClass(newImplementation = Terminator1000.class)
@Profiling
public class TerminatorQuoter implements Quoter {
    @RandomNumber(min = 1, max = 7)
    private double randomNumber;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < randomNumber; i++) {
            System.out.println("message is: " + message);
        }
    }

    public void setRandomNumber(double randomNumber) {
        this.randomNumber = randomNumber;
    }
}
