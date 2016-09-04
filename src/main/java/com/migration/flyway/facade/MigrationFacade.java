package com.migration.flyway.facade;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.migration.flyway.bo.Datasource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Interface MigrationFacade.
 *
 * @author Mustafa Dagher
 */
@Api(value = "migration", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/migration")
public interface MigrationFacade {

    /**
     * Migrate database.
     *
     * @param datasource the datasource information for the databases intended to migrate
     * @return the response entity with Status 200 if migrated.
     */
    @ApiOperation(value = "migrate DB", nickname = "migrateDB", notes = " A status code of " + HttpURLConnection.HTTP_OK
            + " is returned if migration is done successfully.", produces = MediaType.APPLICATION_JSON_VALUE, response = Integer.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Migration Succeeded."),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_MODIFIED, message = "No Migration Done.") })
    @PostMapping
    @Valid
    ResponseEntity<?> migrate(@ApiParam(name = "datasource", value = "The Datasource Information.", required = true) @Valid @RequestBody Datasource datasource);

    /**
     * Migrate multiple databases.
     *
     * @param datasources the datasources information for the databases intended to migrate
     * @return the response entity with Status 200 if migrated.
     */
    @ApiOperation(value = "migrate Multiple Databases", nickname = "migrateMultipleDB", notes = " A status code of " + HttpURLConnection.HTTP_OK
            + " is returned if migration is done successfully.", produces = MediaType.APPLICATION_JSON_VALUE, response = Integer.class, httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Migration Succeeded."),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_MODIFIED, message = "No Migration Done.") })
    @PostMapping(path = "/batch")
    @Valid
    ResponseEntity<?> migrateMultiple(
            @ApiParam(name = "datasources", value = "The Datasource Information array.", required = true) @Valid @RequestBody Datasource[] datasources);

}
