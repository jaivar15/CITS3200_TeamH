package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        responder r = new responder();
        r.newAnimal(1,"cat");
        r.newUser("jhon","jhon@google.com");
        r.addResponder(1,"jhon@google.com");
        r.removeEmail("jhon@google.com");
        System.out.println(r.toString());
    }
}
