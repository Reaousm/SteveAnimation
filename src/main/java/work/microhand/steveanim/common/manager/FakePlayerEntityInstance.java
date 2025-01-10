package work.microhand.steveanim.common.manager;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import work.microhand.steveanim.common.entity.EntityManager;
import work.microhand.steveanim.common.entity.FakePlayerEntity;
import work.microhand.steveanim.common.entity.MaceEntity;
import work.microhand.steveanim.common.entity.TNTEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SanseYooyea
 */
public enum FakePlayerEntityInstance {

    INSTANCE;

    private final Map<Integer, FakePlayerEntity> fakePlayers = new HashMap<>();
    private final Map<Integer, TNTEntity> tntEntities = new HashMap<>();
    private final Map<Integer, MaceEntity> maceEntities = new HashMap<>();

    public FakePlayerEntity getOrCreateFakePlayer(AbstractClientPlayerEntity playerEntity) {
        FakePlayerEntity getOrCreate;
        if (fakePlayers.containsKey(playerEntity.getId())) {
            getOrCreate = fakePlayers.get(playerEntity.getId());
        } else {
            System.out.println("生成新的FakePlayerEntity");
            getOrCreate = new FakePlayerEntity(EntityManager.FAKE_PLAYER, playerEntity.getEntityWorld());
            getOrCreate.setReplacedPlayer(playerEntity);
            fakePlayers.put(playerEntity.getId(), getOrCreate);
            System.out.println("新的FakePlayerEntity皮肤: " + getOrCreate.getReplacedPlayer().getSkinTextures().texture());
        }

        return getOrCreate;
    }

    public TNTEntity getOrCreateTNTEntity(AbstractClientPlayerEntity playerEntity) {
        TNTEntity getOrCreate;
        if (tntEntities.containsKey(playerEntity.getId())) {
            getOrCreate = tntEntities.get(playerEntity.getId());
        } else {
            getOrCreate = new TNTEntity(EntityManager.TNT, playerEntity.clientWorld);
            tntEntities.put(playerEntity.getId(), getOrCreate);
        }

        return getOrCreate;
    }

    public MaceEntity getOrCreateMaceEntity(AbstractClientPlayerEntity playerEntity) {
        MaceEntity getOrCreate;
        if (maceEntities.containsKey(playerEntity.getId())) {
            getOrCreate = maceEntities.get(playerEntity.getId());
        } else {
            getOrCreate = new MaceEntity(EntityManager.MACE, playerEntity.clientWorld);
            maceEntities.put(playerEntity.getId(), getOrCreate);
        }

        return getOrCreate;
    }

    public void remove(int id) {
        fakePlayers.remove(id);
        tntEntities.remove(id);
        maceEntities.remove(id);
    }
}
