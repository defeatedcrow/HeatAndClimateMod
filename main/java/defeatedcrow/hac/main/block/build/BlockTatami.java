package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCFacelessTileBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTatami extends DCFacelessTileBlock implements ITexturePath {

	public BlockTatami(Material m, String s) {
		super(m, s, 5, false);
		this.setTickRandomly(false);
		this.setHardness(1.0F);
		this.setResistance(30.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public int getMaxMeta() {
		return 5;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/carpet/tatami_normal";
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	// 表向きはメタなし

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
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
		return Item.getItemFromBlock(this);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTatami();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.AQUA.toString() + DCName.COLOR_CHANGE_TARGET
				.getLocalizedName());
	}
}
