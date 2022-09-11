package defeatedcrow.hac.core.climate.register;

import java.util.Optional;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class ParamEntity {
	public final String entityName;
	public final float heatResistance;
	public final float coldResistance;

	public ParamEntity(String name, float heat, float cold) {
		entityName = name;
		heatResistance = heat;
		coldResistance = cold;
	}

	public Optional<EntityType<?>> getEntityType() {
		EntityType<?> ret = ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation(entityName));
		if (ret != null) {
			return Optional.of(ret);
		}
		return Optional.empty();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof ParamEntity) {
			ParamEntity target = (ParamEntity) obj;
			return entityName.equals(target.entityName) && heatResistance == target.heatResistance && coldResistance == target.coldResistance;
		}
		return false;
	}
}
