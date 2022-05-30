package com.github.megbailey.google.gspreadsheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GSpreadsheetService {
    private final GSpreadsheetRepository gSpreadsheetRepository;

    @Autowired
    public GSpreadsheetService(GSpreadsheetRepository gSpreadsheetRepository) {
        this.gSpreadsheetRepository = gSpreadsheetRepository;
    }

    public Integer createGSheet(String name) throws IOException {
        return this.gSpreadsheetRepository.createGSheet(name);
    }
    //this.setColumns(this.spreadsheet.getRegularService().getData(this.getName(), "$A1:$Z1").get(0));



}
