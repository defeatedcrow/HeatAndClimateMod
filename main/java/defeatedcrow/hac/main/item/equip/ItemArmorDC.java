package defeatedcrow.hac.main.item.equip;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.client.base.ModelThinBiped;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IColorableArmor;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.util.DCMaterialEnum;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.DyeUtils;

public class ItemArmorDC extends ItemArmor implements ITexturePath, IColorableArmor {

	public final String tex;
	public final DCMaterialEnum material;
	public static final String PASS = "dcs_climate:textures/models/armor/";
	public EnumDyeColor[] colorList = new EnumDyeColor[] {};

	public ItemArmorDC(ArmorMaterial m, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, 2, slot);
		material = mat;
		tex = t;
	}

	public ItemArmorDC(ArmorMaterial m, int renderIndex, DCMaterialEnum mat, EntityEquipmentSlot slot, String t) {
		super(m, renderIndex, slot);
		material = mat;
		tex = t;
	}

	public ItemArmorDC setColorList(EnumDyeColor... list) {
		if (list != null)
			colorList = list;
		return this;
	}

	@Override
	public void setColorableList(EnumDyeColor... colors) {
		if (colors != null)
			colorList = colors;
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
		String name = PASS + tex;
		if (!DCUtil.isEmpty(stack)) {
			int c = getColor(stack);
			if (c > 0) {
				EnumDyeColor d = EnumDyeColor.byMetadata(c);
				name += "_" + d;
			}
		}
		if (slot == EntityEquipmentSlot.LEGS) {
			return name + "_layer_2.png";
		} else {
			return name + "_layer_1.png";
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flagIn) {
		if (!DCUtil.isEmpty(stack)) {
			if (getColorableList().length > 0) {
				String s = "NORMAL";
				for (EnumDyeColor d : getColorableList()) {
					if (d != null && d != EnumDyeColor.WHITE) {
						s += " " + getChatColor(d) + d;
					}
				}
				int c = getColor(stack);
				if (c > 0) {
					EnumDyeColor dye = EnumDyeColor.byMetadata(c);
					tooltip.add("Color: " + getChatColor(dye) + dye);
				} else {
					tooltip.add("Color: NORMAL");
				}
				tooltip.add("==" + I18n.format("dcs.tip.cloth.color") + "==");
				tooltip.add(s);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack item) {
		return MainCoreConfig.armor_effect;
	}

	// color
	@Override
	public EnumDyeColor[] getColorableList() {
		return colorList;
	}

	@Override
	public EnumDyeColor getCurrentColor(ItemStack armor) {
		if (DCUtil.isEmpty(armor)) {
			return EnumDyeColor.WHITE;
		}
		int c = getColor(armor);
		if (c > 0) {
			EnumDyeColor dye = EnumDyeColor.byMetadata(c);
			return dye;
		} else {
			return EnumDyeColor.WHITE;
		}
	}

	public EnumDyeColor getItemColor(ItemStack item) {
		if (DCUtil.isEmpty(item) || DCUtil.matchDicName("soap", item)) {
			return EnumDyeColor.WHITE;
		}
		if (getColorableList() != null && getColorableList().length > 0) {
			for (EnumDyeColor dye : getColorableList()) {
				if (dye != null && DyeUtils.isDye(item)) {
					EnumDyeColor d = DyeUtils.colorFromStack(item).orElse(EnumDyeColor.WHITE);
					if (d == dye)
						return dye;
				}
			}
		}
		return EnumDyeColor.WHITE;
	}

	public int getColorNumber(ItemStack dyeItem) {
		if (DCUtil.isEmpty(dyeItem) || DCUtil.matchDicName("soap", dyeItem)) {
			return EnumDyeColor.WHITE.getMetadata();
		}
		EnumDyeColor dye = getItemColor(dyeItem);
		if (dye != null && DyeUtils.isDye(dyeItem)) {
			EnumDyeColor d = DyeUtils.colorFromStack(dyeItem).orElse(EnumDyeColor.WHITE);
			if (d == dye)
				return d.getMetadata();
		}
		return -1;
	}

	/**
	 * バニラ仕様とは全く異なり、オーバーレイの着色機能を使わずテクスチャを張り替える。
	 */
	@Override
	public boolean hasColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			if (tag.hasKey("color") && tag.getInteger("color") >= 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			if (tag.hasKey("color") && tag.getInteger("color") >= 0) {
				int ret = tag.getInteger("color");
				return ret;
			}
		}
		return -1;
	}

	@Override
	public void removeColor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null) {
			if (tag.hasKey("color")) {
				tag.removeTag("color");
			}
		}
	}

	@Override
	public void setColor(ItemStack stack, int color) {
		NBTTagCompound tag = stack.getTagCompound();

		if (tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}

		tag.setInteger("color", color);
	}

	public static String getChatColor(EnumDyeColor color) {
		int meta = color.getMetadata();
		meta = MathHelper.clamp(meta, 0, 15);
		TextFormatting chat = MainUtil.DYE_CHAT_COLOR[meta];
		return chat.toString();
	}

	public static ItemStack setRondomColor(ItemStack stack) {
		if (DCUtil.isEmpty(stack))
			return stack;
		if (stack.getItem() instanceof ItemArmorDC && ((ItemArmorDC) stack.getItem()).getColorableList() != null) {
			EnumDyeColor[] list = ((ItemArmorDC) stack.getItem()).getColorableList();
			int c = itemRand.nextInt(list.length);
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
				stack.setTagCompound(tag);
			}
			tag.setInteger("color", list[c].getMetadata());
			stack.setTagCompound(tag);
		}
		return stack;
	}

}
