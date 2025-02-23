import net.echo.spigotengine.commands.BaseCommand;
import net.echo.spigotengine.commands.SenderFilter;
import net.echo.spigotengine.commands.annotations.CommandData;
import net.echo.spigotengine.commands.annotations.Default;
import net.echo.spigotengine.commands.annotations.SubCommand;
import org.bukkit.entity.Player;

@CommandData(aliases = {"example", "test"}, permission = "example.use", filter = SenderFilter.PLAYER_ONLY)
public class ExampleCommand extends BaseCommand<ExamplePlugin> {

    public ExampleCommand(ExamplePlugin plugin) {
        super(plugin);
    }

    @Default
    public void onHelp(Player sender) {
        // Code
    }

    @SubCommand(aliases = "hello", permission = "example.hello")
    public void onHello(Player sender) {
        // Code
    }
}
