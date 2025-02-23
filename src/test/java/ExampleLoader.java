import net.echo.spigotengine.boot.PluginLoader;

/**
 * This is the loader class. Your plugin.yml main-class attribute should point here.
 */
public class ExampleLoader extends PluginLoader<ExamplePlugin> {

    @Override
    public ExamplePlugin getPlugin() {
        return new ExamplePlugin(this);
    }
}
