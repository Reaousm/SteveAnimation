package work.microhand.steveanim.client.model.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import work.microhand.steveanim.SteveAnim;
import work.microhand.steveanim.common.entity.TNTEntity;

/**
 * @author SanseYooyea
 */
public class TNTEntityModel extends DefaultedEntityGeoModel<TNTEntity> {
    public TNTEntityModel() {
        super(Identifier.of(SteveAnim.MOD_ID, "tnt"));
    }
}
