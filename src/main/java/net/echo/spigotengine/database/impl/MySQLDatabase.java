package net.echo.spigotengine.database.impl;

import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.database.Database;
import net.echo.spigotengine.utils.functions.StatementConsumer;

import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * An abstract base for MySQL databases.
 */
public abstract class MySQLDatabase<P extends SpigotPlugin<?>> implements Database {

    protected Executor executor;
    protected DatabaseCredentials credentials;
    protected P plugin;

    public MySQLDatabase(P plugin) {
        this.executor = getExecutor();
        this.credentials = getCredentials();
        this.plugin = plugin;
    }

    public abstract Executor getExecutor();

    public abstract DatabaseCredentials getCredentials();

    public String getUrl() {
        return "jdbc:mysql://" + credentials.host() + ":" + credentials.port() + "/" + credentials.database();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), credentials.user(), credentials.password());
    }

    public <T> T execute(String query, StatementConsumer<T> function) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                return function.accept(statement);
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return -1;
    }

    public CompletableFuture<Integer> executeUpdateAsync(String query, Object... params) {
        return CompletableFuture.supplyAsync(() -> executeUpdate(query, params), getExecutor());
    }

    private void setParameters(PreparedStatement statement, Object[] params) throws Exception {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    public record DatabaseCredentials(String host, String port, String database, String user, String password) {
    }
}
