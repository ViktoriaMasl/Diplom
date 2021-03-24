package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_bd {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    public static void clearDB() {
        val deleteOrder = "DELETE FROM order_entity";
        val deletePayment = "DELETE FROM payment_entity";
        val deleteCreditRequest = "DELETE FROM credit_request_entity";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(url, user, password);
                ) {
            runner.update(conn, deleteOrder);
            runner.update(conn, deletePayment);
            runner.update(conn, deleteCreditRequest);
        } catch (SQLException a) {
            a.printStackTrace();
        }
    }

    public static String getOrderCount() {
        Long count = null;
        val codeSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url,user, password)) {
            count = runner.query(conn, codeSQL, new ScalarHandler<>());
        } catch (SQLException a) {
            a.printStackTrace();
        }
        return Long.toString(count);
    }

    public static String getPaymentStatus() {
        val codeSQL = "SELECT status FROM payment_entity;";
        return getData(codeSQL);
    }

    public static String getCreditRequestStatus() {
        val codeSQL = "SELECT status FROM credit_request_entity;";
        return getData(codeSQL);
    }

    public static String getData(String query) {
        val runner = new QueryRunner();
        String data = null;
        try (
                val conn = DriverManager.getConnection(url, user, password
                );
        ) {
            data = runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException a) {
            a.printStackTrace();
        }
        return data;
    }
}
