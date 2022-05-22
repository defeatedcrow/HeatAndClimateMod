package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.block.build.TileDisplayShopCase;
import defeatedcrow.hac.main.block.build.TileDisplayVendingMachine;
import defeatedcrow.hac.main.config.MainCoreConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerDisplayCaseButton implements IMessageHandler<MessageDisplayCaseButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageDisplayCaseButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			BlockPos pos = new BlockPos(x, y, z);
			if (player.world.getTileEntity(pos) != null) {
				TileEntity t = player.world.getTileEntity(pos);
				if (t instanceof TileDisplayShopCase) {
					TileDisplayShopCase target = (TileDisplayShopCase) t;
					byte mode = message.num;
					short eme = message.emerald;

					if (mode == 0 && target.price1 < 64) {
						// item1 up
						target.price1++;
					}
					if (mode == 1 && target.price1 > 0) {
						// item1 down
						target.price1--;
					}
					if (mode == 2 && target.price2 < 64) {
						// item2 up
						target.price2++;
					}
					if (mode == 3 && target.price2 > 0) {
						// item2 down
						target.price2--;
					}
					if (mode == 4) {
						// item1 buy
						ItemStack ret = target.getStackInSlot(0).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(0, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
					if (mode == 5) {
						// item2 buy
						ItemStack ret = target.getStackInSlot(1).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(1, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
				} else if (t instanceof TileDisplayVendingMachine) {
					TileDisplayVendingMachine target = (TileDisplayVendingMachine) t;
					byte mode = message.num;
					short eme = message.emerald;

					if (mode == 0 && target.price1 < 64) {
						// item1 up
						target.price1++;
					}
					if (mode == 1 && target.price1 > 0) {
						// item1 down
						target.price1--;
					}
					if (mode == 2 && target.price2 < 64) {
						// item2 up
						target.price2++;
					}
					if (mode == 3 && target.price2 > 0) {
						// item2 down
						target.price2--;
					}
					if (mode == 4 && target.price3 < 64) {
						// item1 up
						target.price3++;
					}
					if (mode == 5 && target.price3 > 0) {
						// item1 down
						target.price3--;
					}
					if (mode == 6 && target.price4 < 64) {
						// item2 up
						target.price4++;
					}
					if (mode == 7 && target.price4 > 0) {
						// item2 down
						target.price4--;
					}
					if (mode == 8) {
						// item1 buy
						ItemStack ret = target.getStackInSlot(0).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(0, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
					if (mode == 9) {
						// item2 buy
						ItemStack ret = target.getStackInSlot(1).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(1, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
					if (mode == 10) {
						// item3 buy
						ItemStack ret = target.getStackInSlot(2).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(2, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
					if (mode == 11) {
						// item4 buy
						ItemStack ret = target.getStackInSlot(3).copy();
						if (!DCUtil.isEmpty(ret)) {
							ret.setCount(1);
							if (!DCUtil.isEmpty(target.decrStackSize(3, 1))) {
								target.addEmerald(eme);
								reduceEmerald(player, eme);
								EntityItem drop = new EntityItem(player.world, player.posX, player.posY + 0.25D, player.posZ, ret);
								player.world.spawnEntity(drop);
							}
						}
					}
				}
			}
		}
		return null;
	}

	protected void reduceEmerald(EntityPlayer player, int count) {
		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			ItemStack check = player.inventory.getStackInSlot(i);
			if (!DCUtil.isEmpty(check) && DCUtil.isSameItem(check, MainCoreConfig.currency.getSingleStack(), false)) {
				int c1 = check.getCount();
				if (c1 <= count) {
					count -= c1;
					player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
					player.inventoryContainer.detectAndSendChanges();
				} else {
					player.inventory.getStackInSlot(i).splitStack(count);
					player.inventoryContainer.detectAndSendChanges();
					count = 0;
				}
			}
			if (count <= 0) {
				break;
			}
		}
	}
}
