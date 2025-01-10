package work.microhand.steveanim.client.model.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import work.microhand.steveanim.SteveAnim;
import work.microhand.steveanim.common.entity.FakePlayerEntity;

/**
 * @author SanseYooyea
 */
public class FakePlayerEntityModel extends DefaultedEntityGeoModel<FakePlayerEntity> {
    public FakePlayerEntityModel() {
        super(Identifier.of(SteveAnim.MOD_ID, "steve"));
    }

    @Override
    public Identifier getTextureResource(FakePlayerEntity animatable) {
        if (animatable.getReplacedPlayerTexture() == null) {
            return null;
        }

        return animatable.getReplacedPlayerTexture();
    }


}
