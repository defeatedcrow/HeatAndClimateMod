package defeatedcrow.hac.magic.block;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockMaceFlower extends ItemBlockMace {

	public ItemBlockMaceFlower(Block block) {
		super(block);
	}

	@Override
	protected void doUsingEffect(ItemStack stack, EntityPlayer player, World world) {
		if (!DCUtil.isEmpty(stack) && player != null) {
			boolean hasAcv = true;
			boolean flag = player.capabilities.isCreativeMode;

			if (hasAcv || flag) {
				if (!world.isRemote) {
					// 成長
					IClimate clm = ClimateAPI.register.getClimateFromParam(DCHeatTier.SMELTING, DCHumidity.NORMAL,
							DCAirflow.TIGHT);
					// 5x5x3 の範囲
					BlockPos pos = player.getPosition();
					BlockPos min = new BlockPos(pos.add(-3, -1, -3));
					BlockPos max = new BlockPos(pos.add(3, 1, 3));
					Iterable<BlockPos> itr = pos.getAllInBox(min, max);
					for (BlockPos p1 : itr) {
						IBlockState st = world.getBlockState(p1);
						int meta = st.getBlock().getMetaFromState(st);
						if (world.isAirBlock(p1) || st.getMaterial() == Material.WATER || st.getBlock() == Blocks.DIRT
								|| st.getBlock() == Blocks.GRASS) {
							continue;
						}
						if (st.getBlock() instanceof IGrowable) {
							IGrowable pl = (IGrowable) st.getBlock();
							if (pl.canUseBonemeal(world, itemRand, p1, st) && pl.canGrow(world, p1, st, false)) {
								pl.grow(world, itemRand, p1, st);
								if (world instanceof WorldServer) {
									((WorldServer) world).spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
											p1.getX() + 0.5D, p1.getY() + 0.5D, p1.getZ() + 0.5D, 8, 0.5D, 0.5D, 0.5D,
											0.5D, new int[0]);
								}
							}
						}
					}

				}

				world.playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP,
						SoundCategory.PLAYERS, 0.65F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);

			} else {
				world.playSound(player, player.posX, player.posY, player.posZ,
						SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.65F, 1.0F);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + "Require normal temperature and wet climate");
		}
	}

}
