package work.microhand.steveanim.common.handler;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import work.microhand.steveanim.common.model.PlayerAnimationState;
import work.microhand.steveanim.common.network.AnimationSyncPayload;

/**
 * @author SanseYooyea
 */
public class PlayerDamagedHandler {
    public static void register() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            Entity attackerEntity = source.getSource();
            System.out.println("entity: " + entity);
            System.out.println("source: " + source);
            System.out.println("attackerEntity: " + attackerEntity);
            System.out.println("sourceEntity: " + source.getSource());
            System.out.println("amount: " + amount);
            if (attackerEntity == null) {
                return true;
            }

            PlayerAnimationState state;
            System.out.println("attackerEntity.getType(): " + attackerEntity.getType());
            if (attackerEntity.getType() == EntityType.TNT) {
                state = PlayerAnimationState.DAMAGED_BY_TNT;
            } else {
                if (!(attackerEntity instanceof LivingEntity attacker)) {
                    return true;
                }

                boolean usingMace = false;
                for (ItemStack hand : attacker.getHandItems()) {
                    if (hand.getItem() == Items.MACE) {
                        usingMace = true;
                        break;
                    }
                }

                if (!usingMace) {
                    System.out.println("Not using mace");
                    return true;
                }

                System.out.println("Using mace");
                state = PlayerAnimationState.DAMAGED_BY_MACE;
            }

            System.out.println("state: " + state);
            if (!(entity instanceof ServerPlayerEntity player)) {
                return true;
            }

            MinecraftServer server = player.getServer();
            if (server == null) {
                return true;
            }

            server.getPlayerManager().getPlayerList().stream()
                    .filter(serverPlayer -> serverPlayer != player)
                    .filter(serverPlayer -> serverPlayer.canSee(player))
                    .forEach(trackingPlayer -> {
                        ServerPlayNetworking.send(trackingPlayer, new AnimationSyncPayload(player.getId(), state.name()));
                    });

            ServerPlayNetworking.send(player, new AnimationSyncPayload(player.getId(), state.name()));

            return true;
        });
    }
}
