import net.echo.spigotengine.data.loader.DataLoader;

import java.util.Optional;
import java.util.UUID;

public class ExampleDataLoader implements DataLoader<ExampleData> {

    @Override
    public Optional<ExampleData> load(UUID uuid, String name) {
        return Optional.empty();
    }
}
