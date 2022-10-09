
package defeatedcrow.hac.core.climate.register;

import java.util.Optional;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public enum MobClimateData {
	ALLAY(EntityType.ALLAY, 3.0F, 1.0F),
	AXOLOTL(EntityType.AXOLOTL, 3.0F, 1.0F),
	BAT(EntityType.BAT, 3.0F, 2.0F),
	BEE(EntityType.BEE, 3.0F, 2.0F),
	BLAZE(EntityType.BLAZE, 8.0F, 0.0F),
	CAT(EntityType.CAT, 4.0F, 2.0F),
	CHICKEN(EntityType.CHICKEN, 3.0F, 2.0F),
	COW(EntityType.COW, 2.0F, 3.0F),
	CREEPER(EntityType.CREEPER, 3.0F, 0.0F),
	ENDETMAN(EntityType.ENDERMAN, 1.0F, 6.0F),
	ENDERMITE(EntityType.ENDERMITE, 1.0F, 6.0F),
	EVOKER(EntityType.EVOKER, 3.0F, 3.0F),
	FOX(EntityType.FOX, 2.0F, 4.0F),
	FROG(EntityType.FROG, 3.0F, 2.0F),
	GHAST(EntityType.GHAST, 8.0F, 0.0F),
	GORT(EntityType.GOAT, 3.0F, 4.0F),
	GURDIAN(EntityType.GUARDIAN, 3.0F, 1.0F),
	HOGLIN(EntityType.HOGLIN, 8.0F, 1.0F),
	HORSE(EntityType.HORSE, 3.0F, 3.0F),
	HUSK(EntityType.HUSK, 4.0F, 3.0F),
	ILLUSIONER(EntityType.ILLUSIONER, 3.0F, 3.0F),
	IRON_GOREM(EntityType.IRON_GOLEM, 8.0F, 8.0F),
	LLAMA(EntityType.LLAMA, 2.0F, 4.0F),
	MAGMA_CUBE(EntityType.MAGMA_CUBE, 8.0F, 0.0F),
	MOOSHROOM(EntityType.MOOSHROOM, 3.0F, 3.0F),
	OCELOT(EntityType.OCELOT, 4.0F, 2.0F),
	PANDA(EntityType.PANDA, 3.0F, 3.0F),
	PARROT(EntityType.PARROT, 3.0F, 2.0F),
	PHANTOM(EntityType.PHANTOM, 2.0F, 3.0F),
	PIG(EntityType.PIG, 3.0F, 2.0F),
	PIGLIN(EntityType.PIGLIN, 8.0F, 0.0F),
	PILLAGER(EntityType.PILLAGER, 3.0F, 3.0F),
	POLAR_BEAR(EntityType.POLAR_BEAR, 1.0F, 6.0F),
	PUFFERFISH(EntityType.PUFFERFISH, 2.0F, 2.0F),
	RABBIT(EntityType.RABBIT, 3.0F, 2.0F),
	RAVAGER(EntityType.RAVAGER, 3.0F, 3.0F),
	SHEEP(EntityType.SHEEP, 2.0F, 3.0F),
	SHULKER(EntityType.SHULKER, 0.0F, 8.0F),
	SILVERFISH(EntityType.SILVERFISH, 1.0F, 2.0F),
	SLIME(EntityType.SLIME, 2.0F, 2.0F),
	SKELETON(EntityType.SKELETON, 1.0F, 4.0F),
	SNOW_GOLEM(EntityType.SNOW_GOLEM, 0.0F, 8.0F),
	SPIDER(EntityType.SPIDER, 2.0F, 1.0F),
	STRAY(EntityType.STRAY, 1.0F, 6.0F),
	STRIDER(EntityType.STRIDER, 8.0F, 0.0F),
	TROPICAL_FISH(EntityType.TROPICAL_FISH, 3.0F, 2.0F),
	TURTLE(EntityType.TURTLE, 3.0F, 2.0F),
	VEX(EntityType.VEX, 8.0F, 2.0F),
	VILLAGER(EntityType.VILLAGER, 4.0F, 4.0F),
	VINDICATER(EntityType.VINDICATOR, 3.0F, 3.0F),
	WARDEN(EntityType.WARDEN, 8.0F, 2.0F),
	WITCH(EntityType.WITCH, 3.0F, 3.0F),
	WITHER(EntityType.WITHER, 8.0F, 2.0F),
	WOLF(EntityType.WOLF, 3.0F, 4.0F),
	ZOGLIN(EntityType.ZOGLIN, 4.0F, 0.0F),
	ZONBIE(EntityType.ZOMBIE, 0.0F, 6.0F);

	private final EntityType<?> type;
	private final float heat;
	private final float cold;

	private MobClimateData(EntityType<?> t, float h, float c) {
		type = t;
		heat = h;
		cold = c;
	}

	public static Optional<MobClimateData> getData(EntityType typeIn) {
		for (MobClimateData data : MobClimateData.values()) {
			if (data.type.equals(typeIn)) {
				return Optional.of(data);
			}
		}
		return Optional.empty();
	}

	public static Optional<MobClimateData> getData(Entity entity) {
		if (entity != null)
			for (MobClimateData data : MobClimateData.values()) {
				if (data.type.tryCast(entity) != null) {
					return Optional.of(data);
				}
			}
		return Optional.empty();
	}

	public float getHeatResistance() {
		return heat;
	}

	public float getColdResistance() {
		return cold;
	}
}
