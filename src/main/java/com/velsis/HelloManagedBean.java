package com.velsis;

import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named("hello")
@ViewScoped 
public class HelloManagedBean implements Serializable {

  public HelloManagedBean() {
    this.greeting = "Hello JSF - Myfaces";
  }

  private String greeting;

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
}
