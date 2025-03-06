package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Car {
    private String licensePlate;
    private String carOwner;
    private String phoneNumber;
    private String carBrand;
    private int price;
    private Date registrationDate;
    private String placeRegistration;
    private int numberOfSeat;
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public Car(String licensePlate, String carOwner, String phoneNumber, String carBrand, int price, Date registrationDate, String placeRegistration, int numberOfSeat) {
        this.licensePlate = licensePlate;
        this.carOwner = carOwner;
        this.phoneNumber = phoneNumber;
        this.carBrand = carBrand;
        this.price = price;
        this.registrationDate = registrationDate;
        this.placeRegistration = placeRegistration;
        this.numberOfSeat = numberOfSeat;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPlaceRegistration() {
        return placeRegistration;
    }

    public void setPlaceRegistration(String placeRegistration) {
        this.placeRegistration = placeRegistration;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        return licensePlate + "," + carOwner + "," + phoneNumber + "," + carBrand + "," + price + "," + simpleDateFormat.format(registrationDate) + "," + placeRegistration + "," + numberOfSeat;
    }
}
