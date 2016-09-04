package com.migration.flyway.service;

import com.migration.flyway.bo.Datasource;

/**
 * The Interface MigrationService.
 *
 * @author Mustafa Dagher
 */
public interface MigrationService {

    /**
     * Starts the database migration. All pending migrations will be applied in order. Calling migrate on an up-to-date
     * database has no effect.
     *
     * @param datasource the datasource information to migrate.
     * @return The number of successfully applied migrations.
     */
    int performMigration(Datasource datasource);

}
