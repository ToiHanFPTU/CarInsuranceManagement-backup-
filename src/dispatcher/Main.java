package dispatcher;

import core.CarInsuranceManager;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        CarInsuranceManager carInsuranceManager = new CarInsuranceManager();
        String carFile = "C:\\Users\\HP\\Desktop\\CarInsuranceManagement\\src\\data\\CarInfo.txt";
        String insuranceFile = "C:\\Users\\HP\\Desktop\\CarInsuranceManagement\\src\\data\\Insurance.txt";
        File carInfo = new File(carFile);
        File insurance = new File(insuranceFile);

        if (!carInfo.exists()) {
            System.out.println("File CarInfo.txt not exist");
            return;
        }

        if (!insurance.exists()) {
            System.out.println("File Insurance.txt not exist");
            return;
        }
        carInsuranceManager.readFromFileCarInfo(carFile);
        carInsuranceManager.readFromFileInsurance(insuranceFile);
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                carInsuranceManager.menu();
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        carInsuranceManager.addNewCar();
                        break;
                    case 2:
                        carInsuranceManager.findCarByLicensePlate();
                        break;
                    case 3:
                        carInsuranceManager.updateCarInfo();
                        break;
                    case 4:
                        carInsuranceManager.deleteCarInfo();
                        break;
                    case 5:
                        carInsuranceManager.addInsuranceStatement();
                        break;
                    case 6:
                        carInsuranceManager.listInsuranceStatements();
                        break;
                    case 7:
                        carInsuranceManager.reportUninsuredCar();
                        break;
                    case 8:
                        carInsuranceManager.saveData();
                        break;
                    case 9:
                        System.out.println("Exit program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("FUnction not valid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Choice must be integer  number");
            }
        } while (choice != 9);

    }
}