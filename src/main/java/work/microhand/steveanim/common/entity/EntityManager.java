package work.microhand.steveanim.common.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import work.microhand.steveanim.SteveAnim;

/**
 * @author SanseYooyea
 */
public class EntityManager {
    public static final EntityType<TNTEntity> TNT = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SteveAnim.MOD_ID, "tnt"),
            EntityType.Builder.create(TNTEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.98F, 0.98F)
                    .build()
    );

    public static final EntityType<MaceEntity> MACE = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SteveAnim.MOD_ID, "mace"),
            EntityType.Builder.create(MaceEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.98F, 0.98F)
                    .build()
    );

    public static final EntityType<FakePlayerEntity> FAKE_PLAYER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(SteveAnim.MOD_ID, "fake_player"),
            EntityType.Builder.create(FakePlayerEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.98F, 0.98F)
                    .build()
    );
}
