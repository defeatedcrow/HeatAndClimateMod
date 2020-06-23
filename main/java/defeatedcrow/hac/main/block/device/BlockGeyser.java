package defeatedcrow.hac.main.block.device;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGeyser extends BlockTorqueBase implements IHeatTile {

	public BlockGeyser(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 4);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileGeyser();
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos targrt, BlockPos thisTile) {
		return DCHeatTier.OVEN;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing));
		return state;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.NON_POWERED.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add("Steam: 0 ~ 100 mB/s");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.RED.toString() + "OVEN");
			tooltip.add(DCName.AIR.getLocalizedName() + ": " + TextFormatting.AQUA.toString() + "WIND");
			tooltip.add(DCName.RANGE.getLocalizedName() + ": " + I18n.format("dcs.tip.forward_only"));
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.geyser"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this) {
			int c = ClimateMain.proxy.getParticleCount();
			EnumSide side = DCState.getSide(state, DCState.SIDE);
			if (side == null)
				side = EnumSide.UP;
			if (ClimateMain.proxy.getParticleCount() > 0) {
				for (int i = 0; i < ClimateMain.proxy.getParticleCount() / 2; i++) {
					double x = pos.getX() + 0.25D + rand.nextDouble() * 0.5D;
					double y = pos.getY() + 0.25D + rand.nextDouble() * 0.5D;
					double z = pos.getZ() + 0.25D + rand.nextDouble() * 0.5D;
					double s = 0.2D + rand.nextDouble() * 0.2D;
					double dx = s * side.face.getFrontOffsetX();
					double dy = s * side.face.getFrontOffsetY();
					double dz = s * side.face.getFrontOffsetZ();
					Particle cloud = new ParticleCloudDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
				}
			}
		}
	}

}
