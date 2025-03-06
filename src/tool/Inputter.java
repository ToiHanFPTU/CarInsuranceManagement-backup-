package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Inputter {
    public Scanner sc = new Scanner(System.in);
    public String inputString(String mess) {
        System.out.print(mess);
        return sc.nextLine();
    }
    public String inputStringAndLoop(String mess, String messError, String pattern) {
        String result;
        while (true) {
            result = inputString(mess);
            if (!Acceptable.isValid(result, pattern)) {
                System.out.println(messError);
                continue;
            }
            break;
        }
        return result;
    }
    public int inputIntegerAndLoop(String mess, String messError, int min, int max) {
        int result;
        while (true) {
            try {
                result = Integer.parseInt(inputString(mess));
                if (result < min || result > max) {
                    System.out.println(messError);
                    continue;
                }

                return result;
            } catch (Exception e) {
                System.out.println("Must be integer number");
            }
        }
    }
    public double inputDoubleAndLoop(String mess, String messError) {
        double result;
        while (true) {
            try {
                result = Double.parseDouble(inputString(mess));
                if (result < 0) {
                    System.out.println(messError);
                    continue;
                }
                return result;
            } catch (Exception e) {
                System.out.println("Must be real number");
            }
        }
    }
    public Date inputDateAndLoop(String mess, String messError) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date result;
        simpleDateFormat.setLenient(false);
        while (true) {
            String dateValid = inputString(mess);
            if (!Acceptable.isValid(dateValid, Acceptable.DATE_FORMAT_VALID)) {
                System.out.println(messError);
                continue;
            }
            result = simpleDateFormat.parse(dateValid);
            break;
        }
        return result;
    }
}
