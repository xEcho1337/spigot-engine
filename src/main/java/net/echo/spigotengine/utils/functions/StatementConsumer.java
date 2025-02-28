package net.echo.spigotengine.utils.functions;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * A functional interface used to wrap the result of a statement execution.
 * @param <T> the result type
 */
public interface StatementConsumer<T> {

    T accept(Statement statement) throws SQLException;
}
