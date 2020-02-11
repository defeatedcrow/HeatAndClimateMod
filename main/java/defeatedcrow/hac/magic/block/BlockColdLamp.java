package defeatedcrow.hac.magic.block;

import java.util.Random;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.main.block.build.BlockOilLamp;
import defeatedcrow.hac.main.client.particle.ParticleOrb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColdLamp extends BlockOilLamp implements IHeatTile {

	public BlockColdLamp(String s) {
		super(s);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.5D;
			double z = pos.getZ() + 0.5D;
			double dx = 0D;
			double dy = 0.01D;
			double dz = 0D;
			Particle cloud = new ParticleOrb.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos targrt, BlockPos thisTile) {
		return DCHeatTier.COOL;
	}

}
