package core;

import tool.Acceptable;
import tool.Inputter;
import tool.PlaceRegis;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

public class CarInsuranceManager implements Function {

    public static final String LICENSE_PLATE_ERROR = "Invalid License plate\nInclude a character describing the district code of Ho Chi Minh City (for example, P-Tan Binh, S-Binh Thanh, X-Thu Duc, ...) \nCombined with a digit from 1 to 9, followed by 5 digits";
    public static final String PHONE_NUMBER_ERROR = "Must belong to a valid Vietnamese network operator.\nMust contain exactly 10 digits.";
    public static final String NUMBER_OF_SEAT_ERROR = "Number of seat must be integer between 4 and 36";
    public Inputter inputter = new Inputter();
    public ArrayList<Car> cars = new ArrayList<>();
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public ArrayList<Insurance> insurances = new ArrayList<>();
    public Scanner sc = new Scanner(System.in);
    public PlaceRegis place = new PlaceRegis();

    public void addNewCar() throws ParseException {
        String licensePlate, confirm;
        String carOwner;
        String phoneNumber;
        String carBrand;
        int price;
        Date registrationDate;
        String placeRegistration;
        int numberOfSeat;
        //input license plate
        do {
            System.out.println("REGISTER NEW CAR INFORMATION");
            while (true) {
                licensePlate = inputter.inputStringAndLoop("Enter license plate: ", LICENSE_PLATE_ERROR, Acceptable.LICENSE_PLATE_VALID);
                if (licensePlate.isEmpty()) {
                    System.out.println("Can not be empty");
                    continue;
                }
                if (checkDuplicateLicensePlateInCarList(licensePlate)) {
                    System.out.println("License plate must be unique");
                    continue;
                }
                break;
            }
            //input car owner
            while (true) {
                carOwner = inputter.inputString("Enter car owner: ");
                if (carOwner.isEmpty()) {
                    System.out.println("Cannot be empty.");
                    continue;
                }
                if (carOwner.length() < 2 || carOwner.length() > 25) {
                    System.out.println("Length must be between 2 and 25 characters.");
                    continue;
                }
                break;
            }
            //Input phone number
            phoneNumber = inputter.inputStringAndLoop("Enter phone number: ", PHONE_NUMBER_ERROR, Acceptable.PHONE_NUMBER_VALID);
            while (true) {
                carBrand = inputter.inputString("Enter car brand: ");
                if (carBrand.isEmpty()) {
                    System.out.println("Can not be empty");
                    continue;
                }
                if (carBrand.length() < 5 || carBrand.length() > 12) {
                    System.out.println("Length must be between 5 and 12 characters.");
                    continue;
                }
                break;
            }
            //input price
            price = inputter.inputIntegerAndLoop("Enter value of vehicle: ", "Is an integer greater than 999.", 999, 1000000000);
            //input registration date
            while (true) {
                registrationDate = inputter.inputDateAndLoop("Enter registration date: ", "Invalid date format(Day/Month/Year)");
                if (registrationDate == null) {
                    System.out.println("Cannot be null. ");
                    continue;
                }
                if (registrationDate.before(new Date())) {
                    System.out.println(" Registration date must before the current date");
                    continue;
                }
                break;
            }
            placeRegistration = place.getPlace(licensePlate);
            numberOfSeat = inputter.inputIntegerAndLoop("Enter number of seats: ", NUMBER_OF_SEAT_ERROR, 4, 36);

            cars.add(new Car(licensePlate, carOwner, phoneNumber, carBrand, price, registrationDate, placeRegistration, numberOfSeat));
            System.out.println("Register new car successfully");
            confirm = inputter.inputStringAndLoop("Do you want to continue entering new vehicle information(Y/N): ", "Invalid confirm(Y or N)", Acceptable.YES_NO_VALID);
        } while (confirm.equalsIgnoreCase("y"));
    }

    public void findCarByLicensePlate() {
        String confirm;
        do {
            String licensePlate = inputter.inputStringAndLoop("Enter license plate to find a car: ", "Invalid License plate\nInclude a character describing the district code of Ho Chi Minh City (for example, P-Tan Binh, S-Binh Thanh, X-Thu Duc, ...) \nCombined with a digit from 1 to 9, followed by 5 digits", Acceptable.LICENSE_PLATE_VALID);

            Car carFound = findCarByLicensePlate(licensePlate);
            if (carFound == null) {
                System.out.println("No one matches the search criteria!");
                return;
            }
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|License plate |        Car owner        | Phone Number | Car brand  |Place of registration |Number of seats| Registration Date| Price|");
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|%-13s|%-25s|%-14s|%-12s|%-22s|%15d|%-18s|%6d|\n", carFound.getLicensePlate(), carFound.getCarOwner(), carFound.getPhoneNumber(), carFound.getCarBrand(), carFound.getPlaceRegistration(), carFound.getNumberOfSeat(), simpleDateFormat.format(carFound.getRegistrationDate()), carFound.getPrice());
            System.out.println("+--------------------------------------------------------------------------------------------------------------------------------------+");
            confirm = inputter.inputStringAndLoop("Do you want to continue search another car(Y/N): ", "Invalid confirm(Y or N)", Acceptable.YES_NO_VALID);
        } while (confirm.equalsIgnoreCase("y"));
    }

    public void updateCarInfo() {
        String newCarOwner, newPhoneNumber, newCarBrand, confirm, licensePlate;
        int newNumberOfSeat;
        Car carUpdateInfo;
        do {
            licensePlate = inputter.inputStringAndLoop("Enter license plate to update information: ", "Invalid License plate\\nInclude a character describing the district code of Ho Chi Minh City (for example, P-Tan Binh, S-Binh Thanh, X-Thu Duc, ...) \\nCombined with a digit from 1 to 9, followed by 5 digits", Acceptable.LICENSE_PLATE_VALID);
            carUpdateInfo = findCarByLicensePlate(licensePlate);
            if (carUpdateInfo == null) {
                System.out.println("This vehicle does not exist.");
                return;
            }
            newCarOwner = inputter.inputString("Enter new car owner: ");
            if (!newCarOwner.isEmpty()) {
                if (newCarOwner.length() > 2 && newCarOwner.length() < 25) {
                    carUpdateInfo.setCarOwner(newCarOwner);
                }
            }
            newPhoneNumber = inputter.inputString("Enter new phone number: ");
            if (!newPhoneNumber.isEmpty()) {
                if (newPhoneNumber.matches(Acceptable.PHONE_NUMBER_VALID)) {
                    carUpdateInfo.setPhoneNumber(newPhoneNumber);
                }
            }
            newCarBrand = inputter.inputString("Enter new car brand: ");
            if (!newCarBrand.isEmpty()) {
                if (newCarBrand.length() > 5 && newCarBrand.length() < 36) {
                    carUpdateInfo.setCarBrand(newCarBrand);
                }
            }
            String numString = inputter.inputString("Enter new number of seats: ");
            if (!numString.isEmpty()) {
                try {
                    newNumberOfSeat = Integer.parseInt(numString);
                    if (newNumberOfSeat > 4 && newNumberOfSeat < 36) {
                        carUpdateInfo.setNumberOfSeat(newNumberOfSeat);
                    }
                } catch (Exception e) {
                    System.out.println("Number of seats must be integer number");
                }
            }
            System.out.println("Update car information successfully");
            confirm = inputter.inputStringAndLoop("Do you want to continue with another update(Y/N): ", "Invalid confirm(Y orN)", Acceptable.YES_NO_VALID);
        } while (confirm.equalsIgnoreCase("y"));
    }

    public void deleteCarInfo() {
        String confirm;
        String licensePlate = inputter.inputStringAndLoop("Enter license plate to delete car information: ", "Invalid License plate\\nInclude a character describing the district code of Ho Chi Minh City (for example, P-Tan Binh, S-Binh Thanh, X-Thu Duc, ...) \\nCombined with a digit from 1 to 9, followed by 5 digits", Acceptable.LICENSE_PLATE_VALID);
        Car carDelete = findCarByLicensePlate(licensePlate);
        if (carDelete == null) {
            System.out.println("This vehicle has not registered yet.");
            return;
        }
        System.out.println("Vehicle Details: ");
        System.out.println("-----------------------------------------------------");
        System.out.println("License plate    : " + carDelete.getLicensePlate());
        System.out.println("Owner            : " + carDelete.getCarOwner());
        System.out.println("Phone            : " + carDelete.getPhoneNumber());
        System.out.println("Car brand        : " + carDelete.getCarBrand());
        System.out.println("Value of vehicle : " + carDelete.getPrice() + "$");
        System.out.println("Number of seats  : " + carDelete.getNumberOfSeat());
        System.out.println("Registration date: " + simpleDateFormat.format(carDelete.getRegistrationDate()));
        System.out.println("-----------------------------------------------------");
        confirm = inputter.inputStringAndLoop("Are you sure you want to delete this registration? (Y/N): ", "Invalid confirm(Y or N)", Acceptable.YES_NO_VALID);
        if (confirm.equalsIgnoreCase("y")) {
            cars.remove(carDelete);
            System.out.println("The registration has been successfully deleted.");
        } else {
            System.out.println("The registration has been fail.");
        }
    }

    public void addInsuranceStatement() throws ParseException {
        String insuranceID;
        String licensePlate;
        Date establishedDate;
        int insurancePeriod = 36;
        double insuranceFees;
        String insuranceOwner, confirmation;
        Car carInsurance;
        do {
            System.out.println("ADD A INSURANCE STATEMENT FOR A CAR");
            //input insurance ID
            while (true) {
                insuranceID = inputter.inputString("Enter insurance ID: ");
                if (insuranceID.isEmpty()) {
                    System.out.println("Cannot be empty.");
                    continue;
                }
                if (insuranceID.length() != 4 || checkDuplicateInsuranceID(insuranceID)) {
                    System.out.println("A unique 4-character string.");
                    continue;
                }
                break;
            }
            //input license plate
            while (true) {
                licensePlate = inputter.inputStringAndLoop("Enter license plate: ", "Invalid License plate\nInclude a character describing the district code of Ho Chi Minh City (for example, P-Tan Binh, S-Binh Thanh, X-Thu Duc, ...) \nCombined with a digit from 1 to 9, followed by 5 digits", Acceptable.LICENSE_PLATE_VALID);
                //check biển số xe có rỗng hay không
                if (licensePlate.isEmpty()) {
                    System.out.println("Cannot be empty");
                    continue;
                }
                //check biển số xe đã được them vào danh sách hay chưa
                if (!isRegistered(licensePlate)) {
                    System.out.println("License plate hasn't registered yet");
                    continue;
                }
                //check biển số xe có bị trùng lặp hay không
                if (checkDuplicateLicensePlateInInsuranceList(licensePlate)) {
                    System.out.println("This license plate was duplicated in insurance list");
                    continue;
                }
                break;
            }
            carInsurance = findCarByLicensePlate(licensePlate);
            //input established date
            while (true) {
                establishedDate = inputter.inputDateAndLoop("Enter established date: ", "Invalid date format(Day/Month/Year)");
                if (establishedDate == null) {
                    System.out.println("Cannot be empty.");
                    continue;
                }
                break;
            }
            //input insurance period
            while (true) {
                try {
                    System.out.print("Enter insurance period: ");
                    insurancePeriod = Integer.parseInt(sc.nextLine());
                    if (insurancePeriod != 12 && insurancePeriod != 24 && insurancePeriod != 36) {
                        System.out.println("Insurance period must be one of the following: 12, 24 or 36.");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Insurance period must be integer number");
                }
                switch (insurancePeriod) {
                    case 12:
                        insuranceFees = carInsurance.getPrice() * 0.25;
                        break;
                    case 24:
                        insuranceFees = (carInsurance.getPrice() * 0.2) * 2;
                        break;
                    default:
                        insuranceFees = (carInsurance.getPrice() * 0.15) * 3;
                        break;
                }
                break;
            }

            while (true) {
                insuranceOwner = inputter.inputString("Enter insurance owner: ");
                if (insuranceOwner.isEmpty()) {
                    System.out.println("Can not be blank");
                    continue;
                }
                if (insuranceOwner.length() < 2 || insuranceOwner.length() > 25) {
                    System.out.println("Length must be between 2 and 25 characters.");
                    continue;
                }
                break;
            }
            insurances.add(new Insurance(insuranceID, licensePlate, establishedDate, insurancePeriod, insuranceFees, insuranceOwner));
            confirmation = inputter.inputStringAndLoop("Do you want to continue entering new insurance statement(Y/N): ", "Invalid confirm(Y or N)", Acceptable.YES_NO_VALID);
        } while (confirmation.equalsIgnoreCase("y"));
    }

    public void listInsuranceStatements() throws NullPointerException {
        int no = 1;
        int year;
        if (insurances.isEmpty()) {
            System.out.println("Insurance list if empty");
            return;
        }
        String result = inputter.inputStringAndLoop("Enter year you want to list insurance: ", "Invalid year\nYou just need input year", "^\\d{4}$");
        try {
            year = Integer.parseInt(result);
        } catch (Exception e) {
            System.out.println("Year must be integer number");
            return;
        }
        insurances.sort(Comparator.comparing(Insurance::getEstablishedDate));
        System.out.println("Report : INSURANCE STATEMENTS");
        System.out.println("Sorted by: Established Date");
        System.out.println("Sort type : ASC ");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("No. |Insurance Id |Established Date | License plate|       Customer        | Insurance period| Insurance fees");
        for (Insurance insurance : insurances) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(insurance.getEstablishedDate());
            int insuranceYear = calendar.get(Calendar.YEAR);
            if (insuranceYear == year) {
                System.out.printf("%4d|%-13s|%-17s|%-14s|%-23s|%17d|$%5.2f\n", no, insurance.getInsuranceID(), simpleDateFormat.format(insurance.getEstablishedDate()), insurance.getLicensePlate(), insurance.getInsuranceOwner(), insurance.getInsurancePeriod(), insurance.getInsuranceFees());
            }
            no++;
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
    }

    public void reportUninsuredCar() {
        int no = 1;
        boolean hasUninsuredCar = false;
        cars.sort(Comparator.comparingInt(Car::getPrice).reversed());
        if (insurances.isEmpty() || cars.isEmpty()) {
            System.out.println("No information available");
            return;
        }
        System.out.println("Report: UNINSURED CARS");
        System.out.println("Sorted by : Value of vehicle");
        System.out.println("Sort type : DESC");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        System.out.println("No. |License plate| Registration date|      Vehicle Owner     |   Brand    | Number of seats| Value of vehicle");
        for (Car car : cars) {
            boolean isInsured = false;
            for (Insurance insurance : insurances) {
                if (car.getLicensePlate().equalsIgnoreCase(insurance.getLicensePlate())) {
                    isInsured = true;
                    break;
                }
            }
            if (!isInsured) {
                hasUninsuredCar = true;
                System.out.printf("%4d|%-13s|%-18s|%-24s|%-12s|%16d|$%17d\n", no, car.getLicensePlate(), simpleDateFormat.format(car.getRegistrationDate()), car.getCarOwner(), car.getCarBrand(), car.getNumberOfSeat(), car.getPrice());
                no++;
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        if (!hasUninsuredCar) {
            System.out.println("All cars are insured.");
        }
    }

    public void saveData() {
        int choice = 0;
        do {
            try {
                System.out.println("+--------Save data--------+");
                System.out.println("|1. Save car list         |");
                System.out.println("|2. Save insurance list   |");
                System.out.println("|3. Quit                  |");
                System.out.println("+-------------------------+");
                System.out.print("Your choice is: ");
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        saveCarListToFile();
                        break;
                    case 2:
                        saveInsuranceListToFile();
                        break;
                    case 3:
                        System.out.println("Stop saving");
                        return;
                    default:
                        System.out.println("Function not valid");
                }
            } catch (NumberFormatException e) {
                System.out.println("Choice must be integer");
            }
        } while (choice >= 1 && choice <= 2);
    }

    public void saveCarListToFile() {
        File carInfo = new File("C:\\Users\\HP\\Desktop\\CarInsuranceManagement\\src\\data\\CarInfo.txt");
        if (cars.isEmpty()) {
            System.out.println("Car list is empty");
            return;
        }
        if (!carInfo.exists()) {
            System.out.println("CarInfo.txt not exist");
            return;
        }
        try {
            try (PrintWriter printWriter = new PrintWriter(carInfo)) {
                for (Car car : cars) {
                    printWriter.println(car.toString());
                }
            }
            System.out.println("Save to file CarInfo.txt successfully");
        } catch (IOException e) {
            System.out.println("Error to save to file CaInfo.txt");
        }
    }

    public void saveInsuranceListToFile() {
        File insuranceFile = new File("C:\\Users\\HP\\Desktop\\CarInsuranceManagement\\src\\data\\Insurance.txt");
        if (!insuranceFile.exists()) {
            System.out.println("Insurance.txt not exist");
            return;
        }
        if (insurances.isEmpty()) {
            System.out.println("Insurance list is empty");
            return;
        }
        try {
            try (PrintWriter printWriter = new PrintWriter(insuranceFile)) {
                for (Insurance insurance : insurances) {
                    printWriter.println(insurance.toString());
                }
            }
            System.out.println("Save to file Insurance.txt successfully");
        } catch (IOException e) {
            System.out.println("Error to save to file Insurance.txt");
        }

    }

    public void menu() {
        System.out.println("+---------Car Insurance Management---------+");
        System.out.println("| 1. Add new car                           |");
        System.out.println("| 2. Find a car by license plate           |");
        System.out.println("| 3. Update car information                |");
        System.out.println("| 4. Delete car information                |");
        System.out.println("| 5. Add an insurance statement            |");
        System.out.println("| 6. List of insurance statements          |");
        System.out.println("| 7. Report uninsured cars                 |");
        System.out.println("| 8. Save data                             |");
        System.out.println("| 9. Quit                                  |");
        System.out.println("+------------------------------------------+");
        System.out.print("Your option is: ");
    }

    public void readFromFileCarInfo(String fileName) {
        File carInfo = new File(fileName);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(carInfo))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] token = line.split(",");

                if (token.length != 8) {
                    System.out.println("Error: Incorrect data format in file.");
                    continue;
                }

                try {
                    String licensePlate = token[0].trim();
                    String carOwner = token[1].trim();
                    String phoneNumber = token[2].trim();
                    String carBrand = token[3].trim();
                    int price = Integer.parseInt(token[4].trim());
                    Date registrationDate = simpleDateFormat.parse(token[5].trim());
                    String placeRegistration = token[6].trim();
                    int numberOfSeat = Integer.parseInt(token[7].trim());

                    cars.add(new Car(licensePlate, carOwner, phoneNumber, carBrand, price, registrationDate, placeRegistration, numberOfSeat));
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format in file.");
                } catch (ParseException e) {
                    System.out.println("Error: Invalid date format. Expected dd/MM/yyyy.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
    }

    public void readFromFileInsurance(String fileName) {
        File insuranceFile = new File(fileName);
        if (!insuranceFile.exists()) {
            System.out.println("Error: File " + fileName + " does not exist.");
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(insuranceFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] token = line.split(",");
                if (token.length != 6) {
                    System.out.println("Error: Incorrect data format in line: " + line);
                    continue;
                }
                try {
                    String insuranceID = token[0].trim();
                    String licensePlate = token[1].trim();
                    Date establishedDate = simpleDateFormat.parse(token[2].trim());
                    int insurancePeriod = Integer.parseInt(token[3].trim());
                    double insuranceFees = Double.parseDouble(token[4].trim());
                    String insuranceOwner = token[5].trim();
                    insurances.add(new Insurance(insuranceID, licensePlate, establishedDate, insurancePeriod, insuranceFees, insuranceOwner));
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format in line: " + line);
                } catch (ParseException e) {
                    System.out.println("Error: Invalid date format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + fileName);
        }
    }

    public boolean checkDuplicateLicensePlateInCarList(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateLicensePlateInInsuranceList(String licensePlate) {
        for (Insurance insurance : insurances) {
            if (insurance.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDuplicateInsuranceID(String insuranceID) {
        for (Insurance insurance : insurances) {
            if (insurance.getInsuranceID().equalsIgnoreCase(insuranceID)) {
                return true;
            }
        }
        return false;
    }

    public Car findCarByLicensePlate(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    public boolean isRegistered(String licensePlate) {
        for (Car car : cars) {
            if (car.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object apply(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
