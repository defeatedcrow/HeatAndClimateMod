package defeatedcrow.hac.food.material.item;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FertilizerItemDC extends MaterialItemDC {

	public FertilizerItemDC(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	@Override
	public InteractionResult useOn(UseOnContext cont) {
		Level level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		ItemStack use = cont.getItemInHand();
		BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
		Player player = cont.getPlayer();
		for (int x = -2; x < 2; x++) {
			for (int z = -2; z < 2; z++) {
				for (int y = -2; y < 2; y++) {
					mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (mpos.getY() > level.getMinBuildHeight() && mpos.getY() < level.getMaxBuildHeight()) {
						BlockState crop = level.getBlockState(mpos);
						ItemStack meal = new ItemStack(Items.BONE_MEAL);
						int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, level, mpos, crop, meal);
						if (hook != 0)
							continue;

						if (crop.is(TagDC.BlockTag.FARMLAND)) {
							int m = DCState.getInt(crop, BlockStateProperties.MOISTURE);
							int f = DCState.getInt(crop, DCState.FERTILE);
							if (f < 3) {
								if (player instanceof ServerPlayer)
									CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, mpos, use);
								// 肥料アイテムを耕地にまく
								BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, 3).setValue(BlockStateProperties.MOISTURE, m);
								level.setBlockAndUpdate(mpos, next);
								if (!level.isClientSide) {
									level.levelEvent(1505, mpos, 0);
								}

							}
						} else if (crop.getBlock() instanceof BonemealableBlock) {
							BonemealableBlock target = (BonemealableBlock) crop.getBlock();
							if (target.isValidBonemealTarget(level, mpos, crop, level.isClientSide)) {
								if (level instanceof ServerLevel) {
									if (target.isBonemealSuccess(level, level.random, mpos, crop)) {
										target.performBonemeal((ServerLevel) level, level.random, mpos, crop);
										if (!level.isClientSide) {
											level.levelEvent(1505, mpos, 0);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}
