package defeatedcrow.hac.machine.material.block.monitor;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.network.packet.message.IIntReceiverClient;
import defeatedcrow.hac.machine.client.gui.MonitorAndonMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class MonitorAndonTile extends MonitorBaseTile implements IIntReceiverClient {

	public boolean isInventoryMode = false;
	public boolean isAlartMode = false;
	public int thresholdYellow = 8;
	public int thresholdRed = 14;

	public MonitorAndonTile(BlockPos pos, BlockState state) {
		super(MachineInit.MONITOR_ANDON_TILE.get(), pos, state);
	}

	@Override
	protected boolean updateAmount() {
		BlockPos p1 = getPairPos();
		if (getPairPos() == null || getPairPos() == BlockPos.ZERO) {
			p1 = getBlockPos().below();
		}
		if (isInventoryMode) {
			BlockState target = getLevel().getBlockState(p1);
			int signal = target.getAnalogOutputSignal(getLevel(), p1);
			amount = signal;
			amountMax = 15;
			return amount > 0;
		} else {
			int signal = getLevel().getBestNeighborSignal(p1);
			amount = signal;
			amountMax = 15;
			return amount > 0;
		}

	}

	@Override
	protected boolean updateState() {
		BlockState state = this.getBlockState();
		int lastLit = DCState.getInt(state, DCState.STAGE3);
		int lit = 0;
		if (thresholdYellow <= thresholdRed) {
			if (amount >= thresholdRed)
				lit = 2;
			else if (amount >= thresholdYellow)
				lit = 1;
		} else {
			if (amount <= thresholdRed)
				lit = 2;
			else if (amount <= thresholdYellow)
				lit = 1;
		}

		if (lastLit != lit) {
			MonitorAndonBlock.changePowerState(getLevel(), getBlockPos(), lit);
			level.updateNeighbourForOutputSignal(getBlockPos(), getBlockState().getBlock());

			if (lit == 2 && isAlartMode) {
				BlockPos p1 = getPairPos();
				if (getPairPos() == null || getPairPos() == BlockPos.ZERO) {
					p1 = getBlockPos().below();
				}
				Player player = this.getLevel().getPlayerByUUID(getOwner());
				if (player instanceof ServerPlayer sp) {
					MutableComponent mes = Component.literal("ALART! ");
					mes.append(Component.literal(" X:" + p1.getX()));
					mes.append(Component.literal(" Y:" + p1.getY()));
					mes.append(Component.literal(" Z:" + p1.getZ()));
					sp.sendSystemMessage(mes);
				}
			}
		}
		return lastLit != lit;
	}

	@Override
	public void receiveIntegerFromClient(int i) {
		switch (i) {
		case 0:
			if (thresholdRed < 15)
				thresholdRed++;
			break;
		case 1:
			if (thresholdRed > 0)
				thresholdRed--;
			break;
		case 2:
			if (thresholdYellow < 15)
				thresholdYellow++;
			break;
		case 3:
			if (thresholdYellow > 0)
				thresholdYellow--;
			break;
		case 4:
			isInventoryMode = false;
			break;
		case 5:
			isInventoryMode = true;
			break;
		case 6:
			isAlartMode = false;
			break;
		case 7:
			isAlartMode = true;
			break;
		default:
		}
		this.setChanged();
	}

	@Override
	public void loadTag(CompoundTag tag) {
		if (tag.contains("dcs.yellow"))
			thresholdYellow = tag.getInt("dcs.yellow");
		if (tag.contains("dcs.red"))
			thresholdRed = tag.getInt("dcs.red");

		if (tag.contains("dcs.inv_mode"))
			isInventoryMode = tag.getBoolean("dcs.inv_mode");
		if (tag.contains("dcs.alart_mode"))
			isAlartMode = tag.getBoolean("dcs.alart_mode");
		super.loadTag(tag);
	}

	@Override
	public void writeTag(CompoundTag tag) {
		tag.putInt("dcs.yellow", thresholdYellow);
		tag.putInt("dcs.red", thresholdRed);
		tag.putBoolean("dcs.inv_mode", isInventoryMode);
		tag.putBoolean("dcs.alart_mode", isAlartMode);
		super.writeTag(tag);
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return MonitorAndonMenu.getMenu(i, inv, this);
	}

	@Override
	public boolean hasMenu() {
		return true;
	}

}
