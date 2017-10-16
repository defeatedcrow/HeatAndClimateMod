package defeatedcrow.hac.main.block.build;

import java.util.Random;

import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFenceBase extends BlockFence {

	public BlockFenceBase(String s) {
		super(Material.CLAY, MapColor.CLAY);
		this.setUnlocalizedName(s);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (DCUtil.machCreativeTab(tab, getCreativeTabToDisplayOn())) {
			list.add(new ItemStack(this, 1, 0));
		}
	}

	// 設置・破壊処理
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(state.getBlock());
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}

}
