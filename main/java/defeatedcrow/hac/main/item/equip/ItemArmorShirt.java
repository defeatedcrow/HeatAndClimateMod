package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorShirt extends ItemArmorDC implements ITexturePath {

	public ItemArmorShirt(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, mat, slot, t);
		colorList = new EnumDyeColor[] {
				EnumDyeColor.ORANGE,
				EnumDyeColor.MAGENTA,
				EnumDyeColor.LIGHT_BLUE,
				EnumDyeColor.YELLOW,
				EnumDyeColor.PINK,
				EnumDyeColor.GRAY,
				EnumDyeColor.CYAN,
				EnumDyeColor.BLUE,
				EnumDyeColor.GREEN,
				EnumDyeColor.RED,
				EnumDyeColor.BLACK
		};
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		ModelThinBiped next = ClimateCore.proxy.getArmorModel(1);
		if (next != null) {
			next.setModelAttributes(_default);
			return next;
		}
		return _default;
	}

}
