/**
 *
 */
package com.migration.flyway.service.impl;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

import com.migration.flyway.bo.Datasource;
import com.migration.flyway.service.MigrationService;

/**
 * The Class MigrationServiceImpl.
 *
 * @author Mustafa Dagher
 */
@Component
public class MigrationServiceImpl implements MigrationService {

    /*
     * (non-Javadoc)
     * @see com.migration.flyway.service.impl.MigrationService#performMigration(com.migration.flyway.bo.Datasource)
     */
    @Override
    public int performMigration(final Datasource datasource) {

        final Flyway flyway = new Flyway();
        flyway.setDataSource(datasource.getDbUrl(), datasource.getDbUserName(), datasource.getDbPassword());

        if (datasource.getSchemaLocation() != null && !datasource.getSchemaLocation().isEmpty()) {
            flyway.setLocations("filesystem:".concat(datasource.getSchemaLocation().trim()));
        }

        return flyway.migrate();
    }
}
