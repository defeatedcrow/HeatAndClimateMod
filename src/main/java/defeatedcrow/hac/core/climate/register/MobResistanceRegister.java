package defeatedcrow.hac.core.climate.register;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.IMobHeatResistance;
import defeatedcrow.hac.core.DCLogger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

public class MobResistanceRegister implements IMobHeatResistance {

	public static List<ParamEntity> regList;

	public MobResistanceRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerEntityResistance(EntityType.DOLPHIN, 2.0F, 2.0F);
	}

	@Override
	public float getHeatResistance(ResourceLocation name, DCHeatTier temp) {
		if (name != null) {
			Optional<ParamEntity> ret = regList.stream().filter((p) -> p.entityName.equals(name.toString())).findAny();
			if (ret.isPresent()) {
				return temp.isCold() ? ret.map(p -> p.coldResistance).orElse(2.0F) :
						ret.map(p -> p.heatResistance).orElse(2.0F);
			}
			EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(name);
			Optional<MobClimateData> data = MobClimateData.getData(type);
			return temp.isCold() ? data.map(d -> d.getColdResistance()).orElse(2.0F) :
					data.map(d -> d.getHeatResistance()).orElse(2.0F);
		}
		return 2.0F;
	}

	@Override
	public float getHeatResistance(Entity entity, DCHeatTier temp) {
		if (entity != null) {
			Optional<ParamEntity> ret = regList.stream().filter((
					p) -> (p.getEntityType().orElse(EntityType.PLAYER).getBaseClass().isInstance(entity))).findAny();
			if (ret.isPresent()) {
				return temp.isCold() ? ret.map(p -> p.coldResistance).orElse(2.0F) :
						ret.map(p -> p.heatResistance).orElse(2.0F);
			}
			Optional<MobClimateData> data = MobClimateData.getData(entity);
			return temp.isCold() ? data.map(d -> d.getColdResistance()).orElse(2.0F) :
					data.map(d -> d.getHeatResistance()).orElse(2.0F);
		}
		return 2.0F;
	}

	@Override
	public void registerEntityResistance(ResourceLocation name, float heatResistance, float coldResistance) {
		if (name == null)
			return;
		ParamEntity reg = new ParamEntity(name.toString(), heatResistance, coldResistance);
		if (!regList.contains(reg)) {
			regList.add(reg);
			DCLogger.infoLog("Registered entity: " + name.toString() + "... heat " + heatResistance + " / cold " + coldResistance);
		}
	}

	@Override
	public void registerEntityResistance(EntityType<?> type, float heatResistance, float coldResistance) {
		if (type == null)
			return;
		ResourceLocation name = ForgeRegistries.ENTITY_TYPES.getKey(type);
		registerEntityResistance(name, heatResistance, coldResistance);
	}
}