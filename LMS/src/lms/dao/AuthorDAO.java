package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Author;

public class AuthorDAO extends BaseDAO<Author> {
   
    @Override
    protected List<Author> extractData(final ResultSet resultSet) throws SQLException {
        final List<Author> authors = new ArrayList<Author>();
        while(resultSet.next()) {
            final Author author = new Author();
            author.setId(resultSet.getInt("authorId"));
            author.setName(resultSet.getString("authorName"));
            authors.add(author);
        }
        return authors;
    }

    public AuthorDAO(final Connection connection) {
        super(connection, "tbl_author");
    }

    public void insert(final Author author) throws SQLException {
        author.setId(nextId("authorId"));
        final String query = "insert into tbl_author (authorId, authorName) values (?, ?)";
        final Object[] values = { author.getId(), author.getName() };
        save(query, values);
    }

    public void update(final Author author) throws SQLException {
        final String query = "update tbl_author set authorName = ? where authorId = ?";
        final Object[] values = { author.getName(), author.getId() };
        save(query, values);
    }

    public void delete(final Author author) throws SQLException {
        final String query = "delete from tbl_author where authorId = ?";
        final Object[] values = { author.getId() };
        save(query, values);
    }

    public Author selectById(final Integer id) throws SQLException {
        final String query = "select * from tbl_author where authorId = ?";
        final Object[] values = { id };
        final List<Author> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }
}
