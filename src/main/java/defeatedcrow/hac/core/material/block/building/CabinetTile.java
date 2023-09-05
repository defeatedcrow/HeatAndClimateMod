package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IRenderBlockData;
import defeatedcrow.hac.core.client.gui.SimpleInventoryMenu;
import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 汎用チェストベースクラス<br>
 * - Owner登録なし、ドロップのNBT保存なし、開閉アニメーションあり<br>
 * - 54スロット
 */
public class CabinetTile extends SimpleChestDC implements IRenderBlockData {

	public CabinetTile(BlockPos pos, BlockState state) {
		super(BuildInit.CABINET_TILE.get(), pos, state);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == BuildInit.CABINET_BLUE.get())
			return BLUE;
		if (block == BuildInit.CABINET_BLACK.get())
			return BLACK;
		if (block == BuildInit.CABINET_RED.get())
			return RED;
		if (block == BuildInit.CABINET_GREEN.get())
			return GREEN;
		if (block == BuildInit.CABINET_WHITE.get())
			return WHITE;
		if (block == BuildInit.CABINET_NORMAL.get())
			return NORMAL;
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/cabinet_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/cabinet_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/cabinet_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/cabinet_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/cabinet_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/cabinet_normal", 1F, -0.5F);

	// anim

	protected final ChestLidController chestLidController = new ChestLidController();

	public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, CabinetTile tile) {
		tile.chestLidController.tickLid();
	}

	@Override
	public float getOpenNess(float f) {
		return this.chestLidController.getOpenness(f);
	}

	@Override
	public boolean triggerEvent(int i1, int i2) {
		if (i1 == 1) {
			this.chestLidController.shouldBeOpen(i2 > 0);
			return true;
		} else {
			return super.triggerEvent(i1, i2);
		}
	}

	@Override
	public int getContainerSize() {
		return 54;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.double.with_owner", this.ownerName) : Component.translatable("dcs.container.double");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return SimpleInventoryMenu.unlockedMenu(i, inv, this);
	}

	@Override
	public boolean lock(boolean lock) {
		return false;
	}

	@Override
	public boolean toggleLock() {
		return false;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public boolean canOpen(Player player) {
		return true;
	}

	@Override
	public boolean isOwner(Player player) {
		return true;
	}
}
