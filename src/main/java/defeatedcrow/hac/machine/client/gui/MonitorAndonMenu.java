package defeatedcrow.hac.machine.client.gui;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.monitor.MonitorAndonTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class MonitorAndonMenu extends AbstractContainerMenu {

	private final MonitorAndonTile container;
	public final boolean isOwner;

	private int h1 = 26;
	private int h2 = 100;
	private int h3 = 158;

	public MonitorAndonTile getContainer() {
		return container;
	}

	public static MonitorAndonMenu getMenu(int i, Inventory playerInv, MonitorAndonTile cont) {
		return new MonitorAndonMenu(MachineInit.MONITOR_ANDON_MENU.get(), i, playerInv, cont);
	}

	public MonitorAndonMenu(MenuType<?> type, int s, Inventory playerInv, MonitorAndonTile cont) {
		super(type, s);
		container = cont;
		isOwner = cont.isOwner(playerInv.player);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int s) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
	}

	public int getColor() {
		return DCState.getInt(container.getBlockState(), DCState.STAGE3);
	}

	public boolean isRSMode() {
		return !container.isInventoryMode;
	}

	public boolean isAlartMode() {
		return container.isAlartMode;
	}

	public BlockPos getPairPos() {
		return container.getPairPos();
	}

	public int getRedThreshold() {
		return container.thresholdRed;
	}

	public int getYellowThreshold() {
		return container.thresholdYellow;
	}

}
