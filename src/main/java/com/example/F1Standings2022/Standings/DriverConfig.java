package com.example.F1Standings2022.Standings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class DriverConfig {

    private List<Driver> driverList() {
        List<Driver> result = new ArrayList<>();

        try {
            URL url = new URL("url");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    String temp = scan.nextLine();
                    System.out.println(temp);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        //System.out.println(inline);

        return result;
    }

    @Bean
    CommandLineRunner commandLineRunner(DriverRepository driverRepository) {
        return args -> {
            Driver lewishamilton = new Driver(
                    44,
                    "Lewis Hamilton",
                    "Mercedes",
                    109
            );

            Driver valterribottas = new Driver(
                    77,
                    "Valterri Bottas",
                    "Alfa Romeo",
                    46
            );

            driverRepository.saveAll(List.of(lewishamilton,valterribottas));
        };
    }

}
