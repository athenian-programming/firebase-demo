package com.sudothought.firebase;

public class Person {
  private String firstName;
  private String lastName;

  public Person() {}

  public Person(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}