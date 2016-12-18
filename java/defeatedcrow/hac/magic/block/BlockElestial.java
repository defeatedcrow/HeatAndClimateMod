package defeatedcrow.hac.magic.block;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockElestial extends DCSimpleBlock implements ITexturePath {

	public BlockElestial(Material m, String s) {
		super(m, s, 0, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	private static String[] names = {
			"normal" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/ores/gemblock_elestial_s";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	// debug用一時処置
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (ClimateCore.isDebug) {
			if (!player.worldObj.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
				AcvHelper.forceOpenMaster(player);
			}
			return true;
		}
		return false;
	}
}
