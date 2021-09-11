import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    
    public static void main(final String[] _args) {
        Connection conn = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            // Problem #1
            final String query = "SELECT noOfCopies " +
                "FROM tbl_book_copies " +
                "INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId " +
                "INNER JOIN tbl_library_branch ON " + 
                "tbl_library_branch.branchId = tbl_book_copies.branchId " +
                "WHERE title=? AND branchName=?";
            prepareStatement = conn.prepareStatement(query);
            final String bookTitle = "The Lost Tribe";
            final String branchName = "Sharpstown";
            prepareStatement.setString(1, bookTitle);
            prepareStatement.setString(2, branchName);
            resultSet = prepareStatement.executeQuery();
            final int numCopies = resultSet.next() ? Integer.parseInt(resultSet.getString("noOfCopies")) : 0;
            System.out.println(numCopies + " copies of " + bookTitle + " at " + branchName + " library branch.");
            
            
        } catch(final SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(prepareStatement != null) {
                    prepareStatement.close();

                }
                if(conn != null) {
                    conn.close();
                }
            } catch(final SQLException e) {
                e.printStackTrace();
            }
        }
    }
}