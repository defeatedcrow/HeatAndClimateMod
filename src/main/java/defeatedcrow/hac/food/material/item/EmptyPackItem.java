package defeatedcrow.hac.food.material.item;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.item.MaterialItemDC;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EmptyPackItem extends MaterialItemDC {

	public EmptyPackItem(CreativeModeTab tab, String s, TagKey<Item> pair) {
		super(tab, s, pair);
	}

	@Override
	public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		MutableComponent st = Component.translatable("dcs.tip.empty_pack");
		list.add(st);
		super.appendHoverText(item, level, list, flag);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		BlockHitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
		if (hitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else if (hitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			BlockPos pos = hitresult.getBlockPos();
			Direction dir = hitresult.getDirection();
			BlockPos pos1 = pos.relative(dir);
			if (level.mayInteract(player, pos) && player.mayUseItemAt(pos1, dir, itemstack)) {
				BlockState state = level.getBlockState(pos);
				if (state.getFluidState().is(Fluids.WATER) && state.getFluidState().isSource()) {
					player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
					ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_WATER.get()), false);
					return InteractionResultHolder.sidedSuccess(ret.copy(), level.isClientSide());
				}
				if (state.getFluidState().is(CoreInit.SPARKLING.getStillFluid().get())) {
					player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
					ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_SPARKLING.get()), false);
					return InteractionResultHolder.sidedSuccess(ret.copy(), level.isClientSide());
				}
			}
		}
		return InteractionResultHolder.fail(itemstack);
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (entity instanceof Cow || entity instanceof Llama || entity instanceof Goat) {
			if (entity.level.isClientSide)
				return InteractionResult.SUCCESS;
			BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
			if (!((Animal) entity).isBaby()) {
				player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
				ItemStack ret = ItemUtils.createFilledResult(itemstack, player, new ItemStack(FoodInit.FOOD_MILK.get()), false);
			}
			return net.minecraft.world.InteractionResult.SUCCESS;
		}
		return net.minecraft.world.InteractionResult.PASS;
	}

}
