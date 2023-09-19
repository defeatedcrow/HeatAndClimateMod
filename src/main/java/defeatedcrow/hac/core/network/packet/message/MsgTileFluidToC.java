package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import defeatedcrow.hac.machine.material.fluid.IFluidTankTileDC;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgTileFluidToC implements IPacketDC {

	protected int x;
	protected int y;
	protected int z;
	protected NonNullList<FluidStack> list = NonNullList.withSize(3, FluidStack.EMPTY);

	public MsgTileFluidToC() {}

	public MsgTileFluidToC(BlockPos pos, NonNullList<FluidStack> fluids) {
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		for (int i = 0; i < fluids.size(); i++) {
			list.set(i, fluids.get(i).copy());
			// DCLogger.debugInfoLog("tank -> packet " + i + "," + fluids.get(i).getTranslationKey());
		}
	}

	public MsgTileFluidToC(int i1, int i2, int i3, NonNullList<FluidStack> fluids) {
		x = i1;
		y = i2;
		z = i3;
		for (int i = 0; i < fluids.size(); i++) {
			list.set(i, fluids.get(i).copy());
			// DCLogger.debugInfoLog("tank -> packet2 " + i + "," + fluids.get(i).getTranslationKey());
		}
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		list.get(0).writeToPacket(buf);
		list.get(1).writeToPacket(buf);
		list.get(2).writeToPacket(buf);
	}

	public static MsgTileFluidToC decode(FriendlyByteBuf buf) {
		int x1 = buf.readInt();
		int y1 = buf.readInt();
		int z1 = buf.readInt();
		NonNullList<FluidStack> ret = NonNullList.withSize(3, FluidStack.EMPTY);
		ret.set(0, FluidStack.readFromPacket(buf));
		ret.set(1, FluidStack.readFromPacket(buf));
		ret.set(2, FluidStack.readFromPacket(buf));
		return new MsgTileFluidToC(x1, y1, z1, ret);
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist == Dist.CLIENT) {
			BlockPos pos = new BlockPos(x, y, z);
			Level level = Minecraft.getInstance().level;
			BlockEntity entity = level.getBlockEntity(pos);
			if (entity instanceof IFluidTankTileDC handler) {
				for (int i = 0; i < handler.getTanks(); i++) {
					handler.getTank(i).setFluid(list.get(i));
					// DCLogger.debugInfoLog("packet -> tank " + i + "," + list.get(i).getTranslationKey());
				}
			}
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, NonNullList<FluidStack> fluids) {
		if (level != null) {
			MsgTileFluidToC packet = new MsgTileFluidToC(pos, fluids);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

}
