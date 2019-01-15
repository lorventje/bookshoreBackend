package Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WritingFileToDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://studmysql01.fhict.local/dbi302522";
        String user = "dbi302522";
        String password = "dbi302522";

        String filePath = "C:/Users/remmi/OneDrive/Bureaublad/Semester_4/Fun4_Individual_part/E-books/Vechten_met_soldaten.docx";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO attachment (data , name , type ) values (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(2, "Vechten met soldaten");
            statement.setString(3, "docx");
            InputStream inputStream = new FileInputStream(new File(filePath));

            statement.setBlob(1, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Successful added file");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
