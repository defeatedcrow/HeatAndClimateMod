package defeatedcrow.hac.magic.item;

import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorGemBoots extends ItemArmor implements ITexturePath {

	private final String tex;
	private final DCMaterialEnum material;

	public ItemArmorGemBoots(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, 2, slot);
		material = mat;
		tex = t;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:items/equip/boots_gem_" + tex;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return "dcs_climate:textures/models/armor/gemboots_" + tex + "_layer_2.png";
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

	// update
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (!world.isRemote && !DCUtil.isEmpty(stack) && player != null) {
			if (stack.getItem() == MagicInit.gemBootsBird && !player.isPotionActive(MainInit.bird)) {
				player.addPotionEffect(new PotionEffect(MainInit.bird, 600, 0));
			} else if (stack.getItem() == MagicInit.gemBootsFish && !player.isPotionActive(MainInit.ocean)) {
				player.addPotionEffect(new PotionEffect(MainInit.ocean, 600, 0));
			}
		}
	}

}
