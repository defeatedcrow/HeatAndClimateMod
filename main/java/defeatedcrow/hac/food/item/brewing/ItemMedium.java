package defeatedcrow.hac.food.item.brewing;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.api.brewing.EnumHabitat;
import defeatedcrow.hac.main.api.brewing.EnumMedium;
import defeatedcrow.hac.main.api.brewing.IMediumItem;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMedium extends DCItem implements IMediumItem {

	private final int maxMeta;

	private static String[] names = { "simple", "standard", "potato", "giblets", "bacteria" };

	public ItemMedium() {
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
		String s = "items/food/brewing/medium_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		return EnumActionResult.PASS;
	}

	// using
	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null) {
			ItemStack item = player.getHeldItem(hand);
			/* 微生物を採取 */
			if (!DCUtil.isEmpty(item)) {
				EnumMedium type = getMedium(item);
				RayTraceResult res = this.rayTrace(world, player, true);
				DCLogger.debugInfoLog("check 1");
				if (res != null) {
					/* Eventフック予定 */
					// block
					if (res.typeOfHit == RayTraceResult.Type.BLOCK) {
						DCLogger.debugInfoLog("check 2");
						BlockPos pos = res.getBlockPos();
						if (pos.getY() > 0 && pos.getY() < 255) {
							IBlockState state = world.getBlockState(pos);
							EnumHabitat habitat = EnumHabitat.getHabitat(world, pos);
							if (habitat != null) {
								DCLogger.debugInfoLog("check 3 " + habitat);
								IMicrobe microbe = MainAPIManager.microbeRegister.collectSpecies(habitat, type);
								if (microbe != null) {
									DCLogger.debugInfoLog("check 5 " + microbe.getName());
									if (!world.isRemote) {
										ItemStack ret = setMicrobeItem(item.copy(), microbe, null);
										ret.setCount(1);
										EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D,
												player.posZ, ret);
										world.spawnEntity(drop);
									}
									if (!player.capabilities.isCreativeMode) {
										DCUtil.redAndDel(item, 1);
									}
									return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
								}
							}
						}
					} else if (res.typeOfHit == RayTraceResult.Type.ENTITY && res.entityHit instanceof IAnimals) {
						IMicrobe microbe = MainAPIManager.microbeRegister.collectSpecies(EnumHabitat.ANIMAL, type);
						if (microbe != null) {
							if (!world.isRemote) {
								ItemStack ret = setMicrobeItem(item.copy(), microbe, null);
								ret.setCount(1);
								EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ,
										ret);
								world.spawnEntity(drop);
							}
							if (!player.capabilities.isCreativeMode) {
								DCUtil.redAndDel(item, 1);
							}
							player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, 0.75F, 1.25F);
							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
						}
					}
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, ItemStack.EMPTY);
	}

	@Override
	public EnumMedium getMedium(ItemStack item) {
		if (DCUtil.isEmpty(item))
			return EnumMedium.SIMPLE;
		int meta = item.getItemDamage();
		switch (meta) {
		case 0:
			return EnumMedium.SIMPLE;
		case 1:
			return EnumMedium.STANDARD;
		case 2:
			return EnumMedium.POTATO;
		case 3:
			return EnumMedium.GIBLETS;
		case 4:
			return EnumMedium.BACTERIA;
		default:
			return EnumMedium.SIMPLE;
		}
	}

	@Override
	public ItemStack getMediumItem(EnumMedium medium) {
		int meta = 0;
		if (medium != null) {
			switch (medium) {
			case SIMPLE:
				meta = 0;
				break;
			case STANDARD:
				meta = 1;
				break;
			case POTATO:
				meta = 2;
				break;
			case GIBLETS:
				meta = 3;
				break;
			case BACTERIA:
				meta = 4;
				break;
			default:
				meta = 0;
			}
		}
		return new ItemStack(this, 1, meta);
	}

	public static ItemStack getMicrobeItem(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			IMicrobe microbe = getMicrobeData(item);
			EnumRarity rarity = getMicrobeRarity(item);
			ItemStack ret = ItemStack.EMPTY;
			if (microbe != null) {
				if (rarity == null) {
					ret = ItemUnidentified.setSpecies(microbe);
					return ret;
				} else if (microbe.getMicrobeItem() != null) {
					int i = 0;
					if (rarity == EnumRarity.UNCOMMON)
						i = 1;
					if (rarity == EnumRarity.RARE)
						i = 2;
					ret = new ItemStack(microbe.getMicrobeItem(), 1, i);
					return ret;
				}
			}
			/* add event */
			return ret;
		}
		return ItemStack.EMPTY;
	}

	public static ItemStack setMicrobeItem(ItemStack item, IMicrobe microbe, EnumRarity rarity) {
		if (!DCUtil.isEmpty(item) && microbe != null) {
			if (item.hasTagCompound() && item.getTagCompound().hasKey("microbe_name")) {
				return item;
			}
			NBTTagCompound tag = item.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			tag.setString("microbe_name", microbe.getName());
			if (rarity == null) {
				tag.setInteger("microbe_rarity", 0);
			}
			if (rarity == EnumRarity.COMMON) {
				tag.setInteger("microbe_rarity", 1);
			}
			if (rarity == EnumRarity.UNCOMMON) {
				tag.setInteger("microbe_rarity", 2);
			}
			if (rarity == EnumRarity.RARE) {
				tag.setInteger("microbe_rarity", 3);
			}
			if (rarity == EnumRarity.EPIC) {
				tag.setInteger("microbe_rarity", 4);
			}
			/* add event */
			item.setTagCompound(tag);
		}
		return item;
	}

	public static IMicrobe getMicrobeData(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("microbe_name")) {
				String key = tag.getString("microbe_name");
				IMicrobe ret = MainAPIManager.microbeRegister.getSpecies(key);
				if (ret != null) {
					return ret;
				}
			}
		}
		return null;
	}

	public static EnumRarity getMicrobeRarity(ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.hasTagCompound()) {
			NBTTagCompound tag = item.getTagCompound();
			if (tag.hasKey("microbe_rarity")) {
				int key = tag.getInteger("microbe_rarity");
				switch (key) {
				case 0:
					return null;
				case 1:
					return EnumRarity.COMMON;
				case 2:
					return EnumRarity.UNCOMMON;
				case 3:
					return EnumRarity.RARE;
				case 4:
					return EnumRarity.EPIC;
				default:
					return null;
				}
			}
		}
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		if (!DCUtil.isEmpty(stack) && stack.hasTagCompound()) {
			IMicrobe microbe = getMicrobeData(stack);
			EnumRarity rarity = getMicrobeRarity(stack);
			if (microbe != null) {
				tooltip.add(TextFormatting.BOLD.toString() + "=== Cultivation Data ===");
				if (rarity == null) {
					tooltip.add(TextFormatting.BOLD.toString() + I18n.format("dcs.tip.unidentified") + " " + microbe
							.getType().localize());
				} else {
					tooltip.add(TextFormatting.BOLD.toString() + I18n.format(microbe.getMicrobeItem()
							.getUnlocalizedName()));
					tooltip.add(TextFormatting.BOLD.toString() + rarity);
				}
				if (stack.getTagCompound().hasKey("microbe_days")) {
					int i = stack.getTagCompound().getInteger("microbe_days");
					tooltip.add(TextFormatting.BOLD.toString() + I18n.format("dcs.tip.incubation_days", i));
				}
				if (stack.getTagCompound().hasKey("microbe_baddays")) {
					tooltip.add(TextFormatting.BOLD.toString() + TextFormatting.RED.toString() + I18n
							.format("dcs.tip.vbnc"));
				}
			} else {
				tooltip.add(TextFormatting.BOLD.toString() + "EMPTY");
			}

		} else {
			tooltip.add(TextFormatting.BOLD.toString() + "EMPTY");
		}
	}

}
