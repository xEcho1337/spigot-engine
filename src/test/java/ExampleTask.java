import net.echo.spigotengine.tasks.Task;

@Task(async = true, repeating = true, delay = 10, period = 20)
public class ExampleTask implements Runnable {

    @Override
    public void run() {
        // Code
    }
}
