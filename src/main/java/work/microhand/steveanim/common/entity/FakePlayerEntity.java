package work.microhand.steveanim.common.entity;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;
import work.microhand.steveanim.common.model.PlayerAnimationState;

/**
 * @author SanseYooyea
 */
public class FakePlayerEntity extends MobEntity implements GeoEntity {

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private AbstractClientPlayerEntity replacedPlayer;
    private PlayerAnimationState animationState = PlayerAnimationState.IDLE;

    public FakePlayerEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public AbstractClientPlayerEntity getReplacedPlayer() {
        return replacedPlayer;
    }

    public void setReplacedPlayer(AbstractClientPlayerEntity replacedPlayer) {
        this.replacedPlayer = replacedPlayer;
        System.out.println(replacedPlayer.getSkinTextures());
    }

    public PlayerAnimationState getAnimationState() {
        return animationState;
    }

    public void setAnimationState(PlayerAnimationState animationState) {
        this.animationState = animationState;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar registrar) {
        registrar.add(new AnimationController<>(this, "fake_player", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<FakePlayerEntity> fakePlayerEntityAnimationState) {
        System.out.println("播放动画");
        System.out.println("动画状态: " + fakePlayerEntityAnimationState.getController().getAnimationState());
        System.out.println("动画: " + getAnimationState());

        if (animationState == PlayerAnimationState.IDLE) {
            fakePlayerEntityAnimationState.getController().setAnimation(DefaultAnimations.IDLE);
        } else if (animationState == PlayerAnimationState.DAMAGED_BY_TNT) {
            System.out.println("播放TNT动画");
            fakePlayerEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("tnt", Animation.LoopType.PLAY_ONCE));
        } else if (animationState == PlayerAnimationState.DAMAGED_BY_MACE) {
            System.out.println("播放锤子动画");
            fakePlayerEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("chuizi", Animation.LoopType.PLAY_ONCE));
        }

        return PlayState.CONTINUE;
    }

    public Identifier getReplacedPlayerTexture() {
        if (replacedPlayer == null) {
            return null;
        }

        return replacedPlayer.getSkinTextures().texture();
    }
}
