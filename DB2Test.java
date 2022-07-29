import java.sql.*;
import java.util.Scanner;


public class DB2Test {

    private Connection connection;
    private Statement statement;
    private static ResultSet resultSet;


    public void openConnection() {

// Step 1: Load IBM DB2 JDBC driver

        try {

            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());

        } catch (Exception cnfex) {

            System.out.println("Problem in loading or registering IBM DB2 JDBC driver");

            cnfex.printStackTrace();
        }

// Step 2: Opening database connection


        try {

            connection = DriverManager.getConnection("jdbc:db2://62.44.108.24:50000/SAMPLE", "db2admin", "db2admin");

            statement = connection.createStatement();

        } catch (SQLException s) {

            s.printStackTrace();

        }

    }

    public void closeConnection() {

        try {

            if (null != connection) {

                // cleanup resources, once after processing

                resultSet.close();

                statement.close();


                // and then finally close connection

                connection.close();

            }

        } catch (SQLException s) {

            s.printStackTrace();

        }

    }

    public void select(String stmnt) {

        try {

            resultSet = statement.executeQuery(stmnt);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int column = resultSetMetaData.getColumnCount();
            String result = "";

            while (resultSet.next()) {

                for (int i = 1; i <= column; i++) {

                    result += resultSet.getString(i);

                    if (i == column) result += " \n";
                    else result += ", ";
                }
            }

            System.out.println("Executing query: " + stmnt + "\n");
            System.out.println("Result output \n");
            System.out.println("---------------------------------- \n");
            System.out.println(result);

        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void insert(String stmnt) {

        try {

            statement.executeUpdate(stmnt);

        } catch (SQLException s) {

            s.printStackTrace();

        }

        System.out.println("Successfully inserted!");

    }


    public void delete(String stmnt) {

        try {

            statement.executeUpdate(stmnt);

        } catch (SQLException s) {

            s.printStackTrace();

        }

        System.out.println("Successfully deleted!");

    }

    public static void main(String[] args) {

        DB2Test db2Obj = new DB2Test();
        String statement = "";
        String name = "Valentin Zhelezov";
        int work_number = 789;
        String phone_number = "0881111111";
        String city = "Pernik";

        db2Obj.openConnection();

        statement = "SELECT F.NAME, F.GOALS,T.CITY " +
                "FROM FN72097.FOOTBALLER F,FN72097.TEAM T " +
                "WHERE T.NAME=F.TEAM_NAME " +
                "AND F.NAME LIKE 'J%'";

//        statement = "SELECT R.WORK_NUMBER, M.TEAM_NAME1,M.RESULT,M.TEAM_NAME2 " +
//                "FROM FN72097.REFEREE R, FN72097.MATCH M " +
//                "WHERE M.REFEREE_NAME=R.NAME " +
//                "AND R.CITY = 'Plovdiv' " +
//                "AND M.TEAM_NAME1 = 'CSKA'";
        db2Obj.select(statement);
        System.out.println("-----------------------");
        statement = " INSERT INTO FN72097.REFEREE (WORK_NUMBER, NAME, PHONE_NUMBER, CITY)" +
                " VALUES ('" + work_number + "','" + name + "','" + phone_number + "','" + city + "')";

      //   db2Obj.insert(statement);


        statement = "DELETE FROM FN72097.REFEREE " +
                "WHERE WORK_NUMBER = 789";
          //   db2Obj.delete(statement);

        if (resultSet != null)
            db2Obj.closeConnection();
    }

}
