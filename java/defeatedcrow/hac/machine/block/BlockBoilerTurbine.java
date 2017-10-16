package defeatedcrow.hac.machine.block;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
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

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			if (!DCUtil.isEmpty(heldItem) && heldItem.getItem() instanceof IWrenchDC) {
				TileEntity tile = world.getTileEntity(pos);
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, side, hitX, hitY, hitZ);
	}

	// 設置時にはプレイヤーの方を向いている方が自然なので
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
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
				world.notifyNeighborsOfStateChange(pos, MachineInit.redbox, true);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.POWERED, false), 3);
				world.notifyNeighborsOfStateChange(pos, MachineInit.redbox, true);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MachineInit.boilerTurbine)
			return false;
		return DCState.getBool(state, DCState.POWERED);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && DCState.getBool(state, DCState.POWERED)) {
			for (int i = 0; i < 5; i++) {
				EnumFacing face = DCState.getSide(state, DCState.SIDE).getFacing().getOpposite().rotateY();
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.75D;
				double z = pos.getZ() + 0.5D;
				double dx = rand.nextDouble() * face.getFrontOffsetX() * 0.05D;
				double dy = rand.nextDouble() * 0.5D;
				double dz = rand.nextDouble() * face.getFrontOffsetZ() * 0.05D;
				Particle cloud = new ParticleCloudDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}
		}
	}

}
