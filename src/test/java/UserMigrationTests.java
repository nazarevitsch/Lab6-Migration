import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class UserMigrationTests {

    @Test
    public void testNonMigratedOrdersReturnAllRowsEquals() {
        try {
            DataExporter.exportDataFromDb(PathToFiles.userResult,
                    QueriesToDB.getSELECTQueryOrderById("users"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("users")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.userExpected, PathToFiles.userResult, PathToFiles.usersLogFile);
        boolean equality = csvComparer.compareCSV();
        Assert.assertTrue(equality);
    }

    @Test
    public void testMigratedOrders() {
        try {
            Migration.executeMigration(QueriesToDB.addColumnToUsersTable);
            DataExporter.exportDataFromDb(PathToFiles.userResult,
                    QueriesToDB.getSELECTQueryOrderById("users"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("users")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.userExpected, PathToFiles.userResult, PathToFiles.usersLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.executeMigration(QueriesToDB.deleteColumnInUsersTable);
        Assert.assertTrue(equality);
    }
}
