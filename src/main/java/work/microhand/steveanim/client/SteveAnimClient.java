package work.microhand.steveanim.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.microhand.steveanim.client.network.ClientNetworking;
import work.microhand.steveanim.client.renderer.AnimationRender;
import work.microhand.steveanim.client.renderer.entity.FakePlayerEntityRenderer;
import work.microhand.steveanim.client.renderer.entity.MaceEntityRenderer;
import work.microhand.steveanim.client.renderer.entity.TNTEntityRenderer;
import work.microhand.steveanim.common.entity.EntityManager;
import work.microhand.steveanim.common.entity.FakePlayerEntity;
import work.microhand.steveanim.common.entity.MaceEntity;
import work.microhand.steveanim.common.entity.TNTEntity;

/**
 * @author SanseYooyea
 */
public class SteveAnimClient implements ClientModInitializer {

    private static final Logger log = LoggerFactory.getLogger(SteveAnimClient.class);

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EntityManager.TNT, (context) -> {
            AnimationRender.tntEntityRenderer = new TNTEntityRenderer(context);
            return AnimationRender.tntEntityRenderer;
        });
        log.info("Register TNTEntityRenderer");
        FabricDefaultAttributeRegistry.register(EntityManager.TNT, TNTEntity.createMobAttributes());
        log.info("Register TNTEntity");

        EntityRendererRegistry.register(EntityManager.MACE, (context) -> {
            AnimationRender.maceEntityRenderer = new MaceEntityRenderer(context);
            return AnimationRender.maceEntityRenderer;
        });
        log.info("Register MaceEntityRenderer");
        FabricDefaultAttributeRegistry.register(EntityManager.MACE, MaceEntity.createMobAttributes());
        log.info("Register MaceEntity");

        EntityRendererRegistry.register(EntityManager.FAKE_PLAYER, (context) -> {
            AnimationRender.fakePlayerEntityRenderer = new FakePlayerEntityRenderer(context);
            return AnimationRender.fakePlayerEntityRenderer;
        });
        log.info("Register FakePlayerEntityRenderer");
        FabricDefaultAttributeRegistry.register(EntityManager.FAKE_PLAYER, FakePlayerEntity.createMobAttributes());

        ClientNetworking.registerGlobalReceiver();
    }
}
