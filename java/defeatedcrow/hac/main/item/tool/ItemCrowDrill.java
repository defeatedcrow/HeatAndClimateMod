package defeatedcrow.hac.main.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fluids.IFluidBlock;
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
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		int minX = (chunk.xPosition << 4) - 1;
		int minY = pos.getY() - (pos.getY() & 15);
		int minZ = (chunk.zPosition << 4) - 1;
		int maxX = minX + 17;
		int maxY = minY + 16;
		int maxZ = minZ + 17;

		Iterable<BlockPos> itr = pos.getAllInBox(new BlockPos(minX, minY, minZ), new BlockPos(maxX, maxY, maxZ));
		for (BlockPos p1 : itr) {
			if (p1.getY() < 1)
				continue;
			Block block = world.getBlockState(p1).getBlock();
			if (p1.getX() == minX || p1.getX() == maxX || p1.getZ() == minZ || p1.getZ() == maxZ) {
				Block target = world.getBlockState(p1).getBlock();
				if (target instanceof BlockLiquid || target instanceof IFluidBlock) {
					world.setBlockState(p1, Blocks.glass.getDefaultState());
				}
			} else {
				world.setBlockToAir(p1);
			}
		}

		DCLogger.debugLog("Coord: " + chunk.xPosition + ", " + chunk.zPosition);
		DCLogger.debugLog("Coord: " + minX + "-" + maxX + ", " + minY + "-" + maxY + ", " + minZ + "-" + maxZ);
		world.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.pop", 0.5F, 1.8F);
		return true;

	}

}
