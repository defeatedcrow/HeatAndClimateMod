package defeatedcrow.hac.magic.client.gui;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class BoringMenu extends AbstractContainerMenu {

	private final ItemStack container;

	public ItemStack getContainer() {
		return container;
	}

	public static BoringMenu getMenu(int i, ItemStack cont, Player player) {
		return new BoringMenu(MagicInit.BORING_SURVEY_MENU.get(), i, cont, player);
	}

	public BoringMenu(MenuType<?> type, int s, ItemStack cont, Player player) {
		super(type, s);
		container = player.getMainHandItem().copy();
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

}
