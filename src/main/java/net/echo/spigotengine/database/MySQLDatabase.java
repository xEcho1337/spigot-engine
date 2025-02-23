package net.echo.spigotengine.database;

import net.echo.spigotengine.database.functions.StatementConsumer;

import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * An abstract base for MySQL databases.
 */
public abstract class MySQLDatabase {

    public abstract Executor getExecutor();

    public abstract String getHost();

    public abstract int getPort();

    public abstract String getDatabase();

    public abstract String getUser();

    public abstract String getPassword();

    public String getUrl() {
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }

    public <T> T execute(String query, StatementConsumer<T> function) {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.prepareStatement(query)) {
                return function.accept(statement);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public <T> CompletableFuture<T> executeAsync(String query, StatementConsumer<T> function) {
        return CompletableFuture.supplyAsync(() -> execute(query, function), getExecutor());
    }

    public ResultSet executeQuery(String query, Object... params) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                setParameters(statement, params);

                return statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return null;
    }

    public CompletableFuture<ResultSet> executeQueryAsync(String query, Object... params) {
        return CompletableFuture.supplyAsync(() -> executeQuery(query, params), getExecutor());
    }

    public int executeUpdate(String query, Object... params) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                setParameters(statement, params);

                return statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return -1;
    }

    public CompletableFuture<Integer> executeUpdateAsync(String query, Object... params) {
        return CompletableFuture.supplyAsync(() -> executeUpdate(query, params), getExecutor());
    }

    private void setParameters(PreparedStatement statement, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
}
