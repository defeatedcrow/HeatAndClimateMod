package defeatedcrow.hac.main.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;

public class ItemCrowDrill extends DCItem {

	public ItemCrowDrill() {
		super();
		this.setMaxDamage(192);
		this.setMaxStackSize(1);
		this.isFull3D();
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
		String[] s = { "normal" };
		return s;
	}

	// criative 穴掘り機能
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY,
			float hitZ) {
		// creativeチェック
		if (player == null || (!player.capabilities.isCreativeMode && !ClimateCore.isDebug)) {
			return true;
		}

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
			world.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.pop", 0.5F, 1.8F);
			return true;
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
			if (p1.getY() < 1)
				continue;
			if (p1.getX() == minX || p1.getX() == maxX || p1.getZ() == minZ || p1.getZ() == maxZ || p1.getY() == maxY) {
				Block target = world.getBlockState(p1).getBlock();
				if (target instanceof BlockLiquid || target instanceof IFluidBlock) {
					world.setBlockState(p1, Blocks.glass.getDefaultState());
				}
			} else {
				if (ore && !world.isAirBlock(p1)) {
					Block target = world.getBlockState(p1).getBlock();
					int meta = target.getDamageValue(world, p1);
					ItemStack check = new ItemStack(target, 1, meta);
					if (check != null && check.getItem() != null && !isTarget(check)) {
						continue;
					} else if (target instanceof BlockContainer) {
						continue;
					}
				}
				world.setBlockToAir(p1);
			}
		}

		DCLogger.debugLog("Coord: " + chunk.xPosition + ", " + chunk.zPosition);
		DCLogger.debugLog("Coord: " + minX + "-" + maxX + ", " + minY + "-" + maxY + ", " + minZ + "-" + maxZ);
		world.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.pop", 0.5F, 1.8F);
		return true;

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
		if (tag.hasKey("dcs_oredig")) {
			return tag.getBoolean("dcs_oredig");
		}
		return false;
	}

}
