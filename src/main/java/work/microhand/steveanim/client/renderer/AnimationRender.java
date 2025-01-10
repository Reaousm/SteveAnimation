package work.microhand.steveanim.client.renderer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import work.microhand.steveanim.client.renderer.entity.FakePlayerEntityRenderer;
import work.microhand.steveanim.client.renderer.entity.MaceEntityRenderer;
import work.microhand.steveanim.client.renderer.entity.TNTEntityRenderer;
import work.microhand.steveanim.common.manager.PlayerAnimStateManager;
import work.microhand.steveanim.common.model.PlayerAnimationState;
import work.microhand.steveanim.common.network.AnimationSyncPayload;

/**
 * @author SanseYooyea
 */
public class AnimationRender {
    public static FakePlayerEntityRenderer fakePlayerEntityRenderer;
    public static MaceEntityRenderer maceEntityRenderer;
    public static TNTEntityRenderer tntEntityRenderer;

    public static void renderPlayerAnimation(MinecraftClient client, AnimationSyncPayload payload) {
        if (client.world == null) {
            System.out.println("world is null");
            return;
        }

        System.out.println("通知渲染动画: " + payload);
        Entity animPlayer = client.world.getEntityById(payload.playerId());
        if (animPlayer == null) {
            System.out.println("animPlayer is null");
            return;
        }

        PlayerAnimStateManager.INSTANCE.setPlayerAnimationState(payload.playerId(), PlayerAnimationState.fromString(payload.state()));
    }
}
