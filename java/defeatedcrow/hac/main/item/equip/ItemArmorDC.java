package defeatedcrow.hac.main.item.equip;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorDC extends ItemArmor implements ITexturePath {

	private final String tex;
	private final DCMaterialEnum material;

	public ItemArmorDC(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
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
		if (slot == EntityEquipmentSlot.LEGS) {
			return "dcs_climate:textures/models/armor/" + tex + "_layer_2.png";
		} else {
			return "dcs_climate:textures/models/armor/" + tex + "_layer_1.png";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		if (armorSlot == EntityEquipmentSlot.LEGS) {
			ModelThinBiped next = ClimateCore.proxy.getArmorModel(armorSlot.getIndex());
			if (next != null) {
				next.setModelAttributes(_default);
				return next;
			}
		}
		return _default;
	}

}
