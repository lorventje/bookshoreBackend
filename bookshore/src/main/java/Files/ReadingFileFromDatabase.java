package Files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadingFileFromDatabase {
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
        String url = "jdbc:mysql://studmysql01.fhict.local/dbi302522";
        String user = "dbi302522";
        String password = "dbi302522";

        String filePath = "C:/Users/remmi/OneDrive/Bureaublad/kees.docx";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT data FROM attachment WHERE name =? AND type =?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "Het monster van logness");
            statement.setString(2, "docx");

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Blob blob = result.getBlob("data");
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(filePath);

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outputStream.close();
                System.out.println("File saved");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
