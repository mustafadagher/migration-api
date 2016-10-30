package com.migration.flyway.bo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * The Class Datasource.
 *
 * @author Mustafa Dagher
 */
// @ApiModel(value = "Datasource", description = "The datasource information entity.")
public class Datasource {

    @ApiModelProperty(notes = "The Database JDBC URL.", required = true, position = 0, example = "jdbc:mysql://host:port/database")
    @NotNull
    @Size(min = 1)
    @Pattern(regexp = "^(?:jdbc:)(?:.*)://(?:.*)/(?:.*)")
    private String dbUrl;

    @ApiModelProperty(notes = "The Database User Name.", required = true, position = 1)
    @NotNull
    @Size(min = 1)
    private String dbUserName;

    @ApiModelProperty(notes = "The Database Password", required = true, position = 2)
    @NotNull
    @Size(min = 1)
    private String dbPassword;

    @ApiModelProperty(notes = "The file system path for the migration scrips folder", required = false, position = 3, example = "C:\\path\\to\\migration")
    private String schemaLocation;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(final String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(final String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(final String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public void setSchemaLocation(final String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }

}
