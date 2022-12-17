package com.github.megbailey.butter.db;

import com.github.megbailey.butter.google.exception.BadRequestException;
import com.github.megbailey.butter.google.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class ButterDBController {
    private ButterDBService butterDBService;

    @Autowired
    ButterDBController(ButterDBService butterDBService)  {
        this.butterDBService = butterDBService;
    }

    @GetMapping("/")
    public String index() {
        return "Welcome to ButterDB!";
    }

    /*
        Create a new object table. Equivalent of creating a new sheet in the spreadsheet.
        TODO: pass in a object structure and make post mapping
    */
    @PutMapping( path = "/create/{newTable}" )
    public ResponseEntity<String> create( @PathVariable("newTable") String sheetName ) {
        try {
            this.butterDBService.create(sheetName);
            return ResponseEntity.status( HttpStatus.CREATED ).body( "Resource created" );
        } catch ( BadRequestException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
        }
    }


    /*
        Bad path / incorrect param for creating a table in the spreadsheet.
    */
    @PutMapping( path = "/create" )
    public ResponseEntity<String> create( ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Resource was not found on the server." );
    }


    /*
        Delete an object table in the ButterDB. Equivalent of deleting a Google Sheet.
    */
    @DeleteMapping( path = "/delete/{tableName}" )
    public ResponseEntity<String> delete(@PathVariable("tableName") String tableName ) {
        try {
            this.butterDBService.delete(tableName);
            return ResponseEntity.status( HttpStatus.OK ).body( "Resource deleted" );
        } catch ( ResourceNotFoundException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( e.getMessage() );
        }
    }


    /*
        Bad path / incorrect param for deleting a table in the spreadsheet.
    */
    @DeleteMapping( path = "/delete" )
    public ResponseEntity<String> delete( ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Resource was not found on the server." );
    }


}
