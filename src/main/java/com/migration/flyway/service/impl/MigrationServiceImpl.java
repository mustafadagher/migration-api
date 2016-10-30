/**
 *
 */
package com.migration.flyway.service.impl;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.migration.flyway.bo.Datasource;
import com.migration.flyway.service.MigrationService;

/**
 * The Class MigrationServiceImpl.
 *
 * @author Mustafa Dagher
 */
@Service
public class MigrationServiceImpl implements MigrationService {

    @Autowired
    private WebApplicationContext context;

    /*
     * (non-Javadoc)
     * @see com.migration.flyway.service.impl.MigrationService#performMigration(com.migration.flyway.bo.Datasource)
     */
    @Override
    public int performMigration(final Datasource datasource) {
        final Flyway flyway = context.getBean("flyway", Flyway.class);
        flyway.setDataSource(datasource.getDbUrl(), datasource.getDbUserName(), datasource.getDbPassword());

        if (datasource.getSchemaLocation() != null && !datasource.getSchemaLocation().isEmpty()) {
            flyway.setLocations("filesystem:".concat(datasource.getSchemaLocation().trim()));
        }

        return flyway.migrate();
    }
}
