package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {
    
    @Override
    protected List<Publisher> extractData(final ResultSet resultSet) throws SQLException {
        final List<Publisher> publishers = new ArrayList<Publisher>();
        while(resultSet.next()) {
            final Publisher publisher = new Publisher();
            publisher.setId(resultSet.getInt("publisherId"));
            publisher.setName(resultSet.getString("publisherName"));
            publisher.setAddress(resultSet.getString("publisherAddress"));
            publisher.setPhone(resultSet.getString("publisherPhone"));
            publishers.add(publisher);
        }
        return publishers;
    }

    public PublisherDAO(final Connection connection) {
        super(connection, "tbl_publisher");
    }

    public void insert(final Publisher publisher) throws SQLException {
        publisher.setId(selectCount() + 1);
        final String query = "insert into tbl_publisher (publisherId, publisherName, publisherAddress, publisherPhone) values (?, ?, ?, ?)";
        final Object[] values = { publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getPhone() };
        save(query, values);
    }

    public void update(final Publisher publisher) throws SQLException {
        final String query = "update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?";
        final Object[] values = { publisher.getName(), publisher.getAddress(), publisher.getPhone(), publisher.getId() };
        save(query, values);
    }

    public void delete(final Publisher publisher) throws SQLException {
        final String query = "delete from tbl_publisher where publisherId = ?";
        final Object[] values = { publisher.getId() };
        save(query, values);
    }

    public Publisher selectById(final Integer id) throws SQLException {
        final String query = "select * from tbl_publisher where publisherId = ?";
        final Object[] values = { id };
        final List<Publisher> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }
}
