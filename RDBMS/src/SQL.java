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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn = (Connection) DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            
            final String bookTitle = "The Lost Tribe";
            final String branchName = "Sharpstown";
            final String dueDate = "2018-04-08 17:39:24";
            
            System.out.println("Problem 1:");
            String query = "SELECT noOfCopies "
                + "FROM tbl_book_copies "
                + "INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId "
                + "INNER JOIN tbl_library_branch ON tbl_library_branch.branchId = tbl_book_copies.branchId "
                + "WHERE title=? AND branchName=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, branchName);
            resultSet = preparedStatement.executeQuery();
            final String numCopies = resultSet.next() ? resultSet.getString("noOfCopies") : "no";
            System.out.println(numCopies + " copies of " + bookTitle + " at " + branchName + " library branch.");
            System.out.println();
            
            System.out.println("Problem 2:");
            query = "SELECT branchName, noOfCopies "
                + "FROM tbl_book_copies "
                + "INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_copies.bookId "
                + "INNER JOIN tbl_library_branch ON  tbl_library_branch.branchId = tbl_book_copies.branchId "
                + "WHERE title=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, bookTitle);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString("noOfCopies") + " copies of " + bookTitle + " at " + resultSet.getString("branchName")  + " library branch.");
            }
            System.out.println();
            
            System.out.println("Problem 3:");
            query = "SELECT name "
                + "FROM tbl_borrower "
                + "LEFT JOIN tbl_book_loans ON tbl_borrower.cardNo = tbl_book_loans.cardNo "
                + "WHERE tbl_book_loans.cardNo IS NULL";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Names of people who have no books checked out:");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
            System.out.println();
            
            System.out.println("Problem 4:");
            query = "SELECT title, name, address "
                + "FROM tbl_book_loans "
                + "INNER JOIN tbl_book ON tbl_book.bookId = tbl_book_loans.bookId "
                + "INNER JOIN tbl_borrower ON tbl_borrower.cardNo = tbl_book_loans.cardNo "
                + "INNER JOIN tbl_library_branch ON  tbl_library_branch.branchId = tbl_book_loans.branchId "
                + "WHERE branchName=? AND dueDate=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, branchName);
            preparedStatement.setString(2, dueDate);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Books due " + dueDate + " at " + branchName + " library branch with their corresponding borrower's name and address:");
            while(resultSet.next()) {
                System.out.print(resultSet.getString("title") + ": ");
                System.out.print(resultSet.getString("name") + " - ");
                System.out.print(resultSet.getString("address"));
                System.out.println();
            }
            System.out.println();
            
            System.out.println("Problem 5:");
            query = "SELECT branchName, COUNT(*) AS count "
                + "FROM tbl_book_loans "
                + "INNER JOIN tbl_library_branch ON  tbl_library_branch.branchId = tbl_book_loans.branchId "
                + "GROUP BY branchName";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Number of books checked out at each library branch:");
            while(resultSet.next()) {
                System.out.print(resultSet.getString("branchName") + ": ");
                System.out.print(resultSet.getString("count"));
                System.out.println();
            }
            System.out.println();
            
        } catch(final SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(preparedStatement != null) {
                    preparedStatement.close();

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