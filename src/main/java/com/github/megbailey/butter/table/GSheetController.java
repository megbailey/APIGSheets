package com.github.megbailey.butter.table;

import ch.qos.logback.core.util.IncompatibleClassException;
import com.github.megbailey.butter.ObjectModel;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> all(@PathVariable("table") String tableName ) {
        try {
            JsonArray values = this.gSheetService.all(tableName);
            return ResponseEntity.status( HttpStatus.ACCEPTED ).body( values.toString() );
        } catch ( TableNotFound e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "Resource was not found." );
        } catch ( EmptyContentException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "empty" );
        }
    }

    /*
       Query objects in the table
    */
    @GetMapping( path = "/{table}/{constraints}", produces = "application/json" )
    public ResponseEntity<String> query( @PathVariable("table") String tableName,
                                          @PathVariable("constraints") String constraints ) {
        try {
            String queryResults = this.gSheetService.query(tableName, constraints).toString();
            return ResponseEntity.status( HttpStatus.ACCEPTED ).body( queryResults );
        } catch ( InvalidQueryException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Invalid query parameters." );
        } catch ( EmptyContentException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.NO_CONTENT ).body( "empty" );
        }
    }

    /*
        Create objects in the table
    */
    @PostMapping( path = "/{table}/create", consumes = "application/json")
    public ResponseEntity<String> create( @PathVariable("table") String table,
                                          @RequestBody ObjectModel payload ) {
        try {
            this.gSheetService.create( table, payload );
            return ResponseEntity.status( HttpStatus.CREATED ).body( "Resource created." );
        } catch ( IncompatibleClassException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Unable to create resource." );
        }
    }


    //TODO: Right now, it is only possible to delete by range because
    // GViz does not return affected rows.

    /*
        Delete a specific object in the table
    *//*

    @DeleteMapping( path = "/{table}/delete" )
    public @ResponseBody Object delete( @PathVariable String table, @RequestBody Object object ) {
        //return new ResponseEntity<>("delete item " , HttpStatus.OK);
        return null;
    }

    *//*
        Delete objects filtered by str in the table
    *//*

    @DeleteMapping( path = "/{table}/{delete}" )
    public @ResponseBody Object delete( @PathVariable String table, @PathVariable String deleteStr ) {
        //return new ResponseEntity<>("delete item " , HttpStatus.OK);
        return null;
    }*/

}
