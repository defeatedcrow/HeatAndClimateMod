package defeatedcrow.hac.machine.material.block.transport;

import java.util.List;
import java.util.stream.Collectors;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.network.packet.message.IIntReceiver;
import defeatedcrow.hac.core.network.packet.message.MsgTileSimpleIntegerToC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.client.gui.HopperEXPMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.magic.material.MagicInit;
import defeatedcrow.hac.magic.material.item.entity.ThrownSolidOrb;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

public class HopperEXPTile extends HopperBaseTile implements IIntReceiver {

	public HopperEXPTile(BlockPos pos, BlockState state) {
		super(MachineInit.HOPPER_EXP_TILE.get(), pos, state);
	}

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			return switch (id) {
			case 0 -> HopperEXPTile.this.expCount;
			default -> 0;
			};
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				HopperEXPTile.this.expCount = data;
				break;
			}
		}

		@Override
		public int getCount() {
			return 1;
		}
	};

	int count = 4;
	int expCount = 0;
	int lastCount = 0;

	public static final int getMaxEXPCount() {
		return ThrownSolidOrb.EXP_VALUE;
	}

	@Override
	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;
			return !DCState.getBool(state, DCState.POWERED);
		}
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		if (isActive(level, pos, state)) {
			// outlet only
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

			// item suction
			if (this.expCount < getMaxEXPCount()) {
				List<ExperienceOrb> orbs = this.getSuckShape().toAabbs().stream().flatMap(aabb -> getLevel().getEntitiesOfClass(ExperienceOrb.class,
				    aabb.move(this.getLevelX() - 0.5D, this.getLevelY() + getInputSide().getStepY() * 0.5D - 1.0D, this.getLevelZ() - 0.5D).inflate(3.0D, 0.0D, 3.0D),
				    EntitySelector.ENTITY_STILL_ALIVE).stream()).collect(Collectors.toList());
				for (ExperienceOrb orb : orbs) {
					Vec3 current = new Vec3(this.getLevelX(), this.getLevelY(), this.getLevelZ());
					Vec3 rad = orb.position().vectorTo(current);
					double distance = rad.length();
					Vec3 rad2 = rad.normalize();
					orb.move(MoverType.SELF, rad2.scale(0.05D));
					if (distance < 1.0D) {
						if (orb.value > 0) {
							this.expCount += orb.value;
							orb.discard();

							while (this.expCount >= getMaxEXPCount()) {
								ItemStack solidOrb = new ItemStack(MagicInit.EXP_GEM.get());
								int i = this.getInventory().canInsertResult(solidOrb, getBottomSlots());
								if (i > 0) {
									if (this.getInventory().insertResult(solidOrb, getBottomSlots()) > 0) {
										this.expCount -= getMaxEXPCount();
									}
									this.setChanged();
								}
							}
						}
					}
				}
			}

			if (lastCount != expCount) {
				lastCount = expCount;
				if (level instanceof ServerLevel sl)
					MsgTileSimpleIntegerToC.sendToClient(sl, pos, this.expCount);
			}
		}
		return false;
	}

	@Override
	public int getContainerSize() {
		return 5;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] {};
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 0, 1, 2, 3, 4 };
	}

	@Override
	protected int[] getFilterSlots() {
		return new int[] {};
	}

	@Override
	public boolean canPlaceItem(int s, ItemStack stack) {
		return false;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.hopper.with_owner", this.ownerName) : Component.translatable("dcs.container.hopper");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new HopperEXPMenu(MachineInit.HOPPER_EXP_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		this.expCount = tag.getInt(TagKeyDC.AMOUNT);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt(TagKeyDC.AMOUNT, this.expCount);
	}

	@Override
	public void receiveInteger(int i) {
		this.expCount = i;
	}

}
