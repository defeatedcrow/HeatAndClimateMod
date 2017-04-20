package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.INameSuffix;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDesiccantPackage extends Block implements ITexturePath, INameSuffix, IHumidityTile {

	protected static final AxisAlignedBB AABB_MAIN = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.1875D, 0.875D);

	// Type上限
	public final int maxMeta;

	public BlockDesiccantPackage(String s) {
		super(Material.CLOTH);
		this.setUnlocalizedName(s);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
		this.setSoundType(SoundType.CLOTH);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.TYPE4, 0));
		this.setTickRandomly(true);
		this.maxMeta = 3;
	}

	@Override
	public int tickRate(World world) {
		return 100;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		int type = DCState.getInt(state, DCState.TYPE4);
		if (!world.isRemote && type > -1 && type != 3) {
			DCHumidity hum = ClimateAPI.calculator.getHumidity(world, pos);
			if (hum == DCHumidity.UNDERWATER) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 3));
			} else {
				if (world.rand.nextInt(6 - hum.getID()) == 0) {
					world.setBlockState(pos, state.withProperty(DCState.TYPE4, type + 1));
				}
			}
		}
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_MAIN;
	}

	@Override
	public int damageDropped(IBlockState state) {
		int i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}
		return i;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		if (i > maxMeta) {
			i = maxMeta;
		}
		IBlockState state = this.getDefaultState().withProperty(DCState.TYPE4, i);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}
		return i;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.TYPE4
		});
	}

	private static String[] names = {
			"red",
			"yellow",
			"green",
			"blue"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta > getMaxMeta()) {
			meta = getMaxMeta();
		}
		String s = "blocks/build/desiccant_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public DCHumidity getHumdiity(World world, BlockPos targrt, BlockPos thisTile) {
		IBlockState state = world.getBlockState(thisTile);
		int type = DCState.getInt(state, DCState.TYPE4);
		return type == 3 ? DCHumidity.NORMAL : DCHumidity.DRY;
	}

}
