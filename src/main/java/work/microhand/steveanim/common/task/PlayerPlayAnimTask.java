package work.microhand.steveanim.common.task;

import work.microhand.steveanim.common.entity.FakePlayerEntity;
import work.microhand.steveanim.common.manager.FakePlayerEntityInstance;
import work.microhand.steveanim.common.manager.PlayerAnimStateManager;
import work.microhand.steveanim.common.model.PlayerAnimationState;

/**
 * @author SanseYooyea
 */
public class PlayerPlayAnimTask implements Runnable {
    private final FakePlayerEntity fakePlayerEntity;

    public PlayerPlayAnimTask(FakePlayerEntity fakePlayerEntity) {
        this.fakePlayerEntity = fakePlayerEntity;
    }

    @Override
    public void run() {
        fakePlayerEntity.setAnimationState(PlayerAnimationState.IDLE);
        FakePlayerEntityInstance.INSTANCE.remove(fakePlayerEntity.getReplacedPlayer().getId());
        PlayerAnimStateManager.INSTANCE.completeAnim(fakePlayerEntity.getReplacedPlayer().getId());
    }
}
