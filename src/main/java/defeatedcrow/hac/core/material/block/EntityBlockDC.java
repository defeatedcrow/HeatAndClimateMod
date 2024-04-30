package defeatedcrow.hac.core.material.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import net.minecraft.world.phys.BlockHitResult;

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

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitRes) {
		BlockEntity tile = level.getBlockEntity(pos);
		if (tile instanceof OwnableBaseTileDC chest) {
			if (level.isClientSide) {
				return InteractionResult.SUCCESS;
			} else {
				if (player instanceof ServerPlayer sp) {
					if (player.getMainHandItem().is(Items.NAME_TAG) && ClimateCore.proxy.isOP(player)) {
						Component name = player.getMainHandItem().getHoverName();
						Player target = ClimateCore.proxy.getPlayer(sp.getLevel(), name.getString());
						if (target != null) {
							chest.setOwner(target.getUUID());
							chest.setOwnerName(target.getScoreboardName());
							sp.sendSystemMessage(Component.translatable("dcs.tip.register_owner", name.getString()));
						}
					}
				}
				return InteractionResult.SUCCESS;
			}
		} else {
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
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
			if (tile != null && tile instanceof OwnableBaseTileDC base) {
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
			CompoundTag tag = new CompoundTag();
			holder.setNBTFromItem(item, tile);
			if (tile instanceof OwnableBaseTileDC cont) {
				if (!cont.hasOwner() && living instanceof Player player) {
					cont.setOwner(player.getUUID());
					cont.setOwnerName(player.getScoreboardName());
				}
			}
		}
	}

	@Override
	public ItemStack getDropItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && tile instanceof OwnableBaseTileDC basetile) {
			CompoundTag tag = item.getOrCreateTag();
			basetile.writeTag(tag);
			item.setTag(tag);
		}
		return item;
	}

	@Override
	public void setNBTFromItem(ItemStack item, BlockEntity tile) {
		if (!DCUtil.isEmpty(item) && item.hasTag() && tile instanceof OwnableBaseTileDC basetile) {
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
	public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor level, BlockPos p1, BlockPos p2) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(p1, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(state, dir, state2, level, p1, p2);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return DCState.getBool(state, WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}
