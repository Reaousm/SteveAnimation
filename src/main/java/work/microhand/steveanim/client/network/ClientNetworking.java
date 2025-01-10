package work.microhand.steveanim.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import work.microhand.steveanim.client.renderer.AnimationRender;
import work.microhand.steveanim.common.network.AnimationSyncPayload;

/**
 * @author SanseYooyea
 */
public class ClientNetworking {
    public static void registerGlobalReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(AnimationSyncPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                System.out.println("接收到payload: " + payload);
                System.out.println("playerId: " + payload.playerId());
                System.out.println("state: " + payload.state());

                AnimationRender.renderPlayerAnimation(context.client(), payload);
            });
        });
    }
}
