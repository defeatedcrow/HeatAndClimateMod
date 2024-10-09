package defeatedcrow.hac.machine.material.block.machine;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.ClimateAPI;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IClimateReceiver;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import defeatedcrow.hac.core.network.packet.message.MsgTileClimateToC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

/**
 * 汎用マシンベースクラス<br>
 * - Owner登録あり、開閉アニメーションなし<br>
 * - 内容のTick同期あり<br>
 * - スロット不定
 */
public abstract class ProcessTileBaseDC extends OwnableContainerBaseTileDC implements IClimateReceiver {

	public ProcessTileBaseDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public int lastProgress = 0;
	public int currentProgress = 0;
	public int totalProgress = 0;

	public int freeCounter = 0;

	public IDeviceRecipe recipe = null;

	// tick
	public static void serverTick(Level level, BlockPos pos, BlockState state, ProcessTileBaseDC tile) {

		tile.updateClimate(level, pos);

		if (tile.isActive(level, pos, state)) {
			if (tile.isInProcess()) {
				if (tile.continueProcess(level, pos, state)) {
					if (tile.currentProgress >= tile.totalProgress && tile.finishProcess(level, pos, state)) {
						tile.consumeInputs();
						tile.resetProcess();
						tile.setChanged(level, pos, state);
					} else {
						tile.lastProgress = tile.currentProgress;
						tile.currentProgress++;
					}
				} else {
					tile.resetProcess();
				}
			}
			if (!tile.isInProcess()) {
				if (tile.startProcess(level, pos, state)) {
					tile.setChanged(level, pos, state);
				}
			}
		}

		tile.onTickProcess(level, pos, state);
	}

	private int count = 19;
	private int lastClimate = 0;

	public void updateClimate(Level level, BlockPos pos) {
		if (count <= 0) {
			count = 19;

			if (heatTime <= 0 && receivingHeat != DCHeatTier.NORMAL) {
				receivingHeat = DCHeatTier.NORMAL;
			} else {
				heatTime--;
			}

			if (humTime <= 0 && receivingHum != DCHumidity.NORMAL) {
				receivingHum = DCHumidity.NORMAL;
			} else {
				humTime--;
			}

			if (airTime <= 0 && receivingAir != DCAirflow.NORMAL) {
				receivingAir = DCAirflow.NORMAL;
			} else {
				airTime--;
			}

			currentClimate = new ClimateSupplier(level, pos).get();

			int i = resultClimate().getClimateInt();
			if (i != lastClimate) {
				lastClimate = i;
				this.setChanged(level, pos, getBlockState());
				if (level instanceof ServerLevel)
					MsgTileClimateToC.sendToClient((ServerLevel) this.getLevel(), pos, i);
			}

		} else {
			count--;
		}

	}

	public boolean isActive(Level level, BlockPos pos, BlockState state) {
		return !DCState.getBool(state, DCState.POWERED);
	}

	public abstract boolean isInProcess();

	public abstract boolean continueProcess(Level level, BlockPos pos, BlockState state);

	public abstract boolean finishProcess(Level level, BlockPos pos, BlockState state);

	public abstract boolean consumeInputs();

	public abstract boolean resetProcess();

	public abstract boolean startProcess(Level level, BlockPos pos, BlockState state);

	public abstract boolean onTickProcess(Level level, BlockPos pos, BlockState state);

	// inventory

	public InventoryDC inventory = new InventoryDC(this.getContainerSize(), this);

	@Override
	public InventoryDC getInventory() {
		return inventory;
	}

	@Override
	public abstract int getContainerSize();

	protected abstract int[] getTopSlots();

	protected abstract int[] getBottomSlots();

	protected abstract int[] getSideSlots();

	protected boolean include(int[] ii, int s) {
		if (ii != null && ii.length > 0) {
			for (int i : ii) {
				if (i == s)
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean canPlaceItem(int s, ItemStack stack) {
		return !include(getBottomSlots(), s);
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		if (dir == Direction.DOWN) {
			return getBottomSlots();
		} else {
			return dir == Direction.UP ? getTopSlots() : getSideSlots();
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int s, ItemStack stack, @Nullable Direction dir) {
		return this.canPlaceItem(s, stack) && !this.isLocked();
	}

	@Override
	public boolean canTakeItemThroughFace(int s, ItemStack stack, Direction dir) {
		return dir != Direction.UP && !include(getTopSlots(), s) && !this.isLocked();
	}

	// climate

	DCHeatTier receivingHeat = DCHeatTier.NORMAL;
	DCHumidity receivingHum = DCHumidity.NORMAL;
	DCAirflow receivingAir = DCAirflow.NORMAL;
	int heatTime = 0;
	int humTime = 0;
	int airTime = 0;

	@Override
	public void receiveHeatTier(DCHeatTier heat) {
		if (heat != null && heat != DCHeatTier.NORMAL) {
			receivingHeat = heat;
			heatTime = 3;
		}
	}

	@Override
	public void receiveHumidity(DCHumidity hum) {
		if (hum != null && hum != DCHumidity.NORMAL) {
			receivingHum = hum;
			humTime = 3;
		}
	};

	@Override
	public void receiveAirflow(DCAirflow air) {
		if (air != null && air != DCAirflow.NORMAL) {
			receivingAir = air;
			airTime = 3;
		}
	};

	public IClimate clientClimate = ClimateAPI.helper.getDefaultClimate();

	@Override
	public void currentClimate(int clm) {
		clientClimate = ClimateAPI.helper.getClimateFromInt(clm);
	}

	IClimate currentClimate = ClimateAPI.helper.getDefaultClimate();

	@Override
	public IClimate resultClimate() {
		DCHeatTier h = receivingHeat == DCHeatTier.NORMAL ? currentClimate.getHeat() : receivingHeat;
		DCHumidity w = receivingHum == DCHumidity.NORMAL ? currentClimate.getHumidity() : receivingHum;
		DCAirflow a = receivingAir == DCAirflow.NORMAL ? currentClimate.getAirflow() : receivingAir;
		return ClimateAPI.helper.getClimateFromParam(h, w, a);
	}

	// nbt

	@Override
	public void loadTag(CompoundTag tag) {
		super.loadTag(tag);
		lastProgress = tag.getInt(TagKeyDC.LAST_PROGRESS);
		currentProgress = tag.getInt(TagKeyDC.CURRENT_PROGRESS);
		totalProgress = tag.getInt(TagKeyDC.MAX_PROGRESS);
		freeCounter = tag.getInt(TagKeyDC.COUNTER);

		heatTime = tag.getInt("dcs.heat_time");
		humTime = tag.getInt("dcs.hum_time");
		airTime = tag.getInt("dcs.air_time");

		IClimate receiving = ClimateAPI.helper.getClimateFromNBT(tag);
		receivingHeat = receiving.getHeat();
		receivingHum = receiving.getHumidity();
		receivingAir = receiving.getAirflow();

	}

	@Override
	public void writeTag(CompoundTag tag) {
		super.writeTag(tag);
		tag.putInt(TagKeyDC.LAST_PROGRESS, lastProgress);
		tag.putInt(TagKeyDC.CURRENT_PROGRESS, currentProgress);
		tag.putInt(TagKeyDC.MAX_PROGRESS, totalProgress);
		tag.putInt(TagKeyDC.COUNTER, freeCounter);

		tag.putInt("dcs_heat_time", heatTime);
		tag.putInt("dcs_hum_time", humTime);
		tag.putInt("dcs_air_time", airTime);
		IClimate receiving = ClimateAPI.helper.getClimateFromParam(receivingHeat, receivingHum, receivingAir);
		ClimateAPI.helper.setClimateToNBT(tag, receiving);
	}

	// caps

	LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

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
		this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.device.with_owner", this.ownerName) : Component.translatable("dcs.container.device");
	}

	// animはなし

	@Override
	public void startOpen(Player player) {}

	@Override
	public void stopOpen(Player player) {}

	public static int getContainerOutputSignal(BlockEntity tile) {
		if (tile instanceof ProcessTileBaseDC machine) {
			int i = 0;
			float f = 0.0F;

			for (int j : machine.getBottomSlots()) {
				ItemStack itemstack = machine.getItem(j);
				if (!itemstack.isEmpty()) {
					f += (float) itemstack.getCount() / (float) Math.min(machine.getMaxStackSize(), itemstack.getMaxStackSize());
					++i;
				}
			}

			f /= machine.getBottomSlots().length;
			return Mth.floor(f * 14.0F) + (i > 0 ? 1 : 0);
		} else {
			return 0;
		}
	}

}
