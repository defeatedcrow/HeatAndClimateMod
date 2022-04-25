package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEntityPanel extends BlockContainerDC {
	public final int maxMeta;

	public BlockEntityPanel(String s) {
		super(Material.GROUND, s);
		this.setHardness(1.5F);
		this.setResistance(15.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.TYPE4, 0).withProperty(DCState.POWERED, false));
		this.maxMeta = 3;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			this.changeTypeState(world, pos);
			world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.8F, 2.0F);
		}
		return true;
	}

	public static void changeTypeState(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MachineInit.entityPanel) {
			int t = DCState.getInt(state, DCState.TYPE4);
			if (t == 3) {
				if (!world.isRemote) {
					world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 3);
				}
			} else {
				if (!world.isRemote) {
					world.setBlockState(pos, state.withProperty(DCState.TYPE4, t + 1), 3);
				}
			}
		}
	}

	public static void changePowerState(World world, BlockPos pos, boolean b) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MachineInit.entityPanel) {
			boolean m = DCState.getBool(state, DCState.POWERED);
			if (!world.isRemote) {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, b), 3);
			}
			if (m != b) {
				for (EnumFacing facing : EnumFacing.values()) {
					world.notifyNeighborsOfStateChange(pos.offset(facing), MachineInit.entityPanel, false);
				}
			}
		}
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return state.getValue(DCState.POWERED) ? 15 : 0;
	}

	@Override
	public int getStrongPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return state.getValue(DCState.POWERED) ? 15 : 0;
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPanel();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		if (i > maxMeta) {
			i = maxMeta;
		}
		boolean f = (meta & 8) > 0;
		IBlockState state = this.getDefaultState().withProperty(DCState.TYPE4, i);
		state = state.withProperty(DCState.POWERED, f);
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		int f = 0;
		i = state.getValue(DCState.TYPE4);
		if (i > maxMeta) {
			i = maxMeta;
		}
		f = state.getValue(DCState.POWERED) ? 8 : 0;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.POWERED,
				DCState.TYPE4
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(DCName.RIGHT_CLICK.getLocalizedName() + ": " + I18n.format("dcs.tip.entitypanel"));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
