package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.EnumFixedName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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

public class BlockFreezer extends BlockTorqueBase implements IHeatTile {

	public BlockFreezer(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileFreezer();
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem)) {}
		}
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos pos, BlockPos target) {
		TileEntity tile = world.getTileEntity(target);
		if (tile != null && tile instanceof TileFreezer) {
			TileFreezer ex = (TileFreezer) tile;
			if (ex.current != null) {
				DCHeatTier next = ex.current;
				return next;
			}
		}
		return DCHeatTier.NORMAL;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "1: 6.0+ torque/s");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "2: 32.0+ torque/s");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "3: 64.0+ torque/s");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "1 " + EnumFixedName.HEAT.getLocalizedName() + ": " +
					TextFormatting.BLUE.toString() + "FROSTBITE");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "2 " + EnumFixedName.HEAT.getLocalizedName() + ": " +
					TextFormatting.BLUE.toString() + "CRYOGENIC");
			tooltip.add(EnumFixedName.STAGE.getLocalizedName() + "3 " + EnumFixedName.HEAT.getLocalizedName() + ": " +
					TextFormatting.BLUE.toString() + "ABSOLUTE");
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

}
