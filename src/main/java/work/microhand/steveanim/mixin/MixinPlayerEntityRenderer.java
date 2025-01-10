package work.microhand.steveanim.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import work.microhand.steveanim.client.renderer.AnimationRender;
import work.microhand.steveanim.common.entity.FakePlayerEntity;
import work.microhand.steveanim.common.entity.MaceEntity;
import work.microhand.steveanim.common.entity.TNTEntity;
import work.microhand.steveanim.common.manager.FakePlayerEntityInstance;
import work.microhand.steveanim.common.manager.PlayerAnimStateManager;
import work.microhand.steveanim.common.model.PlayerAnimationState;
import work.microhand.steveanim.common.task.PlayerPlayAnimTask;
import work.microhand.steveanim.common.task.TaskManager;

import java.util.concurrent.TimeUnit;

/**
 * @author SanseYooyea
 */
@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {
    @Inject(
            method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    public void render(
            AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci
    ) {
        int playerId = abstractClientPlayerEntity.getId();
        if (PlayerAnimStateManager.INSTANCE.hasAnim(playerId)) {
            // TODO 取消该实体渲染，渲染新的实体，直至动画播放结束
            FakePlayerEntity fakePlayerEntity = FakePlayerEntityInstance.INSTANCE.getOrCreateFakePlayer(abstractClientPlayerEntity);
            TNTEntity tntEntity = FakePlayerEntityInstance.INSTANCE.getOrCreateTNTEntity(abstractClientPlayerEntity);
            MaceEntity maceEntity = FakePlayerEntityInstance.INSTANCE.getOrCreateMaceEntity(abstractClientPlayerEntity);
            if (fakePlayerEntity.getAnimationState() == PlayerAnimationState.IDLE) {
                System.out.println("设置动画状态");
                fakePlayerEntity.setAnimationState(PlayerAnimStateManager.INSTANCE.getPlayerAnimationState(playerId));
                TaskManager.INSTANCE.runTaskLater(new PlayerPlayAnimTask(fakePlayerEntity), (long) (fakePlayerEntity.getAnimationState().getAnimationTime() * 1000), TimeUnit.MILLISECONDS);

                System.out.println("成功Render新的FakePlayerEntity材质: " + fakePlayerEntity.getReplacedPlayerTexture());
                System.out.println("成功Render新的FakePlayerEntity动画状态: " + fakePlayerEntity.getAnimationState());
            } else {
                AnimationRender.fakePlayerEntityRenderer.render(fakePlayerEntity, abstractClientPlayerEntity.headYaw,
                        MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(!abstractClientPlayerEntity.clientWorld.getTickManager().shouldSkipTick(fakePlayerEntity)), matrixStack, vertexConsumerProvider, i);
                if (fakePlayerEntity.getAnimationState() == PlayerAnimationState.DAMAGED_BY_TNT) {
                    tntEntity.age = abstractClientPlayerEntity.age;
                    tntEntity.prevBodyYaw = abstractClientPlayerEntity.prevBodyYaw;
                    tntEntity.bodyYaw = abstractClientPlayerEntity.bodyYaw;
                    tntEntity.prevHeadYaw = abstractClientPlayerEntity.prevHeadYaw;
                    tntEntity.headYaw = abstractClientPlayerEntity.headYaw;
                    AnimationRender.tntEntityRenderer.render(tntEntity, abstractClientPlayerEntity.headYaw,
                            MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(!abstractClientPlayerEntity.clientWorld.getTickManager().shouldSkipTick(tntEntity)), matrixStack, vertexConsumerProvider, i);
                } else if (fakePlayerEntity.getAnimationState() == PlayerAnimationState.DAMAGED_BY_MACE) {
                    maceEntity.age = abstractClientPlayerEntity.age;
                    maceEntity.prevBodyYaw = abstractClientPlayerEntity.prevBodyYaw;
                    maceEntity.bodyYaw = abstractClientPlayerEntity.bodyYaw;
                    maceEntity.prevHeadYaw = abstractClientPlayerEntity.prevHeadYaw;
                    maceEntity.headYaw = abstractClientPlayerEntity.headYaw;
                    AnimationRender.maceEntityRenderer.render(maceEntity, abstractClientPlayerEntity.headYaw,
                            MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(!abstractClientPlayerEntity.clientWorld.getTickManager().shouldSkipTick(maceEntity)), matrixStack, vertexConsumerProvider, i);
                }
                System.out.println("动画播放中: " + fakePlayerEntity.getAnimationState());
            }
            ci.cancel();
        }
    }
}
