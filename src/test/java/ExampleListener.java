import net.echo.spigotengine.listener.impl.BetterListener;

public class ExampleListener extends BetterListener<ExamplePlugin> {

    public ExampleListener(ExamplePlugin plugin) {
        super(plugin);
    }

    // Your events

    @Override
    public void reload() {
        // Here you can reload stuff for the listener
        // This has to be manually called from the plugin
    }
}
