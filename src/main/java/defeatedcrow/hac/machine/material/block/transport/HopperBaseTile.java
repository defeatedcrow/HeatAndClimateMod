package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.InventoryFilterWrapperDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public abstract class HopperBaseTile extends OwnableContainerBaseTileDC implements Hopper {

	public HopperBaseTile(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, HopperBaseTile tile) {
		tile.onTickProcess(level, pos, state);
	}

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED);
	}

	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive(level, pos, state)) {
			// bottom
			BlockEntity outlet = level.getBlockEntity(pos.relative(getOutletSide()));
			if (getBottomSlots() != null && getBottomSlots().length > 0 && outlet != null) {
				outlet.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).ifPresent(handler -> {
					int slot = -1;
					for (int i : getBottomSlots()) {
						if (!DCUtil.isEmpty(this.getInventory().getItem(i))) {
							slot = i;
							break;
						}
					}
					if (slot >= 0) {
						ItemStack take = this.getInventory().getItem(slot).copy();
						take.setCount(1);
						for (int j = 0; j < handler.getSlots(); j++) {
							ItemStack ret = handler.insertItem(j, take, true);
							if (DCUtil.isEmpty(ret)) {
								handler.insertItem(j, take, false);
								this.getInventory().removeItem(slot, 1);
								this.setChanged();
								outlet.setChanged();
								break;
							}
						}
					}
				});
			}
			// side
			BlockEntity filterSide = level.getBlockEntity(pos.relative(getFilterSide()));
			if (hasFilter() && filterSide != null) {
				filterSide.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).ifPresent(handler -> {
					int slot = -1;
					for (int i : getFilterSlots()) {
						if (!DCUtil.isEmpty(this.getInventory().getItem(i)) && this.getInventory().getItem(i).getCount() > 1) {
							slot = i;
							break;
						}
					}
					if (slot >= 0) {
						ItemStack take = this.getInventory().getItem(slot).copy();
						take.setCount(1);
						for (int j = 0; j < handler.getSlots(); j++) {
							ItemStack ret = handler.insertItem(j, take, true);
							if (DCUtil.isEmpty(ret)) {
								handler.insertItem(j, take, false);
								this.getInventory().removeItem(slot, 1);
								this.setChanged();
								filterSide.setChanged();
								break;
							}
						}
					}
				});
			}
			// top
			BlockEntity input = level.getBlockEntity(pos.relative(getInputSide()));
			if (input != null) {
				if (!(input instanceof Hopper)) {
					if (getTopSlots() != null && getTopSlots().length > 0 && input != null) {
						input.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.DOWN)
							.ifPresent(handler -> {
								int slot = -1;
								for (int j = 0; j < handler.getSlots(); j++) {
									if (!handler.extractItem(j, 1, true).isEmpty()) {
										slot = j;
										break;
									}
								}
								if (slot >= 0) {
									ItemStack take = handler.extractItem(slot, 1, true).copy();
									if (isFilterInclude(take)) {
										int i = this.getInventory().canInsertResult(take, getFilterSlots());
										if (i > 0) {
											this.getInventory().insertResult(take, getFilterSlots());
											handler.extractItem(slot, 1, false);
											this.setChanged();
											input.setChanged();
										}
									} else {
										int i = this.getInventory().canInsertResult(take, getBottomSlots());
										if (i > 0) {
											this.getInventory().insertResult(take, getBottomSlots());
											handler.extractItem(slot, 1, false);
											this.setChanged();
											input.setChanged();
										}
									}
								}
							});
					}
				}
			} else {
				// item suction
				List<ItemEntity> drops = this.getSuckShape().toAabbs().stream().flatMap((aabb) -> {
					return getLevel().getEntitiesOfClass(ItemEntity.class,
						aabb.move(this.getLevelX() - 0.5D, this.getLevelY() + (getInputSide().getStepY() * 0.5D) - 1.0D, this.getLevelZ() - 0.5D),
						EntitySelector.ENTITY_STILL_ALIVE).stream();
				}).collect(Collectors.toList());
				for (ItemEntity drop : drops) {
					ItemStack take = drop.getItem().copy();
					take.setCount(1);
					if (isFilterInclude(take)) {
						int i = this.getInventory().canInsertResult(take, getFilterSlots());
						if (i > 0) {
							this.getInventory().insertResult(take, getFilterSlots());
							drop.getItem().split(i);
							if (drop.getItem().isEmpty())
								drop.discard();
							this.setChanged();
						}
					} else {
						int i = this.getInventory().canInsertResult(take, getBottomSlots());
						if (i > 0) {
							this.getInventory().insertResult(take, getBottomSlots());
							drop.getItem().split(i);
							if (drop.getItem().isEmpty())
								drop.discard();
							this.setChanged();
						}
					}
				}
			}
		}
		return false;
	}

	public VoxelShape INSIDE_R = Block.box(2.0D, 16.0D, 2.0D, 14.0D, 20.0D, 14.0D);
	public VoxelShape BELOW = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public VoxelShape SUCK_R = Shapes.or(INSIDE_R, BELOW);

	@Override
	public VoxelShape getSuckShape() {
		return DCState.getBool(getBlockState(), DCState.FLAG) ? SUCK_R : SUCK;
	}

	public Direction getInputSide() {
		return DCState.getBool(getBlockState(), DCState.FLAG) ? Direction.DOWN : Direction.UP;
	}

	public Direction getOutletSide() {
		Direction dir = DCState.getFace(getBlockState(), DCState.DIRECTION);
		if (getBlockState().is(TagDC.BlockTag.HOPPER_FILTER) || hasFilter()) {
			return DCState.getBool(getBlockState(), DCState.FLAG) ? Direction.UP : Direction.DOWN;
		} else {
			if (dir.getAxis().isVertical()) {
				return DCState.getBool(getBlockState(), DCState.FLAG) ? Direction.UP : Direction.DOWN;
			} else {
				return dir;
			}
		}
	}

	public Direction getFilterSide() {
		Direction dir = DCState.getFace(getBlockState(), DCState.DIRECTION);
		if (getBlockState().is(TagDC.BlockTag.HOPPER_FILTER) || hasFilter()) {
			if (dir.getAxis().isVertical()) {
				return Direction.NORTH;
			} else {
				return dir;
			}
		} else {
			return DCState.getBool(getBlockState(), DCState.FLAG) ? Direction.UP : Direction.DOWN;
		}
	}

	@Override
	public double getLevelX() {
		return this.worldPosition.getX() + 0.5D;
	}

	@Override
	public double getLevelY() {
		return this.worldPosition.getY() + 0.5D;
	}

	@Override
	public double getLevelZ() {
		return this.worldPosition.getZ() + 0.5D;
	}

	/* Inventory */

	protected InventoryDC inv = new InventoryDC(getContainerSize(), this);

	@Override
	public InventoryDC getInventory() {
		return inv;
	}

	protected abstract int[] getTopSlots();

	protected abstract int[] getBottomSlots();

	protected abstract int[] getFilterSlots();

	public boolean hasFilter() {
		return getFilterSlots() != null && getFilterSlots().length > 0;
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		if (dir == null || dir == Direction.UP) {
			return getTopSlots();
		} else if (dir == Direction.DOWN) {
			return getBottomSlots();
		} else {
			return getFilterSlots();
		}
	}

	protected boolean include(int[] ii, int s) {
		if (ii != null && ii.length > 0) {
			for (int i : ii) {
				if (i == s)
					return true;
			}
		}
		return false;
	}

	public boolean isFilterInclude(ItemStack stack) {
		if (stack.isEmpty() || getFilterSlots() == null || getFilterSlots().length == 0)
			return false;
		for (int i : getFilterSlots()) {
			ItemStack item = getInventory().getItem(i);
			if (!DCUtil.isEmpty(item) && item.isSameItemSameTags(item, stack))
				return true;
		}
		return false;
	}

	@Override
	public boolean canPlaceItem(int s, ItemStack stack) {
		return include(getFilterSlots(), s) == isFilterInclude(stack);
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return dir == Direction.UP && !this.isLocked() && this.canPlaceItem(s, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		if (dir == Direction.UP || this.isLocked()) {
			return false;
		} else {
			return dir == Direction.DOWN ? include(getBottomSlots(), s) : include(getFilterSlots(), s);
		}
	}

	LazyOptional<? extends IItemHandler>[] handlers = InventoryFilterWrapperDC.create(this, getFilterSlots(), Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null && capability == ForgeCapabilities.ITEM_HANDLER) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.handlers = InventoryFilterWrapperDC.create(this, getFilterSlots(), Direction.UP, Direction.DOWN, Direction.NORTH);
	}

}
