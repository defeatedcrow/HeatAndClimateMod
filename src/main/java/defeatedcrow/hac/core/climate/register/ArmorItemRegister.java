package defeatedcrow.hac.core.climate.register;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.api.damage.IArmorItemRegister;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class ArmorItemRegister implements IArmorItemRegister {

	public static List<ParamArmorItem> regList;

	public ArmorItemRegister() {
		regList = Lists.newArrayList();
		init();
	}

	private void init() {
		registerArmorItem(Items.LEATHER_HORSE_ARMOR, 2.0F, 2.0F);
		registerArmorItem(Items.DIAMOND_HORSE_ARMOR, 2.0F, 2.0F);
	}

	@Override
	public void registerArmorItem(Item item, float heat, float cold) {
		if (item == null)
			return;
		ResourceLocation res = ForgeRegistries.ITEMS.getKey(item);
		ParamArmorItem reg = new ParamArmorItem(res.toString(), heat, cold);
		if (!regList.contains(reg)) {
			regList.add(reg);
			DCLogger.infoLog("Registered armor item: " + res.toString() + "... heat " + heat + " / cold " + cold);
		}
	}

	@Override
	public float getHeatPreventAmount(ItemStack item) {
		if (item.isEmpty())
			return 0.0F;
		Optional<ResourceLocation> res = DCUtil.getRes(item.getItem());
		Optional<ParamArmorItem> ret = regList.stream().filter((
				p) -> p.itemName.equals((res.orElse(DCUtil.DUMMY)).toString())).findAny();
		return ret.map(p -> p.heatResistance).orElse(0.0F);
	}

	@Override
	public float getColdPreventAmount(ItemStack item) {
		if (item.isEmpty())
			return 0.0F;
		Optional<ResourceLocation> res = DCUtil.getRes(item.getItem());
		Optional<ParamArmorItem> ret = regList.stream().filter((
				p) -> p.itemName.equals((res.orElse(DCUtil.DUMMY)).toString())).findAny();
		return ret.map(p -> p.coldResistance).orElse(0.0F);
	}

}
