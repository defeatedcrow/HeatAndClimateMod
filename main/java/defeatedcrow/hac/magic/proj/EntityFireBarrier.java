package defeatedcrow.hac.magic.proj;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.damage.DamageSourceClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFireBarrier extends EntityMobBarrier {

	/* コンストラクタ */

	public EntityFireBarrier(World worldIn) {
		super(worldIn);
		this.setSize(1.0F, 1.0F);
	}

	public EntityFireBarrier(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	public EntityFireBarrier(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		this(worldIn, posX, posY, posZ);
		if (player != null)
			this.rotationYaw = player.rotationYaw;
	}

	protected int cooltime() {
		return 20;
	}

	protected void doBarrierEffect() {
		super.doBarrierEffect();
		if (count == 20) {
			// バリア処理
			int sx = this.getPosition().getX() - 8;
			int sy = this.getPosition().getY() - 2;
			int sz = this.getPosition().getZ() - 8;
			int mx = this.getPosition().getX() + 8;
			int my = this.getPosition().getY() + 2;
			int mz = this.getPosition().getZ() + 8;
			Iterable<BlockPos> itr = BlockPos.getAllInBox(sx, sy, sz, mx, my, mz);
			for (BlockPos p1 : itr) {
				IBlockState state = world.getBlockState(p1);
				if (state.getBlock() == Blocks.AIR || state.getMaterial() == Material.AIR)
					continue;

				int meta = state.getBlock().damageDropped(state);
				ItemStack check = new ItemStack(state.getBlock(), 1, meta);
				IClimate clm = ClimateAPI.register
						.getClimateFromParam(DCHeatTier.KILN, DCHumidity.NORMAL, DCAirflow.TIGHT);
				IClimateSmelting recipe = RecipeAPI.registerSmelting.getRecipe(clm, check);
				if (recipe == null) {
					clm = ClimateAPI.register.getClimateFromParam(DCHeatTier.UHT, DCHumidity.NORMAL, DCAirflow.TIGHT);
					recipe = RecipeAPI.registerSmelting.getRecipe(clm, check);
				}
				if (recipe != null && recipe.additionalRequire(world, p1)) {
					ItemStack output = recipe.getOutput();
					if (!DCUtil.isEmpty(output) && output.getItem() instanceof ItemBlock) {
						Block ret = ((ItemBlock) output.getItem()).getBlock();
						IBlockState retS = ret.getStateFromMeta(output.getMetadata());
						if (world.setBlockState(p1, retS, 2)) {
							world.notifyNeighborsOfStateChange(p1, ret, true);
							// 効果音
							world.playSound(null, p1, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 2.0F);
							DCLogger.debugLog("Smelting! " + output.getDisplayName());
						}
					}
				}
			}
		}
	}

	protected void collideWithEntity(Entity entity) {
		if (entity instanceof FoodEntityBase) {
			FoodEntityBase food = (FoodEntityBase) entity;
			food.setRAW(false);
			food.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
		} else if (entity instanceof EntityLivingBase) {
			EntityLivingBase living = (EntityLivingBase) entity;
			living.attackEntityFrom(DamageSourceClimate.climateHeatDamage, 4.0F);
		}
	}

	protected int[] color() {
		return new int[] {
				255,
				100,
				50
		};
	}

}
