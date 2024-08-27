package NesveSib.Installment.utils;

import NesveSib.Installment.security.PasswordEncryptor;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectInternalTools {

    public static Logger logger = (Logger) LoggerFactory.getILoggerFactory().getLogger("log");

    public static PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    public static String trimPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("09"))
            return phoneNumber.substring(1);
        else if (phoneNumber.startsWith("98"))
            return phoneNumber.substring(2);
        return phoneNumber;
    }
}
