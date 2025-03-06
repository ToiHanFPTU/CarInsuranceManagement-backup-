package core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Insurance {
    private String insuranceID;
    private String licensePlate;
    private Date establishedDate;
    private int insurancePeriod;
    private double insuranceFees;
    private String insuranceOwner;

    public Insurance(String insuranceID, String licensePlate, Date establishedDate, int insurancePeriod, double insuranceFees, String insuranceOwner) {
        this.insuranceID = insuranceID;
        this.licensePlate = licensePlate;
        this.establishedDate = establishedDate;
        this.insurancePeriod = insurancePeriod;
        this.insuranceFees = insuranceFees;
        this.insuranceOwner = insuranceOwner;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(int insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public double getInsuranceFees() {
        return insuranceFees;
    }

    public void setInsuranceFees(double insuranceFees) {
        this.insuranceFees = insuranceFees;
    }

    public String getInsuranceOwner() {
        return insuranceOwner;
    }

    public void setInsuranceOwner(String insuranceOwner) {
        this.insuranceOwner = insuranceOwner;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public String toString() {
        return insuranceID + "," + licensePlate + "," + simpleDateFormat.format(establishedDate) + "," + insurancePeriod + "," + insuranceFees + "," + insuranceOwner;
    }
}
