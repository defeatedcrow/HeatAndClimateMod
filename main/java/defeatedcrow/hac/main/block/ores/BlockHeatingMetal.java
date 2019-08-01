package defeatedcrow.hac.main.block.ores;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.BlockSet;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.IHeatTreatment;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHeatingMetal extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockHeatingMetal(Material m, String s, int max) {
		super(m, s, max, true);
		this.setTickRandomly(true);
		this.setHardness(1.5F);
		this.setResistance(15.0F);
	}

	@Override
	public int tickRate(World world) {
		return 100;
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		if (clm != null) {
			DCHeatTier heat = clm.getHeat();
			DCHumidity hum = clm.getHumidity();
			DCAirflow air = clm.getAirflow();
			int meta = this.damageDropped(state);
			ItemStack check = new ItemStack(this, 1, meta);
			IHeatTreatment recipe = MainAPIManager.heatTreatmentRegister.getRecipe(check);
			if (recipe != null) {
				ItemStack output = recipe.getCurrentOutput(check, clm).getResult();
				if (!DCUtil.isEmpty(output) && output.getItem() instanceof ItemBlock) {
					Block ret = ((ItemBlock) output.getItem()).getBlock();
					IBlockState retS = ret.getStateFromMeta(output.getMetadata());
					if (world.setBlockState(pos, retS, 2)) {
						world.notifyNeighborsOfStateChange(pos, ret, true);

						// 効果音
						if (playSEOnChanging(meta)) {
							world.playSound(null, pos, getSE(meta), SoundCategory.BLOCKS, 0.8F, 2.0F);
							DCLogger.debugLog("Heat Treatment: " + output.getDisplayName());
						}
						return true;
					}
				}
			}
		}
		return super.onClimateChange(world, pos, state, clm);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	private static String[] names = {
		"steel_heating",
		"steel_cooling",
		"steel_fail",
		"sus_heating",
		"sus_cooling",
		"sus_fail",
		"titanium_heating",
		"titanium_cooling",
		"titanium_fail",
		"toolsteel_heating",
		"toolsteel_cooling",
		"toolsteel_fail" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/metal_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		return !DCUtil.isEmpty(item) && (item.getItem() instanceof ItemPickaxe || item.getItem() instanceof ItemSpade);
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 1;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		NonNullList<ItemStack> list = NonNullList.create();
		this.getDrops(list, world, pos, state, 0);
		for (ItemStack item : list) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntity(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}

	// climate

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote && state != null && state.getBlock() != null) {
			IClimate clm = this.onUpdateClimate(world, pos, state);
			if (!this.onClimateChange(world, pos, state, clm)) {
				BlockSet set = getFailureProduct(state);
				if (set != null) {
					IBlockState retS = set.getState();
					world.setBlockState(pos, retS, 2);
					world.notifyNeighborsOfStateChange(pos, set.block, true);
					if (playSEOnChanging(0)) {
						world.playSound(null, pos, getSE(0), SoundCategory.BLOCKS, 0.8F, 2.0F);
						DCLogger.debugLog("Heat Treatment Failed");
					}
				}
				world.scheduleUpdate(pos, this, this.tickRate(world) + rand.nextInt(21));
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && inWater(world, pos)) {
			int m = DCState.getInt(state, DCState.TYPE16);
			if (m == 0 || m == 3 || m == 6 || m == 9) {
				for (int i = 0; i < 5; i++) {
					double x = pos.getX() + rand.nextDouble() * 1D;
					double y = pos.getY() + 1.05D;
					double z = pos.getZ() + rand.nextDouble() * 1D;
					world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, x, y, z, 0.0D, 0.05D, 0.0D, new int[0]);
				}
			}
		}
	}

	boolean inWater(World world, BlockPos pos) {
		if (world != null && pos != null)
			for (EnumFacing f : EnumFacing.VALUES) {
				if (world.getBlockState(pos.offset(f)).getMaterial() == Material.WATER) {
					return true;
				}
			}
		return false;
	}

	public static BlockSet getFailureProduct(IBlockState state) {
		int i = DCState.getInt(state, DCState.TYPE16);
		switch (i) {
		case 0:
		case 1:
			return new BlockSet(MainInit.heatedMetalBlock, 2);
		case 3:
		case 4:
			return new BlockSet(MainInit.heatedMetalBlock, 5);
		case 6:
		case 7:
			return new BlockSet(MainInit.heatedMetalBlock, 8);
		case 9:
		case 10:
			return new BlockSet(MainInit.heatedMetalBlock, 11);
		}
		return null;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return meta == 0 || meta == 3 || meta == 6 || meta == 9 ? 10 : 0;
	}
}
