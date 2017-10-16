package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLotusCandle extends BlockContainer {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.5D, 0.875D);

	public final boolean black;

	public BlockLotusCandle(String s, boolean b) {
		super(Material.GLASS);
		this.setSoundType(SoundType.GLASS);
		this.setUnlocalizedName(s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setLightLevel(0.4F);
		black = b;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (DCUtil.machCreativeTab(tab, getCreativeTabToDisplayOn()))
			list.add(new ItemStack(this, 1, 0));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return black ? new TileLotusCandleBlack() : new TileLotusCandle();
	}

	@Override
	public float getEnchantPowerBonus(World world, BlockPos pos) {
		return black ? 15 : 3;
	}

}
