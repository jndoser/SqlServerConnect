import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Connect {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:49715;" +
                "instance=DESKTOP-9PVGMI4\\DISISLONGG;databaseName=Lab4DB";
        String username = "sa";
        String pw = "1100101010";

        Connection con = DriverManager.getConnection(url, username, pw);

        return con;
    }

    public static ResultSet getCars() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();

        Statement stm = con.createStatement();
        String query = "Select * from Cars";
        ResultSet cars = stm.executeQuery(query);

        return cars;
    }

    public static class Car {
        private int carId;
        private String carName;
        private double price;
        private String picture;
        private boolean type;

        public Car(int carId, String carName, double price, String picture, boolean type) {
            this.carId = carId;
            this.carName = carName;
            this.price = price;
            this.picture = picture;
            this.type = type;
        }

        @Override
        public String toString() {
            return carId + " " + carName + " "
                    + price + " " + picture + " "
                    + type;
        }
    }

    public static Car getCar(ResultSet car) throws SQLException {
        int carId = car.getInt(1);
        String carName = car.getString(2);
        double price = car.getDouble(3);
        String picture = car.getString(4);
        boolean type = car.getBoolean(5);

        return new Car(carId, carName, price, picture, type);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ResultSet cars = getCars();
        while (cars.next())
        {
            Car car = getCar(cars);
            System.out.println(car);
        }
    }
}
