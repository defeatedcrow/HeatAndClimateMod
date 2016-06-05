package defeatedcrow.hac.main.item.equip;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.DCMaterial;

public class ItemArmorThinDC extends ItemArmor implements ITexturePath {

	private final String tex;
	private final DCMaterial material;

	public ItemArmorThinDC(ArmorMaterial m, DCMaterial mat, EntityEquipmentSlot slot, String t) {
		super(m, 2, slot);
		material = mat;
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		String s = "met_";
		switch (this.armorType) {
		case CHEST:
			s = "plate_";
			break;
		case LEGS:
			s = "leggins_";
			break;
		case FEET:
			s = "boots_";
			break;
		default:
			s = "met_";
		}
		return "dcs_climate:items/equip/" + s + tex;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "dcs_climate:textures/models/armor/" + tex + "_layer_" + slot + ".png";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		return ClimateCore.proxy.getArmorModel(armorSlot.getIndex());
	}

}