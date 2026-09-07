package defeatedcrow.hac.food.material.item;

import java.util.function.Supplier;

import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FertilizerItemDC extends MaterialItemDC {

	public FertilizerItemDC(Supplier<CreativeModeTab> tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	@Override
	public InteractionResult useOn(UseOnContext cont) {
		Level level = cont.getLevel();
		BlockPos pos = cont.getClickedPos();
		ItemStack use = cont.getItemInHand();

		Player player = cont.getPlayer();
		boolean success = false;
		BlockState block = level.getBlockState(pos);
		if (block.is(TagDC.BlockTag.FARMLAND)) {
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else if (!DCUtil.isEmpty(use) && use.is(TagDC.ItemTag.FERTILIZER_ADV)) {
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
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
							if (crop.getBlock() instanceof BonemealableBlock && !crop.is(Blocks.GRASS_BLOCK)) {
								BonemealableBlock target = (BonemealableBlock) crop.getBlock();
								if (target.isValidBonemealTarget(level, mpos, crop, level.isClientSide)) {
									if (level instanceof ServerLevel) {
										if (target.isBonemealSuccess(level, level.random, mpos, crop)) {
											target.performBonemeal((ServerLevel) level, level.random, mpos, crop);
											if (!level.isClientSide) {
												level.levelEvent(1505, mpos, 0);
											}
											success = true;
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			ItemStack meal = new ItemStack(Items.BONE_MEAL);
			int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, level, pos, block, meal);
			if (hook != 0)
				success = true;
			else if (block.getBlock() instanceof BonemealableBlock) {
				BonemealableBlock target = (BonemealableBlock) block.getBlock();
				if (target.isValidBonemealTarget(level, pos, block, level.isClientSide)) {
					if (level instanceof ServerLevel) {
						if (target.isBonemealSuccess(level, level.random, pos, block)) {
							target.performBonemeal((ServerLevel) level, level.random, pos, block);
							if (!level.isClientSide) {
								level.levelEvent(1505, pos, 0);
							}
							success = true;
						}
					}
				}
			}
		}

		if (success && !player.getAbilities().instabuild) {
			use.shrink(1);
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}
