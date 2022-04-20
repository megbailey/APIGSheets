package com.github.megbailey.gsheets.api.request;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class APIRequestController extends APIRequest {
    // Singleton class
    private static APIRequestController instance;
    // Delegators
    private APIGetRequest getRequest;
    private APIBatchRequest batchRequest;

    private APIRequestController(String spreadsheetID, Sheets sheetsService) {
        super(spreadsheetID, sheetsService);
        this.getRequest = new APIGetRequest(this.getSpreadsheetID(), this.getSheetsService());
        this.batchRequest = new APIBatchRequest(this.getSpreadsheetID(), this.getSheetsService());
    }

    public static synchronized APIRequestController getInstance(String spreadsheetID, Sheets sheetsService) {
        if (instance == null) {
            instance = new APIRequestController(spreadsheetID, sheetsService);
        }
        return instance;
    }

    public List<Sheet> getSpreadsheetSheets() throws IOException {
        Spreadsheet response = this.getRequest.getSpreadsheetRequest().execute();
        return response.getSheets();
    }

    public SpreadsheetProperties getSpreadsheetProperties() throws IOException {
        Spreadsheet response = this.getRequest.getSpreadsheetRequest().execute();
        return response.getProperties();
    }

    public List<List<Object>> getData(String sheetName, String range) throws IOException {
        return this.getRequest.getData(sheetName, range);
    }

    public Integer addCreateSheetToBatch(String sheetName) {
        return this.batchRequest.createSheetRequest(sheetName);
    }

    public void addDeleteSheetToBatch(Integer sheetID) {
        this.batchRequest.deleteSheetRequest(sheetID);
    }

    public boolean executeBatch() throws IOException {
        return this.batchRequest.executeBatchRequests();
    }

}
