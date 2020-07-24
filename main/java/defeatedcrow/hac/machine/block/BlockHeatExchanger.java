package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatCanceler;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHeatExchanger extends BlockTorqueBase implements IHeatTile, IHeatCanceler {

	public BlockHeatExchanger(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileHeatExchanger();
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileHeatExchanger) {
			TileHeatExchanger ex = (TileHeatExchanger) tile;
			EnumFacing face = ex.getBaseSide();
			if (ex.current != null) {
				int active = isActive(face, pos, target);
				DCHeatTier cur = ex.current.getHeat();
				float torque = ex.getCurrentTorque();
				if (torque >= 31.5F) {
					active *= 2;
				} else if (torque < 5.5F) {
					active = 0;
				}
				DCHeatTier next = cur.addTier(active);
				return next;
			}
		}
		return DCHeatTier.NORMAL;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			if (MainUtil.isHeldWrench(player, hand)) {
				EnumSide current = DCState.getSide(state, DCState.SIDE);
				EnumSide next = MainUtil.getRotatedSide(current, false);
				world.setBlockState(pos, state.withProperty(DCState.SIDE, next));
				return true;
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		// achievement
		if (placer != null && !placer.world.isRemote && placer instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) placer;
		}
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			if (placer.rotationPitch < -75.0F) {
				face = EnumFacing.UP;
			} else if (placer.rotationPitch > 75.0F) {
				face = EnumFacing.DOWN;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite()));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		}
		return state;
	}

	int isActive(EnumFacing face, BlockPos to, BlockPos from) {
		if (to.equals(from))
			return 1;
		switch (face) {
		case DOWN:
			if (to.getY() < from.getY())
				return -1;
			else
				return 1;
		case UP:
			if (to.getY() > from.getY())
				return -1;
			else
				return 1;
		case NORTH:
			if (to.getZ() < from.getZ())
				return -1;
			else
				return 1;
		case SOUTH:
			if (to.getZ() > from.getZ())
				return -1;
			else
				return 1;
		case WEST:
			if (to.getX() < from.getX())
				return -1;
			else
				return 1;
		case EAST:
			if (to.getX() > from.getX())
				return -1;
			else
				return 1;
		default:
			return 1;

		}
	}

	@Override
	public boolean isActive(IBlockState state) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.STAGE.getLocalizedName() + "1: 6.0+ torque/s");
			tooltip.add(DCName.STAGE.getLocalizedName() + "2: 32.0+ torque/s");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.STAGE.getLocalizedName() + "1 " + DCName.HEAT
					.getLocalizedName() + ": " + TextFormatting.RED.toString() + "Tier ±1");
			tooltip.add(DCName.STAGE.getLocalizedName() + "2 " + DCName.HEAT
					.getLocalizedName() + ": " + TextFormatting.RED.toString() + "Tier ±2");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.exchanger"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
