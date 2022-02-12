package defeatedcrow.hac.magic.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IJewel;
import defeatedcrow.hac.api.magic.IMagicCost;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.magic.block.TileMorayLight;
import defeatedcrow.hac.magic.entity.EntityCrowDoll;
import defeatedcrow.hac.magic.entity.EntityOwlDoll;
import defeatedcrow.hac.magic.event.MagicCommonEvent;
import defeatedcrow.hac.main.api.DimCoord;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorGauntlet2 extends DCItem implements IJewel, IMagicCost {

	private final int maxMeta;

	private static String[] names = {
			"ug",
			"gb",
			"ru",
			"bw",
			"wr"
	};

	public ItemColorGauntlet2() {
		super();
		maxMeta = 4;
		this.setMaxStackSize(1);
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
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/gauntlet_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public CharmType getCharmType(int meta) {
		return CharmType.CONSTANT;
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.OFFHAND;
	}

	@Override
	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE_GREEN;
		case 1:
			return MagicColor.GREEN_BLACK;
		case 2:
			return MagicColor.RED_BLUE;
		case 3:
			return MagicColor.BLACK_WHITE;
		case 4:
			return MagicColor.WHITE_RED;
		default:
			return MagicColor.NONE;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_gauntlet2." + meta));
		if (getCost(stack) > 0) {
			float f = getCost(stack);
			tooltip.add(TextFormatting.BLUE.toString() + TextFormatting.BOLD.toString() + "=== Magic Cost ===");
			tooltip.add(TextFormatting.WHITE.toString() + DCName.getMagicCost() + ": " + String.format("%.1f F", f));
		}
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.offhand_tool"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_gauntlet2." + meta));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_gauntlet2_2." + meta));

			NBTTagCompound tag = stack.getTagCompound();
			int limit = getLimit(meta);
			Map<DimCoord, Integer> map = getPosList(tag);
			if (limit < 0) {
				tooltip.add(TextFormatting.RESET.toString() + DCName.MAGIC_PLACE_LIMIT
						.getLocalizedName() + "Unlimited");
			} else {
				if (tag != null && tag.hasKey("moraycount")) {
					int n = getCount(stack);
					limit -= n;
				} else if (tag != null && tag.hasKey("coord")) {
					if (map != null && !map.isEmpty()) {
						limit -= map.size();
					}
				}
				tooltip.add(TextFormatting.RESET.toString() + DCName.MAGIC_PLACE_LIMIT
						.getLocalizedName() + limit);
			}

			tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
			tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff2") + TextFormatting.WHITE
					.toString() + I18n.format("dcs.comment.buff.color_gauntlet2." + meta));
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.color_gauntlet2." + meta));

			if (map != null && !map.isEmpty()) {
				tooltip.add(TextFormatting.RESET.toString() + TextFormatting.BOLD.toString() + "=== Coords ===");
				int n = 1;
				for (DimCoord p : map.keySet()) {
					tooltip.add(p.toString("No" + n));
					n++;
				}
			}

		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
	}

	/* === 効果 === */

	public int getLimit(int meta) {
		switch (meta) {
		case 0:
			return 1;
		case 1:
			return -1;
		case 2:
			return 8;
		case 3:
			return 1;
		}
		return -1;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player != null && hand == EnumHand.OFF_HAND) {
			float effect = MainUtil.magicSuitEff(player);
			ItemStack stack = player.getHeldItem(EnumHand.OFF_HAND);
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();

			if (!DCUtil.isEmpty(stack) && stack.getItem() == this) {
				int meta = stack.getMetadata();
				int limit = getLimit(meta);

				// BLUE-GREEN
				if (meta == 0 && player.isSneaking()) {
					effect -= 1.0F;
					limit += MathHelper.floor(effect * 2F);
					if (!world.isRemote) {
						DimCoord coord = new DimCoord(world.provider.getDimension(), pos);
						if (hasCoord(stack, coord)) {
							if (!world.isRemote) {
								DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(pos.getX() + 0.5D, pos
										.getY() + 0.5D, pos.getZ() + 0.5D));
							}
							removeCoord(stack, player, pos);
						} else {
							if (DCUtil.playerCanUseCharm(player, stack)) {
								if (!world.isRemote) {
									DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(pos.getX() + 0.5D, pos
											.getY() + 0.5D, pos.getZ() + 0.5D));
								}
								if (addCoord(stack, player, pos, limit, false))
									DCUtil.playerConsumeCharm(player, stack);
							}
						}
					}
					world.playSound(player, pos, SoundEvents.BLOCK_NOTE_PLING, SoundCategory.BLOCKS, 0.5F, 1.5F);

					return EnumActionResult.SUCCESS;
				}
				// GREEN-BLACK
				else if (meta == 1) {
					if (DCUtil.playerCanUseCharm(player, stack)) {
						EntityCrowDoll doll = new EntityCrowDoll(world);
						int lim = MathHelper.floor(600 * effect);
						doll.setlimit(lim);
						doll.setOwnerId(player.getUniqueID());
						double fX = facing.getFrontOffsetX() * 0.25D;
						double fY = facing.getFrontOffsetY() * 0.25D;
						double fZ = facing.getFrontOffsetZ() * 0.25D;
						doll.setPositionAndRotation(pos.getX() + hitX + fX, pos
								.getY() + hitY + fY, pos.getZ() + hitZ + fZ, player.rotationYaw, 0F);
						if (!world.isRemote) {
							world.spawnEntity(doll);
						}
						if (!world.isRemote) {
							DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(pos.getX() + 0.5D, pos
									.getY() + 0.5D, pos.getZ() + 0.5D));
						}
						world.playSound(player, pos, SoundEvents.BLOCK_NOTE_GUITAR, SoundCategory.BLOCKS, 0.5F, 1.5F);
						DCUtil.playerConsumeCharm(player, stack);
					}
					return EnumActionResult.SUCCESS;
				}
				// RED-BLUE
				else if (meta == 2 && player.isSneaking()) {
					if (state.getBlock() == MagicInit.morayLamp) {
						TileEntity tile = world.getTileEntity(pos);
						if (tile instanceof TileMorayLight & ((TileMorayLight) tile).isOwnerID(player)) {
							addCount(stack, limit, -1);
							world.setBlockToAir(pos);
							world.playSound(player, pos, SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.BLOCKS, 0.5F, 1.0F);
						}
					} else {
						if (getCount(stack) < limit) {
							if (DCUtil.playerCanUseCharm(player, stack)) {
								BlockPos p1 = pos;
								ItemStack set = new ItemStack(MagicInit.morayLamp);
								if (!block.isReplaceable(world, p1)) {
									p1 = p1.offset(facing);
								}
								if (player.canPlayerEdit(p1, facing, set) && world.isAirBlock(p1)) {
									IBlockState light = MagicInit.morayLamp
											.getStateForPlacement(world, p1, facing, hitX, hitY, hitZ, 0, player, hand);

									if (!world.isRemote && world.setBlockState(p1, light, 3))
										MagicInit.morayLamp.onBlockPlacedBy(world, p1, light, player, set);
									world.playSound(player, pos, SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.BLOCKS, 0.5F, 1.0F);
									addCount(stack, limit, 1);
									DCUtil.playerConsumeCharm(player, stack);
								}
							}
						} else {
							DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.limit");
						}
					}
					return EnumActionResult.SUCCESS;
				}
				// BLACK-WHITE
				else if (meta == 3) {
					if (player.isSneaking()) {
						// spawn owl
						if (getOwlId(stack) == 0) {
							if (DCUtil.playerCanUseCharm(player, stack)) {
								EntityOwlDoll doll = new EntityOwlDoll(world);
								doll.setOwnerId(player.getUniqueID());
								double fX = facing.getFrontOffsetX() * 0.25D;
								double fY = facing.getFrontOffsetY() * 0.25D;
								double fZ = facing.getFrontOffsetZ() * 0.25D;
								doll.setPositionAndRotation(pos.getX() + hitX + fX, pos
										.getY() + hitY + fY, pos.getZ() + hitZ + fZ, 180F + player.rotationYaw, 30F);
								if (!world.isRemote) {
									world.spawnEntity(doll);
								}
								if (!world.isRemote) {
									DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(pos.getX() + 0.5D, pos
											.getY() + 0.5D, pos.getZ() + 0.5D));
								}
								world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BASS, SoundCategory.BLOCKS, 0.5F, 1.5F);
								addOwlId(stack, player, doll.getEntityId());
								DCUtil.playerConsumeCharm(player, stack);
							}
						} else {
							DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.limit");
						}
						return EnumActionResult.SUCCESS;
					}
				}
				// WHITE-RED
				else if (meta == 4) {
					BlockPos p1 = pos;
					ItemStack set = new ItemStack(MagicInit.smallLight);
					if (!block.isReplaceable(world, p1)) {
						p1 = p1.offset(facing);
					}
					if (player.canPlayerEdit(p1, facing, set) && world.isAirBlock(p1)) {
						IBlockState light = MagicInit.smallLight
								.getStateForPlacement(world, p1, facing, hitX, hitY, hitZ, 0, player, hand);

						if (!world.isRemote && world.setBlockState(p1, light, 3))
							MagicInit.smallLight.onBlockPlacedBy(world, p1, light, player, set);
						world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BELL, SoundCategory.BLOCKS, 0.5F, 1.0F);
					}
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick2(World world, EntityPlayer player, EnumHand hand) {
		if (player != null && hand == EnumHand.OFF_HAND) {
			ItemStack stack = player.getHeldItem(EnumHand.OFF_HAND);
			if (!DCUtil.isEmpty(stack)) {
				int meta = stack.getItemDamage();
				if (meta == 2 && !player.isSneaking()) {
					switchActive(stack);
					world.playSound(player, player
							.getPosition(), SoundEvents.BLOCK_NOTE_XYLOPHONE, SoundCategory.BLOCKS, 0.5F, 1.5F);
				} else if (meta == 3 && !player.isSpectator()) {
					if (world.isRemote && ClimateCore.proxy.getPlayer() != null) {
						if (!checkViewEntity()) {
							int id = getOwlId(stack);
							Entity owl = world.getEntityByID(id);
							if (owl == null) {
								// 見つからないときは登録IDの更新を試みる
								for (Entity e : world.loadedEntityList) {
									if (e instanceof EntityOwlDoll && ((EntityOwlDoll) e).isOwnerID(player)) {
										owl = e;
										removeOwlId(stack);
										addOwlId(stack, player, e.getEntityId());
										break;
									}
								}
							}
							if (owl != null) {
								BlockPos owlPos = owl.getPosition();
								setViewEntity(owl);
								world.playSound(player, player
										.getPosition(), SoundEvents.BLOCK_NOTE_BASS, SoundCategory.BLOCKS, 0.5F, 1.5F);
								MagicCommonEvent.coolTime = 20;
							}
						}
					}
				}
			}
			return new ActionResult(EnumActionResult.SUCCESS, stack);
		} else {
			return super.onItemRightClick2(world, player, hand);
		}
	}

	public static Map<DimCoord, Integer> getPosList(NBTTagCompound tag) {
		HashMap map = new HashMap<>();
		if (tag != null) {
			NBTTagList tags = tag.getTagList("coord", 10);
			if (tags != null && tags.tagCount() > 0) {
				for (int i = 0; i < tags.tagCount(); i++) {
					NBTTagCompound coord = tags.getCompoundTagAt(i);
					if (coord != null && coord.hasKey("timecount")) {
						DimCoord co = DimCoord.getCoordFromNBT(coord);
						int time = coord.getInteger("timecount");
						map.put(co, time);
					}
				}
			}
		}
		return map;
	}

	public static NBTTagCompound setPosList(NBTTagCompound tag, Map<DimCoord, Integer> map) {
		if (tag != null && map != null && !map.isEmpty()) {
			NBTTagList tags = new NBTTagList();
			for (Entry<DimCoord, Integer> e : map.entrySet()) {
				DimCoord pos = e.getKey();
				NBTTagCompound coord = new NBTTagCompound();
				pos.setNBT(coord);
				coord.setInteger("timecount", e.getValue());
				tags.appendTag(coord);
			}
			tag.setTag("coord", tags);
		} else {
			tag.removeTag("coord");
		}
		return tag;
	}

	public static boolean addCoord(ItemStack stack, EntityPlayer player, BlockPos add, int limit, boolean delOld) {
		if (!DCUtil.isEmpty(stack) && add != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			Map<DimCoord, Integer> map = getPosList(tag);
			if (map.size() >= limit) {
				if (delOld) {
					// 古い順に消す
					DimCoord del = null;
					int count = Integer.MAX_VALUE;
					for (Entry<DimCoord, Integer> e : map.entrySet()) {
						if (del == null || e.getValue() < count) {
							del = e.getKey();
							count = e.getValue();
						}
					}
					if (del != null) {
						map.remove(del);
						if (player != null) {
							DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.del2", del.toString());
						}
					}
				} else {
					DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.limit");
					return false;
				}
			}
			int time = (DCTimeHelper.getDay(player.world) * 24) + DCTimeHelper.currentTime(player.world);
			DimCoord coord = new DimCoord(player.world.provider.getDimension(), add);
			map.put(coord, time);
			stack.setTagCompound(setPosList(tag, map));

			if (player != null) {
				DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.add", coord.toString());
			}
			return true;
		}
		return false;
	}

	public static boolean removeCoord(ItemStack stack, EntityPlayer player, BlockPos del) {
		if (!DCUtil.isEmpty(stack) && del != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			DimCoord delc = new DimCoord(player.world.provider.getDimension(), del);
			Map<DimCoord, Integer> map = getPosList(tag);
			if (!map.isEmpty() && map.containsKey(delc)) {
				map.remove(delc);
				if (player != null) {
					DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.del", delc.toString());
				}
			}
			stack.setTagCompound(setPosList(tag, map));
			return true;
		}
		return false;
	}

	public static boolean hasCoord(ItemStack stack, DimCoord target) {
		if (!DCUtil.isEmpty(stack) && target != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			Map<DimCoord, Integer> map = getPosList(tag);
			if (!map.isEmpty() && map.containsKey(target)) {
				return true;
			}
		}
		return false;
	}

	public static int countCoord(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			Map<DimCoord, Integer> map = getPosList(tag);
			if (!map.isEmpty()) {
				return map.size();
			}
		}
		return 0;
	}

	public static boolean getActive(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("active")) {
				boolean b = tag.getBoolean("active");
				return b;
			}
		}
		return false;
	}

	public static ItemStack switchActive(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("active")) {
				boolean b = tag.getBoolean("active");
				tag.setBoolean("active", !b);
			} else {
				tag.setBoolean("active", true);
			}
			stack.setTagCompound(tag);
		}
		return stack;
	}

	public static int getCount(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("moraycount")) {
				int i = tag.getInteger("moraycount");
				return i;
			}
		}
		return 0;
	}

	public static ItemStack addCount(ItemStack stack, int limit, int set) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			int i = 0;
			if (tag.hasKey("moraycount")) {
				i = tag.getInteger("moraycount");
			}
			if (i + set < (limit + 1)) {
				tag.setInteger("moraycount", i + set);
			}
			stack.setTagCompound(tag);
		}
		return stack;
	}

	public static int getOwlId(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("owlid")) {
				int i = tag.getInteger("owlid");
				return i;
			}
		}
		return 0;
	}

	public static ItemStack addOwlId(ItemStack stack, EntityPlayer player, int id) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (tag.hasKey("owlid")) {
				int i = tag.getInteger("owlid");
				if (player.world.getEntityByID(i) != null) {
					DCMainPacket.sendChat(player, "dcs.comment.color_gauntlet2.limit");
					return stack;
				}
			}
			tag.setInteger("owlid", id);
			stack.setTagCompound(tag);
		}
		return stack;
	}

	public static ItemStack removeOwlId(ItemStack stack) {
		if (!DCUtil.isEmpty(stack)) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				return stack;
			}
			if (tag.hasKey("owlid")) {
				tag.removeTag("owlid");
				stack.setTagCompound(tag);
			}
		}
		return stack;
	}

	@SideOnly(Side.CLIENT)
	public void setViewEntity(Entity target) {
		if (ClimateCore.proxy.getPlayer() != null && target != null) {
			Minecraft.getMinecraft().setRenderViewEntity(target);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void removeViewEntity() {
		if (ClimateCore.proxy.getPlayer() != null) {
			EntityPlayer player = ClimateCore.proxy.getPlayer();
			Minecraft.getMinecraft().setRenderViewEntity(player);
		}
	}

	@SideOnly(Side.CLIENT)
	public static boolean checkViewEntity() {
		if (ClimateCore.proxy.getPlayer() != null) {
			if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityOwlDoll)
				return true;
		}
		return false;
	}

	@Override
	public boolean canUseMagic(EntityPlayer player, ItemStack stack) {
		return true;
	}

	@Override
	public boolean beforeConsumption(EntityPlayer player, ItemStack stack) {
		return true;
	}

	@Override
	public float getCost(ItemStack item) {
		if (!DCUtil.isEmpty(item) && CoreConfigDC.harderMagic) {
			int i = item.getItemDamage();
			float f = (float) CoreConfigDC.harderMagicCostAmount;
			if (i != 4) {
				return f * 2F;
			}
		}
		return 0;
	}

}
