package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorHat extends ItemArmorDC implements ITexturePath {

	public ItemArmorHat(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, mat, slot, t);
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/equip/hat_" + tex;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "dcs_climate:textures/models/armor/hat_" + tex + ".png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		net.minecraft.client.model.ModelBiped next = ClimateMain.proxy.getArmorModel(0);
		if (next != null) {
			next.setModelAttributes(_default);
			return next;
		}
		return _default;
	}

}
