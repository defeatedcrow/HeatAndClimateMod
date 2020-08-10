package defeatedcrow.hac.machine.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBoilerTurbine extends BlockTorqueBase {

	public BlockBoilerTurbine(String s) {
		super(Material.ROCK, s, 0);
		this.setHardness(1.5F);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBoilerTurbine();
	}

	// 見た目の更新
	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos);

			if (flag || block.getDefaultState().canProvidePower()) {
				if (flag != state.getValue(DCState.POWERED).booleanValue()) {
					world.setBlockState(pos, state.withProperty(DCState.POWERED, Boolean.valueOf(flag)), 2);
					float f = state.getValue(DCState.POWERED).booleanValue() ? 0.6F : 0.5F;
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && !DCState.getBool(state, DCState.POWERED)) {
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
				for (int i = 0; i < 5; i++) {
					EnumFacing face = DCState.getSide(state, DCState.SIDE).getFacing();
					double x = pos.getX() + 0.5D - face.getFrontOffsetX() * 0.25D - face.getFrontOffsetZ() * 0.25D;
					double y = pos.getY() + 1.0D;
					double z = pos.getZ() + 0.5D - face.getFrontOffsetZ() * 0.25D - face.getFrontOffsetX() * 0.25D;
					double dx = 0.0D;
					double dy = 0.0D;
					double dz = 0.0D;

					world.spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0.0D, dy, 0.0D, new int[0]);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.RED.toString() + "OVEN ~ UHT");
			tooltip.add(DCName.WATER_CONSUMPTION.getLocalizedName() + ": " + "1 ~ 50 mB/s");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.TORQUE.getLocalizedName() + ": 8.0F ~ 128.0F torque/s");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(DCName.DRAIN_SIDED_TANK.getLocalizedName());
			tooltip.add(DCName.TURN_OFF.getLocalizedName());
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

}
