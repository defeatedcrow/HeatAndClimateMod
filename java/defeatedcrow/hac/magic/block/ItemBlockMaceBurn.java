package defeatedcrow.hac.magic.block;

import java.util.List;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceBurn extends ItemBlockMace {

	public ItemBlockMaceBurn(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (stack != null && player != null) {
			boolean hasAcv = AcvHelper.hasMagicMaster(player);
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					// 自身の炎上
					player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 3600, 0));
					if (world instanceof WorldServer) {
						((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY,
								player.posZ, 16, 0.75D, 0.75D, 0.75D, 0.5D, new int[0]);
					}

					if (player.isSneaking()) {
						// 精錬
						IClimate clm = ClimateAPI.register.getClimateFromParam(DCHeatTier.SMELTING, DCHumidity.NORMAL,
								DCAirflow.TIGHT);
						// 5x5x3 の範囲
						BlockPos pos = player.getPosition();
						BlockPos min = new BlockPos(pos.add(-3, -2, -3));
						BlockPos max = new BlockPos(pos.add(3, 3, 3));
						Iterable<BlockPos> itr = pos.getAllInBox(min, max);
						for (BlockPos p1 : itr) {
							IBlockState st = world.getBlockState(p1);
							int meta = st.getBlock().damageDropped(st);
							if (world.isAirBlock(p1) || st.getMaterial() == Material.WATER
									|| st.getBlock() == Blocks.DIRT || st.getBlock() == Blocks.GRASS) {
								continue;
							}
							ItemStack target = new ItemStack(st.getBlock(), 1, meta);
							IClimateSmelting recipe = RecipeAPI.registerSmelting.getRecipe(clm, target);
							if (recipe != null) {
								ItemStack ret = recipe.getOutput();
								if (ret != null) {
									EntityItem drop = new EntityItem(world, p1.getX() + 0.5D, p1.getY() + 0.5D,
											p1.getZ() + 0.5D, ret);
									if (ret.getItem() instanceof ItemBlock) {
										Block put = ((ItemBlock) ret.getItem()).getBlock();
										IBlockState next = put.getStateFromMeta(ret.getItemDamage());
										world.setBlockState(p1, next);
										world.notifyBlockOfStateChange(p1, put);
									} else {
										world.setBlockToAir(p1);
										drop.motionY = 0.025D;
										world.spawnEntityInWorld(drop);
									}
									if (world instanceof WorldServer) {
										((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, p1.getX() + 0.5D,
												p1.getY() + 0.5D, p1.getZ() + 0.5D, 8, 0.75D, 0.75D, 0.75D, 0.5D,
												new int[0]);
									}
								}
							} else {
								ItemStack burnt = FurnaceRecipes.instance().getSmeltingResult(target);
								if (burnt != null) {
									EntityItem drop2 = new EntityItem(world, p1.getX() + 0.5D, p1.getY() + 0.5D,
											p1.getZ() + 0.5D, new ItemStack(burnt.getItem(), 1, burnt.getItemDamage()));
									if (burnt.getItem() instanceof ItemBlock) {
										Block put = ((ItemBlock) burnt.getItem()).getBlock();
										IBlockState next = put.getStateFromMeta(burnt.getItemDamage());
										world.setBlockState(p1, next);
										world.notifyBlockOfStateChange(p1, put);
									} else {
										world.setBlockToAir(p1);
										drop2.motionY = 0.025D;
										world.spawnEntityInWorld(drop2);
									}
									if (world instanceof WorldServer) {
										((WorldServer) world).spawnParticle(EnumParticleTypes.FLAME, p1.getX() + 0.5D,
												p1.getY() + 0.0D, p1.getZ() + 0.5D, 8, 0.75D, 0.75D, 0.75D, 0.05D,
												new int[0]);
									}
								}
							}
						}
					}

				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP,
						SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_FIRE_EXTINGUISH,
						SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else {
				world.playSound(player, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.65F, 1.0F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Require high temperature");
		}
	}

}
