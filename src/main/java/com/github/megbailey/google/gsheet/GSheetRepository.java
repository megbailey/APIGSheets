package com.github.megbailey.google.gsheet;

import com.github.megbailey.google.GException;
import com.github.megbailey.google.api.request.APIVisualizationQueryUtility;
import com.github.megbailey.google.gspreadsheet.GSpreadsheet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Hashtable;

@Repository
public class GSheetRepository {
    private final GSpreadsheet gSpreadsheet;

    @Autowired
    public GSheetRepository(GSpreadsheet gSpreadsheet) {
        this.gSpreadsheet = gSpreadsheet;
    }

    public String getTable(String tableName) throws IOException {
        return this.gSpreadsheet.getGSheet(tableName).toString();
    }

    public JsonArray all(String tableName) throws IOException {
        return this.gSpreadsheet.executeQuery(tableName, "select *");
    }

    public JsonArray query(String tableName, String[] constraints) throws IOException, GException {
        return this.gSpreadsheet.executeQuery(tableName, "select *", constraints);
    }

}
