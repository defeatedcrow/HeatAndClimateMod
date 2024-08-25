package defeatedcrow.hac.machine.material.block.transport;

import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.ConveyorSorterMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class ConveyorSortingTile extends ConveyorTile implements IIntReceiver {

	public ConveyorSortingTile(BlockPos pos, BlockState state) {
		super(MachineInit.CONVEYOR_SORTER_TILE.get(), pos, state);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return ConveyorSortingTile.this.sides.get(0).priority;
			case 1:
				return ConveyorSortingTile.this.sides.get(1).priority;
			case 2:
				return ConveyorSortingTile.this.sides.get(2).priority;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				SortingType t1 = SortingType.fronInt(data);
				ConveyorSortingTile.this.sides.set(0, t1);
				break;
			case 1:
				SortingType t2 = SortingType.fronInt(data);
				ConveyorSortingTile.this.sides.set(1, t2);
				break;
			case 2:
				SortingType t3 = SortingType.fronInt(data);
				ConveyorSortingTile.this.sides.set(2, t3);
				break;
			}

		}

		@Override
		public int getCount() {
			return 3;
		}
	};

	public int outputSide = 0; // 0:forward, 1:left, 2:right

	public Direction getOutputDirection() {
		Direction side = getBlockDir();
		if (outputSide == 1) {
			side = getBlockDir().getCounterClockWise();
		}
		if (outputSide == 2) {
			side = getBlockDir().getClockWise();
		}
		return side;
	}

	protected final NonNullList<SortingType> sides = NonNullList.withSize(3, SortingType.OFF);

	@Override
	protected boolean onMiddlePosition() {
		if (!DCUtil.isEmpty(this.getItem(0))) {
			ItemStack target = this.getItem(0).copy();
			int currentSide = -1;
			int priority = -1;
			for (int s = 0; s < 3; s++) {
				SortingType type = sides.get(s);
				if (type.priority <= priority) {
					continue;
				}
				ItemStack filter = inv.getItem(s + 1);
				if (DCUtil.isEmpty(filter)) {
					if (type == SortingType.OFF) {
						currentSide = s;
						priority = type.priority;
					} else {
						continue;
					}
				} else {
					if (type == SortingType.EXACT_MATCH && DCItemUtil.isSameItem(target, filter, true)) {
						currentSide = s;
						priority = type.priority;
					} else if (type == SortingType.COMMON_TAG && DCItemUtil.hasSameTag(target, filter)) {
						currentSide = s;
						priority = type.priority;
					} else if (type == SortingType.COMMON_PHRASE) {
						String name = filter.getHoverName().getString();
						if (TagUtil.matchTag(name, target.getItem()).isPresent()) {
							currentSide = s;
							priority = type.priority;
						} else if (target.getHoverName().getString().contains(name)) {
							currentSide = s;
							priority = type.priority;
						}
					} else if (type == SortingType.OFF) {
						currentSide = s;
						priority = type.priority;
					}
				}
			}
			if (currentSide != outputSide) {
				outputSide = currentSide;
				if (level instanceof ServerLevel)
					MsgTileSimpleIntegerToC.sendToClient((ServerLevel) level, getBlockPos(), currentSide);
			}

			if (outputSide < 0) {
				// 出口がない
				BlockPos next = getBlockPos().below();
				BlockEntity outlet = getLevel().getBlockEntity(next);
				boolean flag = false;
				if (outlet != null) {
					flag = outlet.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).map(handler -> {
						ItemStack take = this.getItem(0).copy();
						take.setCount(1);
						for (int j = 0; j < handler.getSlots(); j++) {
							ItemStack ret = handler.insertItem(j, take, true);
							if (DCUtil.isEmpty(ret)) {
								handler.insertItem(j, take, false);
								this.getInventory().removeItem(0, 1);
								this.setChanged();
								outlet.setChanged();
								break;
							}
						}
						return DCUtil.isEmpty(this.getItem(0));
					}).orElse(false);
				}
				if (!flag) {
					ItemEntity drop = new ItemEntity(getLevel(), next.getX() + 0.5D, next.getY() + 0.5D, next.getZ() + 0.5D, this.getItem(0).copy());
					if (getLevel().addFreshEntity(drop)) {
						this.getInventory().setItem(0, ItemStack.EMPTY);
						this.setChanged();
						flag = true;
					}
				}
			}
		}
		return true;
	}

	@Override
	public void receiveInteger(int i) {
		outputSide = i;
	}

	public void switchSide(int i) {
		if (i < 0 || i > 2)
			i = 0;
		SortingType type = sides.get(i);
		// DCLogger.debugInfoLog("sorter setting:" + i + ", " + type);
		if (type == SortingType.EXACT_MATCH) {
			sides.set(i, SortingType.OFF);
		} else if (type == SortingType.COMMON_TAG) {
			sides.set(i, SortingType.EXACT_MATCH);
		} else if (type == SortingType.COMMON_PHRASE) {
			sides.set(i, SortingType.COMMON_TAG);
		} else if (type == SortingType.OFF) {
			sides.set(i, SortingType.COMMON_PHRASE);
		}
	}

	@Override
	protected BlockPos getForwardPos() {
		Direction side = getBlockDir();
		if (outputSide == 1) {
			side = getBlockDir().getCounterClockWise();
		}
		if (outputSide == 2) {
			side = getBlockDir().getClockWise();
		}
		return getBlockPos().relative(side);
	}

	public void setFace(int side, SortingType type) {
		sides.set(side, type);
	}

	public SortingType getFace(int side) {
		if (side >= 0 && side <= 2) {
			return sides.get(side);
		} else {
			return sides.get(0);
		}
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new ConveyorSorterMenu(MachineInit.CONVEYOR_SORTER_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public boolean hasMenu() {
		return true;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.conveyor.with_owner", this.ownerName) : Component.translatable("dcs.conveyor.generator");
	}

	/* Inventory */

	protected InventoryDC inv = new InventoryDC(4, this);

	@Override
	public InventoryDC getInventory() {
		return inv;
	}

	@Override
	public int getContainerSize() {
		return 4;
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		outputSide = tag.getInt("dcs.sorting.outputSide");
		for (int i = 0; i < 3; i++) {
			int p = tag.getInt("dcs.sorting.sortingType_" + i);
			sides.set(i, SortingType.fronInt(p));
		}
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt("dcs.sorting.outputSide", outputSide);
		for (int i = 0; i < 3; i++) {
			tag.putInt("dcs.sorting.sortingType_" + i, sides.get(i).priority);
		}
	}

	public enum SortingType {
		OFF(0),
		COMMON_PHRASE(1),
		COMMON_TAG(2),
		EXACT_MATCH(3);

		public final int priority;

		SortingType(int i) {
			priority = i;
		}

		public static SortingType fronInt(int i) {
			switch (i) {
			case 0:
				return OFF;
			case 1:
				return COMMON_PHRASE;
			case 2:
				return COMMON_TAG;
			case 3:
				return EXACT_MATCH;
			default:
				return OFF;
			}
		}
	}
}
