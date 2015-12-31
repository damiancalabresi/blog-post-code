package com.dcalabresi.faces;

import java.util.Random;

/**
 * Created by damian on 12/30/15.
 */
public class ExampleManagedBean {

    private Integer random;

    public ExampleManagedBean() {
        this.random = new Random().nextInt();
    }

    public Integer getRandom() {
        return random;
    }

}
