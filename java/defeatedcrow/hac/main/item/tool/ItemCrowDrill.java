package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCrowDrill extends DCItem {

	public ItemCrowDrill() {
		super();
		this.setMaxDamage(192);
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	@Override
	public int getMaxMeta() {
		return 0;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/crow_drill";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public String[] getNameSuffix() {
		String[] s = {
				"normal"
		};
		return s;
	}

	// criative 穴掘り機能
	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		// creativeチェック
		ItemStack stack = player.getHeldItem(hand);
		if (player == null || (!player.capabilities.isCreativeMode && !ClimateCore.isDebug))
			return EnumActionResult.PASS;

		boolean ore = false;
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		if (tag.hasKey("dcs_oredig")) {
			ore = tag.getBoolean("dcs_oredig");
		}

		if (player.isSneaking()) {
			if (!world.isRemote) {
				tag.setBoolean("dcs_oredig", !ore);
				stack.setTagCompound(tag);
			}
			world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
			return EnumActionResult.SUCCESS;
		}

		Chunk chunk = world.getChunkFromBlockCoords(pos);
		int minX = (chunk.xPosition << 4) - 1;
		int minY = pos.getY() - (pos.getY() & 15);
		int minZ = (chunk.zPosition << 4) - 1;
		int maxX = minX + 17;
		int maxY = minY + 17;
		int maxZ = minZ + 17;

		Iterable<BlockPos> itr = pos.getAllInBox(new BlockPos(minX, minY, minZ), new BlockPos(maxX, maxY, maxZ));
		for (BlockPos p1 : itr) {
			if (p1.getY() < 1) {
				continue;
			}
			if (p1.getX() == minX || p1.getX() == maxX || p1.getZ() == minZ || p1.getZ() == maxZ || p1.getY() == maxY) {
				Block target = world.getBlockState(p1).getBlock();
				if (target instanceof BlockLiquid || target instanceof IFluidBlock) {
					world.setBlockState(p1, Blocks.GLASS.getDefaultState());
				}
			} else {
				if (ore && !world.isAirBlock(p1)) {
					Block target = world.getBlockState(p1).getBlock();
					int meta = target.getMetaFromState(world.getBlockState(p1));
					ItemStack check = new ItemStack(target, 1, meta);
					if (!DCUtil.isEmpty(check) && !isTarget(check)) {
						continue;
					} else if (target instanceof BlockContainer) {
						continue;
					}
				}
				world.setBlockToAir(p1);
			}
		}

		// DCLogger.debugLog("Coord: " + chunk.xPosition + ", " + chunk.zPosition);
		// DCLogger.debugLog("Coord: " + minX + "-" + maxX + ", " + minY + "-" + maxY + ", " + minZ
		// + "-" + maxZ);
		world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
		return EnumActionResult.SUCCESS;

	}

	private boolean isTarget(ItemStack item) {
		int[] ids = OreDictionary.getOreIDs(item);
		if (ids != null) {
			for (int i : ids) {
				if (OreDictionary.getOreName(i).contains("ore"))
					return false;
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag = new NBTTagCompound();
		}
		if (tag.hasKey("dcs_oredig"))
			return tag.getBoolean("dcs_oredig");
		return false;
	}

}
