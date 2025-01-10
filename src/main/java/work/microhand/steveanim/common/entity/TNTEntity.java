package work.microhand.steveanim.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

/**
 * @author SanseYooyea
 */
public class TNTEntity extends MobEntity implements GeoEntity {
    // 动画持续时间（以刻为单位）
    private static final int ANIMATION_DURATION = 50;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    // 已存在的刻数
    private int ticksExisted = 0;

    public TNTEntity(EntityType<? extends TNTEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
        this.setInvulnerable(true);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<TNTEntity> tntEntityAnimationState) {
        tntEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("tnt", Animation.LoopType.PLAY_ONCE));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            return;
        }

        ticksExisted++;
        if (ticksExisted >= ANIMATION_DURATION) {
            this.discard(); // 移除实体
        }
    }
}
