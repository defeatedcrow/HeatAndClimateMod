package defeatedcrow.hac.plugin.waila;

import java.util.List;

import javax.annotation.Nonnull;

import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.main.util.DCName;
import mcp.mobius.waila.addons.core.HUDHandlerEntities;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class HUDHandlerClimateMob extends HUDHandlerEntities {

	@Nonnull
	@Override
	public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor,
			IWailaConfigHandler config) {
		if (!config.getConfig("dcs_climate.showclimate") || entity == null)
			return currenttip;

		if (entity instanceof EntityLivingBase) {
			float heat = DamageAPI.resistantData.getHeatResistant(entity);
			float cold = DamageAPI.resistantData.getColdResistant(entity);
			if (entity.isImmuneToFire()) {
				heat += CoreConfigDC.infernalInferno ? 8.0F : 4.0F;
				cold -= 2.0F;
			}
			if (((EntityLivingBase) entity).isEntityUndead()) {
				heat -= 2.0F;
				cold += 2.0F;
			}

			currenttip.add(String.format("%s : %.1f / %.1f", DCName.CLIMATE_R.getLocalizedName(), heat, cold));
		}

		return currenttip;
	}

	public static void register(IWailaRegistrar registrar) {
		HUDHandlerClimateMob provider = new HUDHandlerClimateMob();
		registrar.registerBodyProvider(provider, EntityLivingBase.class);
	}

}
