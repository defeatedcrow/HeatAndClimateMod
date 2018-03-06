package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.block.TileConveyor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class MHandlerConveyor implements IMessageHandler<MessageConveyor, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageConveyor message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			byte ii1 = message.i1;
			short im1 = message.m1;
			String name1 = message.n1;
			Item item1 = ForgeRegistries.ITEMS.getValue(new ResourceLocation(name1));
			ItemStack ret1 = item1 == null ? null : new ItemStack(item1, 1, im1);
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tile = player.worldObj.getTileEntity(pos);
			if (tile instanceof TileConveyor) {
				TileConveyor te = (TileConveyor) tile;
				te.move = ii1;
				te.disp = ret1;
			}
		}
		return null;
	}
}
