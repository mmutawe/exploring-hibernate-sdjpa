package com.mmutawe.explore.hibernate.sdjpa.jdbc.dao;

import com.mmutawe.explore.hibernate.sdjpa.jdbc.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource dataSource;

    @Autowired
    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getAuthorById(Long id) {
        Connection connection = null;
//        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM author WHERE id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM author WHERE first_name = ? AND last_name = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }

        return null;
    }

    @Override
    public Author saveOneAuthor(Author author) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection
                    .prepareStatement("INSERT INTO author(first_name,last_name) VALUES (?,?)");

            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");

            if (resultSet.next()) {
                Long savedAuthorId = resultSet.getLong(1);
                return this.getAuthorById(savedAuthorId);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection
                    .prepareStatement("UPDATE author SET first_name=?, last_name=? WHERE id=?");

            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setLong(3, author.getId());
            preparedStatement.execute();

            Long updatedAuthorId = author.getId();
            return this.getAuthorById(updatedAuthorId);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, null);
        }
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection
                    .prepareStatement("DELETE FROM author WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(connection, preparedStatement, resultSet);
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) {

        Author resultAuthor = new Author();
        try {
            resultAuthor.setId(resultSet.getLong("id"));
            resultAuthor.setFirstName(resultSet.getString("first_name"));
            resultAuthor.setLastName(resultSet.getString("last_name"));

            return resultAuthor;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void closeAll(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }

//            if (statement != null) {
//                statement.close();
//            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
