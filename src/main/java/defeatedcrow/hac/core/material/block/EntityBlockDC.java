package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public abstract class EntityBlockDC extends BlockDC implements EntityBlock, SimpleWaterloggedBlock, ITileNBTHolder {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public EntityBlockDC(BlockBehaviour.Properties prop) {
		super(prop);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int i1, int i2) {
		super.triggerEvent(state, level, pos, i1, i2);
		BlockEntity blockentity = level.getBlockEntity(pos);
		return blockentity == null ? false : blockentity.triggerEvent(i1, i2);
	}

	@Override
	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		return blockentity instanceof MenuProvider ? (MenuProvider) blockentity : null;
	}

	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(BlockEntityType<A> type, BlockEntityType<E> type2, BlockEntityTicker<? super E> ticker) {
		return type2 == type ? (BlockEntityTicker<A>) ticker : null;
	}

	// drop

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> ret = Lists.newArrayList();
		if (state == null || builder == null) {
			ret.addAll(super.getDrops(state, builder));
		} else if (state.getBlock() instanceof EntityBlockDC cont && builder != null) {
			LootContext context = builder.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
			BlockEntity tile = null;
			if (context.hasParam(LootContextParams.BLOCK_ENTITY)) {
				tile = context.getParam(LootContextParams.BLOCK_ENTITY);
			}

			ItemStack drop = getMainDrop();
			if (tile != null && tile instanceof OwnableContainerBaseTileDC base) {
				drop = getDropItem(drop, base);
			}
			ret.add(drop);
		} else {
			ret.addAll(super.getDrops(state, builder));
		}
		return ret;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity living, ItemStack item) {
		if (!DCUtil.isEmpty(item) && item.getItem() instanceof BlockItemDC && ((BlockItemDC) item.getItem()).getBlock() instanceof ITileNBTHolder holder) {
			BlockEntity tile = level.getBlockEntity(pos);
			if (tile instanceof OwnableContainerBaseTileDC cont) {
				CompoundTag tag = new CompoundTag();
				holder.setNBTFromItem(item, cont);
				if (!cont.hasOwner() && living instanceof Player player) {
					cont.setOwner(player.getUUID());
					cont.setOwnerName(player.getScoreboardName());
				}
			}
		}
	}

	@Override
	public ItemStack getDropItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && tile instanceof OwnableContainerBaseTileDC basetile) {
			CompoundTag tag = item.getOrCreateTag();
			basetile.writeTag(tag);
			item.setTag(tag);
		}
		return item;
	}

	@Override
	public void setNBTFromItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && item.hasTag() && tile instanceof OwnableContainerBaseTileDC basetile) {
			CompoundTag tag = item.getTag();
			basetile.load(tag);
		}
	}

	@Override
	public ItemStack getMainDrop() {
		return new ItemStack(this);
	}

	@Override
	public ItemStack getSilkyDrop() {
		return ItemStack.EMPTY;
	}

	@Override
	public List<ItemStack> getAdditionalDrop(BlockState state, ItemStack tool, Entity entity, @Nullable BlockEntity tile) {
		return Lists.newArrayList();
	}

	/* waterlogged */

	@Override
	public FluidState getFluidState(BlockState state) {
		return DCState.getBool(state, WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
