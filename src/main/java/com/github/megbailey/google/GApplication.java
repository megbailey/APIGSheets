package com.github.megbailey.google;

import com.github.megbailey.google.api.GAuthentication;
import com.github.megbailey.google.gspreadsheet.GSpreadsheet;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.security.GeneralSecurityException;

/*
    This is the main thread which starts the ORM.
*/
@SpringBootApplication
public class GApplication {
    private static GLogHandler logger = new GLogHandler();

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(GApplication.class, args);
    }

/*
    The GSpreadsheet bean is given to both the GSpreadsheet & GSheet Repositories
*/
    @Bean
    public GSpreadsheet getGSpreadsheet() throws GeneralSecurityException, IOException {
        GAuthentication gAuthentication = new GAuthentication("1hKQc8R7wedlzx60EfS820ZH5mFo0gwZbHaDq25ROT34");
        gAuthentication.authenticateWithServiceAccount();
        GSpreadsheet gSpreadsheet = new GSpreadsheet(gAuthentication);
        return gSpreadsheet;
    }
}
