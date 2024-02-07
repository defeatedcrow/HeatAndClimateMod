package defeatedcrow.hac.food.material.item;

import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.core.tag.TagDC;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.material.Material;

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
		boolean success = false;
		BlockState block = level.getBlockState(pos);
		if (block.is(TagDC.BlockTag.FARMLAND)) {
			return InteractionResult.sidedSuccess(level.isClientSide);
		} else {
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

							if (crop.getBlock() instanceof BonemealableBlock && crop.getMaterial() != Material.GRASS) {
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
		}

		if (success && !player.getAbilities().instabuild) {
			use.shrink(1);
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}
}
