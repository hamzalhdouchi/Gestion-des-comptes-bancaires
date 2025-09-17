package Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

public class Helper {

    public static String genererUUID() {
        return UUID.randomUUID().toString();
    }
    public static String genererCodeCompte() {
        Random random = new Random();
        int nombre = random.nextInt(100000);
        return String.format("CPT-%05d", nombre);
    }

    public static String genererDateOperation() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }
}
