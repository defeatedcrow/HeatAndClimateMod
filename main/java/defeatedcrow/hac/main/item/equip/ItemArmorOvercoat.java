package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorOvercoat extends ItemArmorDC implements ITexturePath {

	private boolean isShort = false;

	public ItemArmorOvercoat(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, 1, mat, slot, "coat_" + t);
	}

	public ItemArmorOvercoat setShort() {
		isShort = true;
		return this;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/equip/" + tex;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		net.minecraft.client.model.ModelBiped next = ClimateMain.proxy.getArmorModel(4);
		if (isShort) {
			next = ClimateMain.proxy.getArmorModel(5);
		}
		if (next != null) {
			next.setModelAttributes(_default);
			return next;
		}
		return _default;
	}

}
