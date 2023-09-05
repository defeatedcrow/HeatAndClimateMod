package defeatedcrow.hac.core.material.block.building;

import defeatedcrow.hac.core.client.gui.SimpleInventoryMenu;
import defeatedcrow.hac.core.material.block.InventoryDC;
import defeatedcrow.hac.core.material.block.OwnableContainerBaseTileDC;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 汎用チェストベースクラス<br>
 * - Owner登録、開閉アニメーションあり<br>
 * - 27スロット
 */
public abstract class SimpleChestDC extends OwnableContainerBaseTileDC implements LidBlockEntity {

	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level level, BlockPos pos, BlockState state) {
			level.playSound((Player) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
		}

		@Override
		protected void onClose(Level level, BlockPos pos, BlockState state) {
			level.playSound((Player) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
		}

		@Override
		protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int i1, int i2) {
			SimpleChestDC.this.signalOpenCount(level, pos, state, i1, i2);
		}

		@Override
		protected boolean isOwnContainer(Player player) {
			if (!(player.containerMenu instanceof SimpleInventoryMenu)) {
				return false;
			} else {
				SimpleChestDC container = ((SimpleInventoryMenu) player.containerMenu).getContainer();
				return container == SimpleChestDC.this;
			}
		}
	};

	public SimpleChestDC(BlockEntityType<?> tile, BlockPos pos, BlockState state) {
		super(tile, pos, state);
	}

	public InventoryDC inventory = new InventoryDC(this.getContainerSize(), this);

	@Override
	public InventoryDC getInventory() {
		return inventory;
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	protected Component getDefaultName() {
		return this.hasOwner() ? Component.translatable("dcs.container.simple.with_owner", this.ownerName) : Component.translatable("dcs.container.simple");
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return SimpleInventoryMenu.singleMenu(i, inv, this);
	}

	@Override
	public void startOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public static int getOpenCount(BlockGetter level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		if (state.hasBlockEntity()) {
			BlockEntity tile = level.getBlockEntity(pos);
			if (tile instanceof SimpleChestDC) {
				return ((SimpleChestDC) tile).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int i1, int i2) {
		Block block = state.getBlock();
		level.blockEvent(pos, block, 1, i2);
	}

	// anim
	@Override
	public abstract float getOpenNess(float f);

}
