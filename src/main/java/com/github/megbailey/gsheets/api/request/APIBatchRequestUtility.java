package com.github.megbailey.gsheets.api.request;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteSheetRequest;
import com.google.api.services.sheets.v4.model.Request;

import java.io.IOException;
import java.util.List;

public class APIBatchRequestUtility extends APIRequest {
    private static APIBatchRequestUtility instance;
    private List<Request> requests;
    private APIBatchUpdateRequestFactory batchUpdate;

    private APIBatchRequestUtility(String spreadsheetID, Sheets sheetService)  {
        super(spreadsheetID, sheetService);
        this.batchUpdate = new APIBatchUpdateRequestFactory(spreadsheetID, sheetService);
    }

    public static synchronized APIBatchRequestUtility getInstance(String spreadsheetID, Sheets sheetsService) {
        if (instance == null) {
            instance = new APIBatchRequestUtility(spreadsheetID, sheetsService);
        }
        return instance;
    }

    public boolean executeBatch() throws IOException {
        if (!requests.isEmpty()) {
            BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest();
            requestBody.setRequests(requests);
            Sheets.Spreadsheets.BatchUpdate request =
                    this.getSheetsService().spreadsheets().batchUpdate(this.getSpreadsheetID(), requestBody);
            request.execute();
            this.requests.clear();
            return true;
        }
        return false;
    }

    public Integer createSheet(String sheetName) {
        AddSheetRequest addSheetRequest = this.batchUpdate.createSheetRequest(sheetName);
        this.requests.add(new Request().setAddSheet(addSheetRequest));
        return addSheetRequest.getProperties().getSheetId();
    }

    public void deleteSheet(Integer sheetID)  {
        DeleteSheetRequest deleteSheetRequest = this.batchUpdate.deleteSheetRequest(sheetID);
        this.requests.add(new Request().setDeleteSheet(deleteSheetRequest));
    }

}
