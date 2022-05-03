package com.github.megbailey.gsheets.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GSheet {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final GSpreadsheet spreadsheet;
    private Integer Id;
    private String name;
    private HashMap<String, String> labelToName;

    /* TODO:
    * - grab a list of the cells in <ColumnID>1
    * - map the label name to the column ID (letter) for graph viz queries
    * - figure out how keep track of the filled range
    * - append data to next available row in spreadsheet
    */

    public GSheet(GSpreadsheet spreadsheet, String name, Integer Id) {
        this.spreadsheet = spreadsheet;
        this.name = name;
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getID() {
        return this.Id;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setID(Integer newId) {
        this.Id = newId;
    }

    /*
    public List<List<Object>> getData(String range) {
        try {
            return this.spreadsheet.getRegularService().getData(this.name, range);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
     */

    public void updateData(String range, List<Object> values) {
        try {
            this.spreadsheet.getRegularService().updateData(this.name, range, values);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}