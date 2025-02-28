import net.echo.spigotengine.boot.PluginLoader;
import net.echo.spigotengine.boot.SpigotPlugin;
import net.echo.spigotengine.data.loader.DataLoader;
import net.echo.spigotengine.database.Database;

public class ExamplePlugin extends SpigotPlugin<ExampleData> {

    private final DataLoader<ExampleData> dataLoader = new ExampleDataLoader();

    public ExamplePlugin(PluginLoader<?> pluginLoader) {
        super(pluginLoader);
    }

    @Override
    public Database getDatabase() {
        return null;
    }

    @Override
    public DataLoader<ExampleData> getDataLoader() {
        return dataLoader;
    }

    @Override
    public void registerListeners() {
        listenerHandler.registerAll("net.echo.spigotengine.test");
    }

    @Override
    public void registerCommands() {
        commandHandler.registerAll("net.echo.spigotengine.test");
    }

    @Override
    public void registerTasks() {
        taskHandler.registerAll("net.echo.spigotengine.test");
    }
}
