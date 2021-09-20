package lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.domain.LibraryBranch;

public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
    
    @Override
    protected List<LibraryBranch> extractData(final ResultSet resultSet) throws SQLException {
        final List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
        while(resultSet.next()) {
            final LibraryBranch branch = new LibraryBranch();
            branch.setId(resultSet.getInt("branchId"));
            branch.setName(resultSet.getString("branchName"));
            branch.setAddress(resultSet.getString("branchAddress"));
            branches.add(branch);
        }
        return branches;
    }

    public LibraryBranchDAO(final Connection connection) {
        super(connection, "tbl_library_branch");
    }

    public void insert(final LibraryBranch branch) throws SQLException {
        final String query = "insert into tbl_library_branch (branchName, branchAddress) values (?, ?)";
        final Object[] values = { branch.getName(), branch.getAddress() };
        save(query, values);
    }

    public void update(final LibraryBranch branch) throws SQLException {
        final String query = "update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?";
        final Object[] values = { branch.getName(), branch.getAddress(), branch.getId() };
        save(query, values);
    }

    public void delete(final LibraryBranch branch) throws SQLException {
        final String query = "delete from tbl_library_branch where branchId = ?";
        final Object[] values = { branch.getId() };
        save(query, values);
    }

    public LibraryBranch selectById(final Integer id) throws SQLException {
        final String query = "select * from tbl_library_branch where branchId = ?";
        final Object[] values = { id };
        final List<LibraryBranch> result = read(query, values);
        return result.isEmpty() ? null : result.get(0);
    }
}
