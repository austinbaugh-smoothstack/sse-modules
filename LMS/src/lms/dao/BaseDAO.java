package lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    
    private Connection connection;
    private String table;
    
    protected Connection getConnection() {
        return connection;
    }
    
    protected void save(final String query, final Object[] values) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(query);
        int count = 1;
        for(Object value : values) {
            statement.setObject(count, value);
            count++;
        }
        statement.executeUpdate();
    }
    
    protected List<T> read(final String query, final Object[] values) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(query);
        int count = 1;
        for(Object value : values) {
            statement.setObject(count, value);
            count++;
        }
        final ResultSet resultSet = statement.executeQuery();
        return extractData(resultSet);
    }
    
    protected abstract List<T> extractData(final ResultSet resultSet) throws SQLException;
    
    public BaseDAO(final Connection connection, final String table) {
        this.connection = connection;
        this.table = table;
    }

    public List<T> selectAll() throws SQLException {
        return read("select * from " + table, new Object[] {});
    }
}
