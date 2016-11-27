package defeatedcrow.hac.machine.block;

import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBoilerTurbine extends BlockTorqueBase {

	public BlockBoilerTurbine(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBoilerTurbine();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && heldItem != null && heldItem.getItem() instanceof IWrenchDC) {
			TileEntity tile = world.getTileEntity(pos);
			// achievement
			if (!player.hasAchievement(AchievementClimate.MACHINE_CHANGE)) {
				AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_CHANGE);
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer) {
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
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

	// 見た目の更新
	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MachineInit.boilerTurbine) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, true), 3);
				world.notifyBlockOfStateChange(pos, MachineInit.redbox);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				world.notifyBlockOfStateChange(pos, MachineInit.redbox);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MachineInit.boilerTurbine)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 8;
		return meta > 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && BlockBoilerTurbine.isLit(world, pos)) {
			EnumFacing face = DCState.getSide(state, DCState.SIDE).getFacing().getOpposite().rotateY();
			double x = pos.getX() + 0.5D + face.getFrontOffsetX() * 0.75D + rand.nextDouble() * 0.15D;
			double y = pos.getY() + 0.75D + rand.nextDouble() * 0.15D;
			double z = pos.getZ() + 0.5D + face.getFrontOffsetZ() * 0.75D + rand.nextDouble() * 0.15D;

			world.spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

}
