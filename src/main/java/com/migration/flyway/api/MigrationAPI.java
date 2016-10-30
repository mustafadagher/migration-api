package com.migration.flyway.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.migration.flyway.bo.Datasource;
import com.migration.flyway.facade.MigrationFacade;
import com.migration.flyway.service.MigrationService;

import io.swagger.annotations.ApiParam;

/**
 * The Rest API MigrationAPI.
 *
 * @author Mustafa Dagher
 */
@Component
public class MigrationAPI implements MigrationFacade {

    @Autowired
    private MigrationService migrationService;

    /*
     * (non-Javadoc)
     * @see com.migration.flyway.api.MigrationFacade#migrate(com.migration.flyway.bo.Datasource)
     */
    @Override
    public ResponseEntity<?> migrate(final @ApiParam(value = "The Datasource Information.", required = true) @Valid @RequestBody Datasource datasource) {

        final int response = migrationService.performMigration(datasource);
        if (response > 0) {
            return new ResponseEntity<Integer>(response, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Schema is up to date. No migration necessary.");
        }

    }

    /*
     * (non-Javadoc)
     * @see com.migration.flyway.api.MigrationFacade#migrateMultiple(com.migration.flyway.bo.Datasource[])
     */
    @Override
    public ResponseEntity<?> migrateMultiple(
            final @ApiParam(value = "The Datasource Information array.", required = true) @Valid @RequestBody Datasource[] datasources) {

        int response = 0;
        for (final Datasource datasource : datasources) {
            response += migrationService.performMigration(datasource);
        }
        if (response > 0) {
            return new ResponseEntity<Integer>(response, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Schema's are up to date. No migration necessary.");
        }
    }

}
