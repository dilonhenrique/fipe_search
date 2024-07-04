package com.fipe_search.fipe.models;

public class Vehicle {

  private String price;
  private String brand;
  private String model;
  private Integer year;
  private String fuel;

  public Vehicle(VehicleData payload) {
    this.price = payload.price();
    this.brand = payload.brand();
    this.model = payload.model();
    this.year = payload.year();
    this.fuel = payload.fuel();
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getFuel() {
    return fuel;
  }

  public void setFuel(String fuel) {
    this.fuel = fuel;
  }
}
