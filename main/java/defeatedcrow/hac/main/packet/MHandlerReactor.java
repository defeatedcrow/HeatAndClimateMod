package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.machine.block.TileReactor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerReactor implements IMessageHandler<MessageReactor, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageReactor message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			int x = message.x;
			int y = message.y;
			int z = message.z;
			String id1 = message.id1;
			int amo1 = message.amo1;
			String id2 = message.id2;
			int amo2 = message.amo2;
			String id3 = message.id3;
			int amo3 = message.amo3;
			String id4 = message.id4;
			int amo4 = message.amo4;
			byte s1 = message.side1;
			byte s2 = message.side2;
			byte s3 = message.side3;
			byte s4 = message.side4;
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tile = player.world.getTileEntity(pos);
			if (tile instanceof TileReactor) {
				TileReactor machine = (TileReactor) tile;
				machine.inputT1.setAmount(amo1);
				machine.inputT1.setFluidByIdName(id1);
				machine.inputT1.setAmount(amo2);
				machine.inputT2.setFluidByIdName(id2);
				machine.outputT1.setAmount(amo3);
				machine.outputT1.setFluidByIdName(id3);
				machine.outputT2.setAmount(amo4);
				machine.outputT2.setFluidByIdName(id4);
				machine.tankSide1 = EnumSide.fromIndex(s1);
				machine.tankSide2 = EnumSide.fromIndex(s2);
				machine.tankSide3 = EnumSide.fromIndex(s3);
				machine.tankSide4 = EnumSide.fromIndex(s4);
			}
		}
		return null;
	}
}
