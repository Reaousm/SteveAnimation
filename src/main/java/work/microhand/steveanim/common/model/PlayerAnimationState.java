package work.microhand.steveanim.common.model;

import java.util.Arrays;

/**
 * @author SanseYooyea
 */
public enum PlayerAnimationState {
    IDLE(0),
    DAMAGED_BY_MACE(2),
    DAMAGED_BY_TNT(2.5);

    private final double animationTime;

    PlayerAnimationState(double animationTime) {
        this.animationTime = animationTime;
    }

    public static PlayerAnimationState fromString(String name) {
        return Arrays.stream(values())
                .filter(state -> state.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid animation state: " + name));
    }

    public double getAnimationTime() {
        return animationTime;
    }
}
