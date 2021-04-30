public class QueriesToDB {

    public static final String addColumnToOrderTable = "ALTER TABLE orders\n" +
            "ADD COLUMN company varchar(255) not null default 'Ritter Company';";
    public static String deleteColumnInOrdersTable = "ALTER TABLE orders\n" +
            "drop COLUMN company;";
    public static final String addColumnToUsersTable = "ALTER TABLE users\n" +
            "ADD COLUMN username varchar(255) not null default 'ritter';";
    public static String deleteColumnInUsersTable = "ALTER TABLE users\n" +
            "drop COLUMN username;";


    public static String selectOrderCategoryIDAndPrice = "select category_id, price from orders";
    public static String selectOrderCategoryIDAndPriceAndCompany = "select category_id, price, company from orders";


    public static final String SELECTQuerySumPriceGroupBy1ForOrders = "select category_id, SUM(price) as price from orders GROUP BY category_id";
    public static final String SELECTQuerySumPriceGroupBy2ForOrders = "select category_id, company, SUM(price) as price from orders GROUP BY category_id, company";

    public static String getSELECTQueryOrderById (String tableName) {
        return "select * from " + tableName + " order by id";
    }

    public static String getSELECTQuery (String tableName) {
        return "select * from " + tableName;
    }
}
