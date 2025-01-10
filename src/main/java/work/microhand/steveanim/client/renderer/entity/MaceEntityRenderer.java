package work.microhand.steveanim.client.renderer.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import work.microhand.steveanim.client.model.entity.MaceEntityModel;
import work.microhand.steveanim.common.entity.MaceEntity;

/**
 * @author SanseYooyea
 */
public class MaceEntityRenderer extends GeoEntityRenderer<MaceEntity> {
    public MaceEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MaceEntityModel());
    }


}
