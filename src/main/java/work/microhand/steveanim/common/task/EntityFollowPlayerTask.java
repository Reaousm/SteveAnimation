package work.microhand.steveanim.common.task;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @author SanseYooyea
 */
public class EntityFollowPlayerTask implements Runnable {
    private final Entity entity;
    private final PlayerEntity followPlayer;
    private final int followTime;

    private int followTimeCount = 0;

    public EntityFollowPlayerTask(Entity entity, PlayerEntity followPlayer, int followTime) {
        this.entity = entity;
        this.followPlayer = followPlayer;
        this.followTime = followTime;
    }

    @Override
    public void run() {
        entity.setPos(followPlayer.getX(), followPlayer.getY(), followPlayer.getZ());

        followTimeCount++;
        if (followTimeCount >= followTime) {
            TaskManager.INSTANCE.cancel(this);
        }
    }
}
