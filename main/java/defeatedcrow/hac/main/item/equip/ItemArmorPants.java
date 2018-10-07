package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorPants extends ItemArmorDC implements ITexturePath {

	public ItemArmorPants(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, mat, slot, t);
		colorList = new EnumDyeColor[] {
				EnumDyeColor.GRAY,
				EnumDyeColor.BLUE
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		net.minecraft.client.model.ModelBiped next = ClimateMain.proxy.getArmorModel(8);
		if (next != null) {
			next.setModelAttributes(_default);
			return next;
		}
		return _default;
	}

}
