package defeatedcrow.hac.main.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@SideOnly(Side.CLIENT)
public class AltTooltipEvent {

	private ArrayList<String> ores = new ArrayList<String>();

	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		ItemStack target = event.getItemStack();
		boolean flag = false;

		if (player != null && player instanceof EntityPlayerSP && !DCUtil.isEmpty(target)) {
			Item tI = target.getItem();

			if (target.hasTagCompound() && target.getTagCompound().hasKey("dcs.plated")) {
				byte b = target.getTagCompound().getByte("dcs.plated");
				event.getToolTip().add(TextFormatting.BOLD.toString() + "Plated Item x" + b);
			}

			if (CoreConfigDC.showAltTips) {
				// charm
				ItemStack[] inside = new ItemStack[9];
				for (int i = 0; i < 9; i++) {
					inside[i] = player.inventory.getStackInSlot(i + 9);
				}
				List<ItemStack> charms = new ArrayList<ItemStack>();
				for (ItemStack item1 : inside) {
					if (item1 != null && item1.getItem() != null && item1.getItem() == MagicInit.pendant) {
						int m = item1.getMetadata();
						if (m == 4) {
							flag = true;
						}
					}
				}

				if (event.getFlags().isAdvanced()) {
					// まず耐久値
					if (tI.isDamageable() && target.getMetadata() == 0) {
						int max = target.getMaxDamage();
						String ret = I18n.translateToLocal("dcs_climate.tip.durability") + ": " + max;
						event.getToolTip().add(ret);
					}

					// tool tier
					if (tI instanceof ItemTool) {
						Set<String> classes = tI.getToolClasses(target);
						if (!classes.isEmpty()) {
							String className = classes.iterator().next();
							int tier = ((ItemTool) tI).getHarvestLevel(target, className, player, null);
							String ret = I18n.translateToLocal("dcs_climate.tip.harvestlevel") + ": " + tier;
							event.getToolTip().add(ret);
						}
					}

					// climate reg
					float regH = DamageAPI.itemRegister.getHeatPreventAmount(target);
					float regC = DamageAPI.itemRegister.getColdPreventAmount(target);

					if (regH == 0 && regC == 0 && tI instanceof ItemArmor) {
						ArmorMaterial mat = ((ItemArmor) tI).getArmorMaterial();
						regH = DamageAPI.armorRegister.getHeatPreventAmount(mat);
						regC = DamageAPI.armorRegister.getColdPreventAmount(mat);
					}
					if (regH != 0 || regC != 0) {
						String ret = I18n.translateToLocal("dcs_climate.tip.resistance") + ": Heat " + regH +
								"/ Cold " + regC;
						event.getToolTip().add(ret);
					}

					// universal bucket
					if (tI instanceof UniversalBucket) {
						UniversalBucket bucket = (UniversalBucket) tI;
						FluidStack f = bucket.getFluid(target);
						if (f != null && f.getFluid() != null) {
							String fName = f.getFluid().getName();
							int temp = f.getFluid().getTemperature();
							DCHeatTier tier = DCHeatTier.getTypeByTemperature(temp);
							event.getToolTip().add(fName);
							event.getToolTip().add("Temp: " + temp + " / " + tier);
						}
					}
				}
			}

			if (flag) {
				this.ores = this.getOre(target);
				if (!ores.isEmpty()) {
					event.getToolTip().addAll(ores);
				}
			}
		}
	}

	private ArrayList<String> getOre(ItemStack item) {
		ArrayList<String> ore = new ArrayList<String>();

		int[] id = OreDictionary.getOreIDs(item);
		if (id != null && id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				String s = OreDictionary.getOreName(id[i]);
				ore.add(s);
			}
		}

		return ore;
	}

}
