package work.microhand.steveanim.client.model.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import work.microhand.steveanim.SteveAnimation;
import work.microhand.steveanim.common.entity.MaceEntity;

/**
 * @author SanseYooyea
 */
public class MaceEntityModel extends DefaultedEntityGeoModel<MaceEntity> {
    public MaceEntityModel() {
        super(Identifier.of(SteveAnimation.MOD_ID, "mace"));
    }
}
