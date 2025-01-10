package work.microhand.steveanim.common.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

/**
 * @author SanseYooyea
 */
public class Networking {
    public static void register() {
        // Register your custom packet here
        PayloadTypeRegistry.playS2C().register(AnimationSyncPayload.ID, AnimationSyncPayload.CODEC);
    }
}
