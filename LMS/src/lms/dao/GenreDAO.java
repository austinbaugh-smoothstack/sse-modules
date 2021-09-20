package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.Genre;

public class GenreDAO extends BaseDAO<Genre> {
    
    @Override
    protected List<Genre> extractData(final ResultSet resultSet) throws SQLException {
        final List<Genre> genres = new ArrayList<Genre>();
        while(resultSet.next()) {
            final Genre genre = new Genre();
            genre.setId(resultSet.getInt("genre_id"));
            genre.setName(resultSet.getString("genre_name"));
            genres.add(genre);
        }
        return genres;
    }

    public GenreDAO(final Connection connection) {
        super(connection, "tbl_genre");
    }

    public void insert(final Genre genre) throws SQLException {
        final String query = "insert into tbl_genre (genre_name) values (?)";
        final Object[] values = { genre.getName() };
        save(query, values);
    }

    public void update(final Genre genre) throws SQLException {
        final String query = "update tbl_genre set genre_name = ? where genre_id = ?";
        final Object[] values = { genre.getName(), genre.getId() };
        save(query, values);
    }

    public void delete(final Genre genre) throws SQLException {
        final String query = "delete from tbl_genre where genre_id = ?";
        final Object[] values = { genre.getId() };
        save(query, values);
    }

    public Genre selectById(final Integer id) throws SQLException {
        final String query = "select * from tbl_genre where genre_id = ?";
        final Object[] values = { id };
        final List<Genre> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Genre> selectAllByBookId(final Integer bookId) throws SQLException {
        final String query = "select * from tbl_genre inner join tbl_book_genres on tbl_genre.genre_id = tbl_book_genres.genre_id where bookId = ?";
        final Object[] values = { bookId };
        return read(query, values);
    }
}
