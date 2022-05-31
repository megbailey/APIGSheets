package com.github.megbailey.google.gsheet;

import com.github.megbailey.google.gspreadsheet.GSheetService;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "/api/v1/orm")
public class GSheetController {
    private GSheetService gSheetService;

    @Autowired
    GSheetController(GSheetService gSheetService)  {
        this.gSheetService = gSheetService;
    }

    @GetMapping("/")
    public String index() {
        return "GSheet ORM index.";
    }


//    /*
//        Create a GSheet - Return ID
//    */
//    @PostMapping( path = "/create/{table}" )
//    public @ResponseBody Object create( @PathVariable String sheetName ) {
//        //return new ResponseEntity<>("create item " , HttpStatus.CREATED);
//
//        return null;
//    }
//
//    /*
//        Delete a GSheet - Return ID
//    */
//    @DeleteMapping( path = "/delete/{table}" )
//    public @ResponseBody Object delete( @PathVariable String tableName ) {
//        //return new ResponseEntity<>("create item " , HttpStatus.CREATED);
//
//        return null;
//    }
//
//    /*
//        Delete a GSheet - Return name
//    */
//    @DeleteMapping( path = "/delete/{tableID}" )
//    public @ResponseBody Object delete( @PathVariable Integer tableID ) {
//        //return new ResponseEntity<>("create item " , HttpStatus.CREATED);
//
//        return null;
//    }

    /*
        Get a GSheet - Return all data in the table
    */
    @GetMapping( path = "/{table}" )
    public @ResponseBody
    JsonArray all(@PathVariable("table") String tableName ) {
        try {
            return this.gSheetService.getGSheet(tableName);
        } catch (IOException e) {
            JsonArray ar = new JsonArray();
            ar.add("noelements");
            return ar;
        }
    }

    /*
        Get a GSheet - Return all data in the table
    */
//    @GetMapping( path = "/{tableID}" )
//    public @ResponseBody Object all( @PathVariable("tableID") Integer tableID ) {
//        //return new ResponseEntity<>("filter item " , HttpStatus.ACCEPTED);
//        return null;
//    }


    /*
       Filter objects in the table
    */
//    @GetMapping( path = "/filter/{table}/{filter}" )
//    public @ResponseBody Object filter( @PathVariable String table, @PathVariable String filterStr ) {
//        System.out.println(filterStr);
//        String[] filterAssignments = filterStr.split(",");
//
//        String[] attributeValue;
//        HashMap filterMap = new HashMap();
//        for (String filter: filterAssignments) {
//            attributeValue = filter.split("=");
//            filterMap.put( attributeValue[0], attributeValue[1] );
//        }
//        Integer sheetID = this.gSpreadsheetService.getGSheet(table).getID();
////        String query = this.gVizRequestUtility.formGVizQuery( filterMap, sheetID );
////
////        this.gVizRequestUtility.executeGVizQuery( query );
//        //return new ResponseEntity<>("filter item " , HttpStatus.ACCEPTED);
//        return null;
//    }



//    /*
//        Create objects in the table
//     */
//    @PostMapping( path = "/create/{table}" )
//    public @ResponseBody Object create( @PathVariable String table, @RequestBody Object object ) {
//        //return new ResponseEntity<>("create item " , HttpStatus.CREATED);
//        return null;
//    }
//
//
//    /*
//        Delete a specific object in the table
//     */
//    @DeleteMapping( path = "/delete/{table}" )
//    public @ResponseBody Object delete( @PathVariable String table, @RequestBody Object object ) {
//        //return new ResponseEntity<>("delete item " , HttpStatus.OK);
//        return null;
//    }
//
//    /*
//        Delete objects filtered by str in the table
//    */
//    @DeleteMapping( path = "/delete/{table}/{delete}" )
//    public @ResponseBody Object delete( @PathVariable String table, @PathVariable String deleteStr ) {
//        //return new ResponseEntity<>("delete item " , HttpStatus.OK);
//        return null;
//    }





}
