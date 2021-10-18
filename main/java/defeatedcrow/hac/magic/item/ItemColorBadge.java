package defeatedcrow.hac.magic.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.api.magic.CharmType;
import defeatedcrow.hac.api.magic.IMagicCost;
import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.api.magic.MagicType;
import defeatedcrow.hac.config.CoreConfigDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.plugin.baubles.CharmItemBase;
import defeatedcrow.hac.core.util.DCTimeHelper;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.DimCoord;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.packet.DCMainPacket;
import defeatedcrow.hac.main.packet.MessageMagicParticle;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import defeatedcrow.hac.main.util.portal.DCDimChangeHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorBadge extends CharmItemBase implements IMagicCost {

	private final int maxMeta;

	/*
	 * U: 登録地ワープ
	 * G: 死亡回避
	 * R: 一括破壊
	 * B: 偽装攻撃
	 * W: 修復
	 */
	private static String[] names = {
			"u1",
			"g1",
			"r1",
			"b1",
			"w1"
	};

	public ItemColorBadge() {
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
	public String getTexPath(int meta, boolean f) {
		String s = "items/magic/badge_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	public CharmType getCharmType(int meta) {
		switch (meta) {
		case 0:
			return CharmType.KEY;
		case 1:
			return CharmType.SPECIAL;
		case 2:
			return CharmType.TOOL;
		case 3:
			return CharmType.SPECIAL;
		case 4:
			return CharmType.CONSTANT;
		default:
			return CharmType.SPECIAL;
		}
	}

	@Override
	public MagicType getType(int meta) {
		return MagicType.BADGE;
	}

	@Override
	public MagicColor getColor(int meta) {
		switch (meta) {
		case 0:
			return MagicColor.BLUE;
		case 1:
			return MagicColor.GREEN;
		case 2:
			return MagicColor.RED;
		case 3:
			return MagicColor.BLACK;
		case 4:
			return MagicColor.WHITE;
		default:
			return MagicColor.NONE;
		}
	}

	@Override
	public float reduceDamage(DamageSource source, ItemStack charm) {
		return 0;
	}

	@Override
	public boolean onDiffence(DamageSource source, EntityLivingBase owner, float damage, ItemStack charm) {
		return false;
	}

	@Override
	public float increaceDamage(EntityLivingBase target, DamageSource source, ItemStack charm) {
		return 1F;
	}

	@Override
	public boolean onAttacking(EntityLivingBase owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return false;
	}

	@Override
	public boolean onPlayerAttacking(EntityPlayer owner, EntityLivingBase target, DamageSource source, float damage,
			ItemStack charm) {
		return false;
	}

	@Override
	public boolean onToolUsing(EntityLivingBase owner, BlockPos pos, IBlockState state, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.RED && owner instanceof EntityPlayer && owner.isSneaking()) {
			EntityPlayer player = (EntityPlayer) owner;
			if (!player.world.isRemote && state != null) {
				int c = 1 + charm.getCount() * 3;
				c = MathHelper.floor(MainUtil.magicSuitEff(owner) * c);
				BlockSet set = new BlockSet(state.getBlock(), state.getBlock().getMetaFromState(state));
				if (MainCoreConfig.disables.contains(set)) {
					return false;
				}
				// 一括破壊
				ItemStack hold = player.getHeldItemMainhand();
				BlockPos min = pos.add(-c, -c, -c);
				BlockPos max = pos.add(c, c, c);
				Iterable<BlockPos> itr = pos.getAllInBox(min, max);
				boolean flag = false;
				for (BlockPos p : itr) {
					if (p.getY() < 0 || p.getY() > 255 || p.equals(pos)) {
						continue;
					}
					if (!player.canPlayerEdit(p, EnumFacing.UP, charm))
						continue;
					IBlockState target = player.world.getBlockState(p);
					if (target == null || !target.getBlock().canHarvestBlock(player.world, p, player))
						continue;
					if (target.equals(state) && target.getBlock().getMetaFromState(target) == state.getBlock()
							.getMetaFromState(state) && !target.getBlock().hasTileEntity(target)) {
						target.getBlock().harvestBlock(player.world, player, p, target, null, hold);
						player.world.setBlockToAir(p);
					}
				}
			}
		}
		return false;
	}

	@Override
	public void constantEffect(EntityLivingBase owner, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.WHITE) {
			ItemStack off = owner.getHeldItemOffhand();
			if (!DCUtil.isEmpty(off) && off.getItem().isDamageable()) {
				long time = DCTimeHelper.time(owner.world);
				int f = MathHelper.ceil(MainUtil.magicSuitEff(owner) * charm.getCount() * 2);
				if (f > 16)
					f = 16;
				int i = (128 / f) - 1;
				if ((time & i) == 0) {
					int dam = off.getItemDamage();
					if (dam > 0) {
						dam -= charm.getCount();
						off.setItemDamage(dam);
					}
				}
			}
		}
	}

	@Override
	public boolean onUsing(EntityPlayer owner, ItemStack charm) {
		if (getColor(charm.getItemDamage()) == MagicColor.BLUE) {
			if (!owner.world.isRemote && owner.world instanceof WorldServer) {
				DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(owner.posX, owner.posY, owner.posZ));
				int dim = owner.world.provider.getDimension();
				NBTTagCompound tag = charm.getTagCompound();
				if (tag != null && tag.hasKey("dcs.charm.dimname")) {
					DimCoord coord = DimCoord.getCoordFromNBT(tag);
					if (coord != null)
						if (coord.getDim() != dim) {
							DCDimChangeHelper.INSTANCE.addCoord(owner, coord.getDim(), coord.getPos());
						} else {
							owner.setPositionAndUpdate(coord.x + 0.5D, coord.y + 0.5D, coord.z + 0.5D);
							owner.fallDistance = 0.0F;
						}
				}
			}
		}
		return false;
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack stack = player.getHeldItem(hand);
			if (!world.isAirBlock(pos) && !DCUtil.isEmpty(stack) && stack.getItem() == this) {
				int meta = stack.getMetadata();
				if (!world.isRemote) {
					if (meta == 0) {
						if (pos.getY() > 0 && pos.getY() < 254 && world.isAirBlock(pos.up()) && world.isAirBlock(pos
								.up(2))) {
							NBTTagCompound tag = stack.getTagCompound();
							if (tag == null) {
								tag = new NBTTagCompound();
							}
							String dimName = world.provider.getDimensionType().getName();
							int dim = world.provider.getDimension();
							int x = pos.getX();
							int y = pos.getY() + 1;
							int z = pos.getZ();
							DimCoord coord = new DimCoord(dim, x, y, z);
							tag.setString("dcs.charm.dimname", dimName);
							coord.setNBT(tag);
							stack.setTagCompound(tag);
							DCMainPacket.INSTANCE.sendToAll(new MessageMagicParticle(pos.getX() + 0.5D, pos
									.getY() + 0.5D, pos.getZ() + 0.5D));
							return EnumActionResult.SUCCESS;
						}
					}
				}
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public boolean isActive(ItemStack charm) {
		return true;
	}

	@Override
	public void setActive(ItemStack charm, boolean flag) {}

	@Override
	public ItemStack consumeCharmItem(ItemStack stack) {
		return stack;
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
			if (i == 0) {
				return f * 2F;
			}
			if (i == 1 || i == 2 || i == 3) {
				return f;
			}
		}
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation2(ItemStack stack, @Nullable World world, List<String> tooltip) {
		String s = "";
		int meta = stack.getMetadata();
		if (meta == 0 || meta == 2) {
			tooltip.add(TextFormatting.BOLD.toString() + "PLAYER ONLY");
		}
		tooltip.add(TextFormatting.AQUA.toString() + I18n.format("dcs.tip.color_badge." + meta));
		if (getCost(stack) > 0) {
			float f = getCost(stack);
			tooltip.add(TextFormatting.BLUE.toString() + TextFormatting.BOLD.toString() + "=== Magic Cost ===");
			tooltip.add(TextFormatting.WHITE.toString() + DCName.getMagicCost() + ": " + String.format("%.1f F", f));
		}
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.allcharm"));
			tooltip.add(TextFormatting.YELLOW.toString() + I18n.format("dcs.comment.color_badge." + meta));
			if (meta == 2) {
				tooltip.add(TextFormatting.GOLD.toString() + TextFormatting.BOLD.toString() + "=== Boost ===");
				tooltip.add(TextFormatting.GOLD.toString() + I18n.format("dcs.tip.buff1") + "," + I18n
						.format("dcs.tip.buff2") + TextFormatting.WHITE.toString() + I18n
								.format("dcs.comment.buff.color_badge." + meta));

			}
			tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.BOLD.toString() + "============");
			tooltip.add(TextFormatting.GRAY.toString() + I18n.format("dcs.comment.flavor.color_badge." + meta));
		} else {
			tooltip.add(TextFormatting.RESET.toString() + I18n.format("dcs.tip.shift"));
		}
		NBTTagCompound tag = stack.getTagCompound();
		DimCoord coord = DimCoord.getCoordFromNBT(tag);
		if (coord != null && tag.hasKey("dcs.charm.dimname")) {
			String warpDim = tag.getString("dcs.charm.dimname");
			tooltip.add(TextFormatting.RESET + " ");
			tooltip.add("Registered Coord");
			tooltip.add(coord.toString(warpDim));
		}
		if (coord != null && tag.hasKey("dcs.portal.dimname")) {
			String warpDim = tag.getString("dcs.portal.dimname");
			tooltip.add(TextFormatting.RESET + " ");
			tooltip.add("Last Portal Coord");
			tooltip.add(coord.toString(warpDim));
		}
	}

	public enum DropType {
		NORMAL,
		SILK,
		SMELTING;
	}

}
