package com.github.megbailey.google.gsheet;

import com.github.megbailey.google.ObjectModel;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    /*
        Get a GSheet - Return all data in the table
    */
    @GetMapping( path = "/{table}" )
    public @ResponseBody String all(@PathVariable("table") String tableName ) {
        JsonArray values = this.gSheetService.all(tableName);
        return values.toString();
    }

    /*
       Query objects in the table
    */
    @GetMapping( path = "/{table}/{constraints}", produces = "application/json" )
    public @ResponseBody Object filter( @PathVariable("table") String tableName,
                                        @PathVariable("constraints") String constraints ) {
        return this.gSheetService.query(tableName, constraints).toString();
    }



    /*
        Create objects in the table
    */
    @PostMapping( path = "/create/{table}", consumes = "application/json")
    public @ResponseBody String create( @PathVariable("table") String table,
                                        @RequestBody ObjectModel payload ) {
        ObjectModel newObject = this.gSheetService.create(table, payload);
        return newObject.toString();
    }


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
