package Dao;

import Model.Database.ConexionBD;
import Model.Database.ErrorsAndSuccesses;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hitler
 */
public class TablasDB {

    private static ResultSet tables;
    private final ConexionBD conexion = ConexionBD.getInstance();

    public boolean ComprobarTablas() {
        String tablasFaltantes = "";
        boolean allTablesExist = true;

        try {
            DatabaseMetaData metaData = conexion.conectar().getMetaData();
            tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            List<String> existingTables = new ArrayList<>();
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME").toLowerCase();
                existingTables.add(tableName);
            }

            List<String> requiredTables = Arrays.asList("persona", "docente", "alumno", "dispositivo", "mac", "ipad", "prestamos", "notas");

            for (String requiredTable : requiredTables) {
                if (!existingTables.contains(requiredTable.toLowerCase())) {
                    tablasFaltantes += requiredTable + ", ";
                    allTablesExist = false;
                }
            }

            if (!tablasFaltantes.isEmpty()) {
                tablasFaltantes = tablasFaltantes.substring(0, tablasFaltantes.length() - 2);
                ErrorsAndSuccesses.setCadenaErrorBD(tablasFaltantes);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            ErrorsAndSuccesses.setCadenaErrorBD(ex.toString());
        }
        return allTablesExist;
    }
}
