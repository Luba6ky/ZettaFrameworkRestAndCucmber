package api.utils.database.models;

import api.utils.database.DBConnection;
import api.utils.logging.Log;
import lombok.Data;
import org.junit.jupiter.api.Assertions;

import java.sql.ResultSet;

@Data
public class ExampleModel extends DBConnection {

    private int id;
    private String name;
    private String email;
    private String phone;

    public void verifyUser(int userId, String expectedName, String expectedEmail, String expectedPhone) throws Exception {

        ResultSet resultSet = executeQuery(
                "SELECT * FROM users WHERE id = " + userId);

        while (resultSet.next()) {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.email = resultSet.getString("email");
            this.phone = resultSet.getString("phone");


            Assertions.assertEquals(this.id, userId, "User ID does not match");
            Assertions.assertEquals(this.name, expectedName, "Name does not match");
            Assertions.assertEquals(this.email, expectedEmail, "Email does not match");
            Assertions.assertEquals(this.phone, expectedPhone, "Phone does not match");

            Log.info("User verified with ID: " + userId);
        }
    }
}
