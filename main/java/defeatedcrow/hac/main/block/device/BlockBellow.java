package defeatedcrow.hac.main.block.device;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.DCName;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBellow extends BlockTorqueBase implements IAirflowTile {

	public BlockBellow(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBellow();
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			TileEntity tile = world.getTileEntity(pos);
			ItemStack held = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(held) && held.getItem() instanceof IWrenchDC) {
				// achievement
			} else if (tile != null && tile instanceof TileBellow) {
				TileBellow crank = ((TileBellow) tile);
				float add = crank.currentTorque + 1.0F;
				if (add > crank.maxTorque()) {
					add = crank.maxTorque();
				}
				crank.currentTorque = add;
				return true;
			}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public DCAirflow getAirflow(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileBellow) {
			TileBellow fan = (TileBellow) tile;
			EnumFacing face = fan.getBaseSide();
			boolean active = isActive(face, pos, target);
			if (active) {
				float torque = fan.getCurrentTorque();
				if (torque > 0.0F) {
					return DCAirflow.WIND;
				}
			}
		}
		return DCAirflow.TIGHT;
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer != null) {
			EnumFacing face = placer.getHorizontalFacing();
			if (placer.rotationPitch < -75.0F) {
				face = EnumFacing.UP;
			} else if (placer.rotationPitch > 75.0F) {
				face = EnumFacing.DOWN;
			}
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(face));
		} else {
			state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing));
		}
		return state;
	}

	boolean isActive(EnumFacing face, BlockPos to, BlockPos from) {
		if (to.equals(from)) {
			return true;
		}
		switch (face) {
		case DOWN:
			return to.up().equals(from);
		case UP:
			return to.down().equals(from);
		case NORTH:
			return to.south().equals(from);
		case SOUTH:
			return to.north().equals(from);
		case WEST:
			return to.east().equals(from);
		case EAST:
			return to.west().equals(from);
		default:
			return false;

		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.TORQUE.getLocalizedName() + ": 1.0F /s or " +
					DCName.RIGHT_CLICK.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.AIR.getLocalizedName() + ": " + TextFormatting.AQUA.toString() + "WIND");
			tooltip.add(DCName.RANGE.getLocalizedName() + ": " + I18n.format("dcs.tip.forward_only"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
