import net.echo.spigotengine.data.UserData;

import java.util.UUID;

public class ExampleData extends UserData {

    private int kills;
    private int deaths;
    private int streak;

    public ExampleData(UUID uuid, String name) {
        super(uuid, name);
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
