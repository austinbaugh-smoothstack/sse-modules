package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {
    
    @Override
    protected List<Borrower> extractData(final ResultSet resultSet) throws SQLException {
        final List<Borrower> borrowers = new ArrayList<Borrower>();
        while(resultSet.next()) {
            final Borrower borrower = new Borrower();
            borrower.setCardNumber(resultSet.getInt("cardNo"));
            borrower.setName(resultSet.getString("name"));
            borrower.setAddress(resultSet.getString("address"));
            borrower.setPhone(resultSet.getString("phone"));
            borrowers.add(borrower);
        }
        return borrowers;
    }

    public BorrowerDAO(final Connection connection) {
        super(connection, "tbl_borrower");
    }

    public void insert(final Borrower borrower) throws SQLException {
        borrower.setCardNumber(selectCount() + 1);
        final String query = "insert into tbl_borrower (cardNo, name, address, phone) values (?, ?, ?, ?)";
        final Object[] values = { borrower.getCardNumber(), borrower.getName(), borrower.getAddress(), borrower.getPhone() };
        save(query, values);
    }

    public void update(final Borrower borrower) throws SQLException {
        final String query = "update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?";
        final Object[] values = { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNumber() };
        save(query, values);
    }

    public void delete(final Borrower borrower) throws SQLException {
        final String query = "delete from tbl_borrower where cardNo = ?";
        final Object[] values = { borrower.getCardNumber() };
        save(query, values);
    }

    public Borrower selectByCardNumber(final Integer cardNumber) throws SQLException {
        final String query = "select * from tbl_borrower where cardNo = ?";
        final Object[] values = { cardNumber };
        final List<Borrower> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }
}
