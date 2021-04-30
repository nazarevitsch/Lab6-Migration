import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class OrderMigrationTests {

    @Test
    public void testMigratedOrders() {
        try {
            Migration.executeMigration(QueriesToDB.addColumnToOrderTable);
            DataExporter.exportDataFromDb(PathToFiles.orderResult,
                    QueriesToDB.getSELECTQueryOrderById("orders"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("orders")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.orderExpected, PathToFiles.orderResult, PathToFiles.ordersLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.executeMigration(QueriesToDB.deleteColumnInOrdersTable);
        Assert.assertTrue(equality);
    }

    @Test
    public void testNonMigratedOrdersReturnAllRowsEquals() {
        try {
            DataExporter.exportDataFromDb(PathToFiles.orderResult,
                    QueriesToDB.getSELECTQueryOrderById("orders"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("orders")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.orderExpected, PathToFiles.orderResult, PathToFiles.ordersLogFile);
        boolean equality = csvComparer.compareCSV();
        Assert.assertTrue(equality);
    }

    @Test
    public void testMigratedOrdersWithGroupBy() {
        try {
            Migration.executeMigration(QueriesToDB.addColumnToOrderTable);
            DataExporter.exportDataFromDb(PathToFiles.orderResultWithGroupBy,
                    QueriesToDB.SELECTQuerySumPriceGroupBy2ForOrders,
                    DataExporter.getTableColumnsNames(QueriesToDB.selectOrderCategoryIDAndPriceAndCompany));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.orderExpectedWithGroupBy, PathToFiles.orderResultWithGroupBy, PathToFiles.ordersLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.executeMigration(QueriesToDB.deleteColumnInOrdersTable);
        Assert.assertTrue(equality);
    }
}
