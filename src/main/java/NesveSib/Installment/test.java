package NesveSib.Installment;

import com.github.mfathi91.time.PersianDate;
import com.github.mfathi91.time.PersianMonth;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class test {

    public static void main(String[] args) {
        PersianDate today = PersianDate.now();
        PersianDate persianDate1 = PersianDate.of(1396, 7, 15);
        PersianDate persianDate2 = PersianDate.of(1396, PersianMonth.MEHR, 15);

// Convert
        PersianDate persianDate5 = PersianDate.of(1397, 5, 11);
        LocalDate gregDate = persianDate5.toGregorian();    // => '2018-08-02'
        PersianDate persianDate6 = PersianDate.fromGregorian(gregDate);  //  => '1397/05/11'

// Parse
        PersianDate persianDate3 = PersianDate.parse("1400-06-15");    // From the standard format
        PersianDate persianDate4 = PersianDate.parse("1400/06/15", DateTimeFormatter.ofPattern("yyyy/MM/dd"));    // From a desired format

// Format
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        dtf.format(PersianDate.now());    // => e.g. '1396/05/10'
        System.out.println(today);
        System.out.println(persianDate1);
        System.out.println(persianDate2);
        System.out.println(persianDate5);
        System.out.println(gregDate);
        System.out.println(persianDate6);
        System.out.println(persianDate3);
        System.out.println(persianDate4);

    }
}
