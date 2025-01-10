package work.microhand.steveanim.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import work.microhand.steveanim.client.model.entity.TNTEntityModel;
import work.microhand.steveanim.common.entity.TNTEntity;

/**
 * @author SanseYooyea
 */
public class TNTEntityRenderer extends GeoEntityRenderer<TNTEntity> {
    public TNTEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TNTEntityModel());
    }
}
