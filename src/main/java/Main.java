public class Main {

    public static void main(String[] args) throws Exception{

        DataExporter.exportDataFromDb(PathToFiles.userExpected,
                QueriesToDB.getSELECTQueryOrderById("users"),
                DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("users")));

        DataExporter.exportDataFromDb(PathToFiles.orderExpected,
                QueriesToDB.getSELECTQueryOrderById("orders"),
                DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("orders")));

        DataExporter.exportDataFromDb(PathToFiles.orderExpectedWithGroupBy,
                QueriesToDB.SELECTQuerySumPriceGroupBy1ForOrders, DataExporter.getTableColumnsNames(QueriesToDB.selectOrderCategoryIDAndPrice));
    }
}
