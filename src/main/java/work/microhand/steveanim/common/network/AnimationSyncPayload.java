package work.microhand.steveanim.common.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import work.microhand.steveanim.SteveAnim;

/**
 * @author SanseYooyea
 */
public record AnimationSyncPayload(
        int playerId,
        String state
) implements CustomPayload {
    public static final Identifier ANIM_SYNC_ID = Identifier.of(SteveAnim.MOD_ID, "animation_sync");
    public static final CustomPayload.Id<AnimationSyncPayload> ID = new CustomPayload.Id<>(ANIM_SYNC_ID);

    // should you need to send more data, add the appropriate record parameters and change your codec:
    public static final PacketCodec<RegistryByteBuf, AnimationSyncPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, AnimationSyncPayload::playerId,
            PacketCodecs.STRING, AnimationSyncPayload::state,
            AnimationSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
