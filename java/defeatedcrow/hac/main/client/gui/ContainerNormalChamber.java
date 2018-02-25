package defeatedcrow.hac.main.client.gui;

import defeatedcrow.hac.core.client.base.ContainerBaseDC;
import defeatedcrow.hac.main.block.device.TileChamberBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerNormalChamber extends ContainerBaseDC {

	public final TileChamberBase chamber;
	public final InventoryPlayer player;

	private int currentBurn;
	private int maxBurn;
	private int heatTier;

	public ContainerNormalChamber(TileChamberBase cham, InventoryPlayer playerInv) {
		this.chamber = cham;
		this.player = playerInv;

		this.addSlotToContainer(new Slot(cham, 0, 80, 58));

		this.addSlotToContainer(new SlotInvalid(cham, 1, 134, 21));
		this.addSlotToContainer(new SlotInvalid(cham, 2, 134, 39));
		this.addSlotToContainer(new SlotInvalid(cham, 3, 134, 57));

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlotToContainer(new Slot(playerInv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInv, l, 8 + l * 18, 142));
		}
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.chamber);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icrafting = this.listeners.get(i);

			if (this.heatTier != this.chamber.getField(2)) {
				icrafting.sendProgressBarUpdate(this, 2, this.chamber.getField(2));
			}

			if (this.currentBurn != this.chamber.getField(0)) {
				icrafting.sendProgressBarUpdate(this, 0, this.chamber.getField(0));
			}

			if (this.maxBurn != this.chamber.getField(1)) {
				icrafting.sendProgressBarUpdate(this, 1, this.chamber.getField(1));
			}
		}

		this.heatTier = this.chamber.getField(2);
		this.currentBurn = this.chamber.getField(0);
		this.maxBurn = this.chamber.getField(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.chamber.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.chamber.isUseableByPlayer(playerIn);
	}

	@Override
	protected int inputMinIndex() {
		return 0;
	}

	@Override
	protected int inputMaxIndex() {
		return 2;
	}

	@Override
	protected int slotIndex() {
		return 3;
	}

}
