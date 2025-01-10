package work.microhand.steveanim;

import net.fabricmc.api.ModInitializer;
import work.microhand.steveanim.common.handler.PlayerDamagedHandler;
import work.microhand.steveanim.common.network.Networking;

/**
 * @author SanseYooyea
 */
public class SteveAnim implements ModInitializer {
    public static final String MOD_ID = "steveanim";

    @Override
    public void onInitialize() {
        PlayerDamagedHandler.register();
        Networking.register();
    }
}
