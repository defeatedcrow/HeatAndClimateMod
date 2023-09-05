package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.client.gui.SimpleInventoryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 汎用チェストベースクラス<br>
 * - Owner登録、開閉アニメーションあり<br>
 * - 54スロット
 */
public abstract class DoubleChestDC extends SimpleChestDC {

	public DoubleChestDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	@Override
	public int getContainerSize() {
		return 54;
	}

	@Override
	public int[] getSlotsForFace(Direction dir) {
		return doubleSlots;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.double.with_owner", this.ownerName) : Component.translatable("dcs.container.double");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return SimpleInventoryMenu.doubleMenu(i, inv, this);
	}
}
