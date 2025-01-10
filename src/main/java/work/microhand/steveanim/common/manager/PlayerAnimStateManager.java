package work.microhand.steveanim.common.manager;

import work.microhand.steveanim.common.model.PlayerAnimationState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SanseYooyea
 */
public enum PlayerAnimStateManager {
    INSTANCE;

    private final Map<Integer, PlayerAnimationState> PLAY_STATES = new ConcurrentHashMap<>();

    public void setPlayerAnimationState(int playerId, PlayerAnimationState state) {
        PLAY_STATES.put(playerId, state);
    }

    public PlayerAnimationState getPlayerAnimationState(int playerId) {
        return PLAY_STATES.get(playerId);
    }

    public void completeAnim(int playerId) {
        PlayerAnimationState state = PLAY_STATES.get(playerId);
        if (state == null) {
            System.err.println("Player doesnt have anim to play");
            return;
        }

        System.out.println("动画播放完成: " + state);
        PLAY_STATES.remove(playerId);
    }

    public boolean hasAnim(int playerId) {
        return PLAY_STATES.containsKey(playerId);
    }
}
