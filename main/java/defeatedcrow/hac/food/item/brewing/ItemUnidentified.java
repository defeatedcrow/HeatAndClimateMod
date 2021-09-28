package defeatedcrow.hac.food.item.brewing;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.EnumMicrobeType;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemUnidentified extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"archaea",
			"bacilli",
			"cocci",
			"yeast",
			"mold"
	};

	public ItemUnidentified() {
		super();
		maxMeta = 4;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/brewing/unidentified_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			/* 鑑定 */
			if (!DCUtil.isEmpty(item)) {
				IMicrobe microbe = getSpecies(item);
				if (microbe != null && microbe.getMicrobeItem() != null) {
					int meta = 0;
					if (item.hasTagCompound() && item.getTagCompound().hasKey("microbe_rarity")) {
						int rarity = item.getTagCompound().getInteger("microbe_rarity");
						if (rarity > 0 && rarity < 5) {
							meta = rarity - 1;
						}
					} else {
						int r = world.rand.nextInt(100);
						if (r < 5)
							meta = 2;// RARE
						if (r > 69)
							meta = 1;// UNCOMMON
					}

					ItemStack ret = new ItemStack(microbe.getMicrobeItem(), 1, meta);
					if (!world.isRemote) {
						EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
						world.spawnEntity(drop);
					}
					if (!player.capabilities.isCreativeMode)
						DCUtil.redAndDel(item, 1);
					player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.75F, 1.25F);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, ItemStack.EMPTY);
	}

	public static ItemStack setSpecies(IMicrobe sp) {
		ItemStack ret = new ItemStack(FoodInit.unidentified, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		if (sp != null) {
			if (sp.getType() == EnumMicrobeType.BACILLI) {
				ret = new ItemStack(FoodInit.unidentified, 1, 1);
			} else if (sp.getType() == EnumMicrobeType.COCCI) {
				ret = new ItemStack(FoodInit.unidentified, 1, 2);
			} else if (sp.getType() == EnumMicrobeType.YEAST) {
				ret = new ItemStack(FoodInit.unidentified, 1, 3);
			} else if (sp.getType() == EnumMicrobeType.FUNGI) {
				ret = new ItemStack(FoodInit.unidentified, 1, 4);
			}
			tag.setString("species", sp.getName());
		} else {
			tag.setString("species", "bacillus");
		}
		ret.setTagCompound(tag);
		return ret;
	}

	public static ItemStack setRarity(ItemStack item, int i) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			tag.setInteger("microbe_rarity", i);
			item.setTagCompound(tag);
		}
		return item;
	}

	public static IMicrobe getSpecies(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("species")) {
				String name = tag.getString("species");
				IMicrobe sp = MainAPIManager.microbeRegister.getSpecies(name);
				if (sp != null) {
					return sp;
				}
			}
		}
		return null;
	}

}
