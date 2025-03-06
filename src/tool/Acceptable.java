package tool;

public interface Acceptable {
    String LICENSE_PLATE_VALID = "^(5[0-9])[a-zA-Z]([0-9]\\d{5})$";
    String PHONE_NUMBER_VALID = "^(086|096|097|098|032|033|034|035|036|037|038|039|091|094|088|081|082|083|084|085|090|093|089|070|076|077|078|079|092|056|058|099|059|087)\\d{7}$";
    String DATE_FORMAT_VALID = "^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    String YES_NO_VALID = "^[YyNn]$";
    static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
