package work.microhand.steveanim.client.renderer.entity;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import work.microhand.steveanim.client.model.entity.FakePlayerEntityModel;
import work.microhand.steveanim.common.entity.FakePlayerEntity;

/**
 * @author SanseYooyea
 */
public class FakePlayerEntityRenderer extends GeoEntityRenderer<FakePlayerEntity> {
    public FakePlayerEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new FakePlayerEntityModel());
    }

    @Override
    public void preRender(MatrixStack poseStack, FakePlayerEntity animatable, BakedGeoModel model, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        animatable.age = animatable.getReplacedPlayer().age;
        animatable.prevBodyYaw = animatable.getReplacedPlayer().prevBodyYaw;
        animatable.prevHeadYaw = animatable.getReplacedPlayer().prevHeadYaw;
        animatable.bodyYaw = animatable.getReplacedPlayer().bodyYaw;
        animatable.headYaw = animatable.getReplacedPlayer().headYaw;
    }
}
