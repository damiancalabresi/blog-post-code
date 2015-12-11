package com.dcalabresi.arguments_by_value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
public class Application {

    public static void main( String[] args ) {
        Person person = new Person("Damian");
        System.out.println("Name: " + person.name);

        System.out.println("Test pointer by reference");
        testPointerByReference(person);
        System.out.println("Name: " + person.name);

        System.out.println("Test pointer by value");
        person = testPointerByValue(person);
        System.out.println("Name: " + person.name);

        System.out.println("Modify object");
        modifyObject(person);
        System.out.println("Name: " + person.name);

        System.out.println("Primitives - Integer");
        Integer aNumber = 5;
        modifyInt(aNumber);
        System.out.println("Integer: " + aNumber);

        System.out.println("Immutable - String (Not really a primitive)");
        String aString = "First value";
        modifyString(aString);
        System.out.println("String: " + aString);

        System.out.println("Pass an Integer by reference");
        AtomicInteger otherNumber = new AtomicInteger(5);
        modifyAtomicInt(otherNumber);
        System.out.println("Integer: " + otherNumber);
    }

    private static void testPointerByReference(Person person) {
        person = new Person("Mark");
    }

    private static Person testPointerByValue(Person person) {
        person= new Person("Thomas");
        return person;
    }

    private static void modifyObject(Person person) {
        person.name = "Paul";
    }

    private static void modifyInt(Integer aNumber) {
        aNumber = 7;
    }

    private static void modifyString(String aString) {
        // Even if you concatenate the String, Java will generate a copy.
        aString.concat("Another value");
    }

    private static void modifyAtomicInt(AtomicInteger otherNumber) {
        otherNumber.set(7);
    }

}
