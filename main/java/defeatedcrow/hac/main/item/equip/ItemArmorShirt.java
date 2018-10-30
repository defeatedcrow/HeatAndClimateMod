package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
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
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase living, ItemStack stack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		net.minecraft.client.model.ModelBiped next = ClimateCore.proxy.getArmorModel(1);
		// 特殊処理
		if (!DCUtil.isEmpty(stack) && stack.getItem() == MainInit.clothShirt) {
			EnumDyeColor color = ((ItemArmorDC) stack.getItem()).getCurrentColor(stack);
			if (color == EnumDyeColor.RED || color == EnumDyeColor.PURPLE || color == EnumDyeColor.BLACK) {
				next = ClimateMain.proxy.getArmorModel(10);
			}
		}
		if (next != null) {
			next.setModelAttributes(_default);
			return next;
		}
		return _default;
	}

}
