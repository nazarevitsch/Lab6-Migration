import db.BasicConnectionPool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataExporter {

    public static void exportDataFromDb(String fileOut, String query, List<String> columns) throws IOException, SQLException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOut));
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        while (result.next()){
            StringBuffer totalLine = new StringBuffer();
            for (int i = 0; i < columns.size(); i++) {
                if(i == columns.size()-1){
                    totalLine.append(result.getObject(columns.get(i)).toString());
                }else totalLine.append(result.getObject(columns.get(i)).toString()+",");
            }
            totalLine.append("\n");
            fileWriter.write(totalLine.toString());
        }
        fileWriter.close();
        statement.close();
    }

    public static List<String> getTableColumnsNames(String query){
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<String> columns = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            for (int it = 1; it <= count ; it++) {
                columns.add(metaData.getColumnName(it));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columns;
    }
}
