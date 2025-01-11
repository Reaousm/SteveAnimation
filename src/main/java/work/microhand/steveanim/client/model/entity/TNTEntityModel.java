package work.microhand.steveanim.client.model.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import work.microhand.steveanim.SteveAnimation;
import work.microhand.steveanim.common.entity.TNTEntity;

/**
 * @author SanseYooyea
 */
public class TNTEntityModel extends DefaultedEntityGeoModel<TNTEntity> {
    public TNTEntityModel() {
        super(Identifier.of(SteveAnimation.MOD_ID, "tnt"));
    }
}
