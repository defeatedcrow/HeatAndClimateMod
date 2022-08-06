package defeatedcrow.hac.main.item.equip;

import java.util.Collections;
import java.util.Map;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemArmorCombat extends ItemArmorDC implements ITexturePath {

	public ItemArmorCombat(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, 1, mat, slot, t);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack,
			EntityEquipmentSlot armorSlot, net.minecraft.client.model.ModelBiped _default) {
		net.minecraft.client.model.ModelBiped met = ClimateMain.proxy.getArmorModel(14);
		net.minecraft.client.model.ModelBiped body = ClimateMain.proxy.getArmorModel(15);
		net.minecraft.client.model.ModelBiped legs = ClimateCore.proxy.getArmorModel(armorSlot.getIndex());
		net.minecraft.client.model.ModelBiped boots = ClimateMain.proxy.getArmorModel(8);
		if (armorSlot == EntityEquipmentSlot.HEAD && met != null) {
			met.setModelAttributes(_default);
			return met;
		} else if (armorSlot == EntityEquipmentSlot.CHEST && body != null) {
			body.setModelAttributes(_default);
			return body;
		} else if (armorSlot == EntityEquipmentSlot.LEGS && legs != null) {
			legs.setModelAttributes(_default);
			return legs;
		} else if (armorSlot == EntityEquipmentSlot.FEET && boots != null) {
			boots.setModelAttributes(_default);
			return boots;
		}
		return _default;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		String name = PASS + tex;
		if (slot == EntityEquipmentSlot.LEGS) {
			return name + "_layer_2.png";
		} else if (slot == EntityEquipmentSlot.FEET) {
			return name + "_layer_3.png";
		}
		return name + "_combat_layer_1.png";
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		if (!DCUtil.isEmpty(stack) && stack.getItem() == MainInit.titaniumArmor[1])
			EnchantmentHelper.setEnchantments(PROJ4, stack);
		super.onCreated(stack, world, player);
	}

	public static final Map<Enchantment, Integer> PROJ2 = Collections.singletonMap(Enchantments.PROJECTILE_PROTECTION, 2);
	public static final Map<Enchantment, Integer> PROJ4 = Collections.singletonMap(Enchantments.PROJECTILE_PROTECTION, 4);

}
