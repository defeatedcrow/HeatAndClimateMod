package defeatedcrow.hac.main.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class DCChunkloadContoroller implements LoadingCallback {

	private static DCChunkloadContoroller instance;

	// 稼働中のチケット
	protected static final HashMap<DCCoordinate, Ticket> ticketList = new HashMap<DCCoordinate, Ticket>();
	// 発行済みの全チケット
	protected static final ArrayList<Ticket> allTicket = new ArrayList<Ticket>();

	private DCChunkloadContoroller() {}

	public static DCChunkloadContoroller getInstance() {
		if (instance == null) {
			instance = new DCChunkloadContoroller();
		}
		return instance;
	}

	public void preInit() {
		ForgeChunkManager.setForcedChunkLoadingCallback(ClimateMain.instance, instance);
	}

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		for (Ticket t : tickets) {

			if (this.isBlockTicket(t)) {

				int x = t.getModData().getInteger("x");
				int y = t.getModData().getInteger("y");
				int z = t.getModData().getInteger("z");

				int i = t.getModData().getInteger("i");
				int j = t.getModData().getInteger("j");
				int d = t.getModData().getInteger("d");

				DCCoordinate cood = new DCCoordinate(i, j, d);
				BlockPos pos = new BlockPos(x, y, z);

				if (this.getBlock(t, world) instanceof IChunkBlock) {
					IChunkBlock block = (IChunkBlock) this.getBlock(t, world);
					if (block.canLoad(world, x, y, z)) {
						setBlockTicket(world, x, y, z, i, j, d);
					} else {
						deleteBlockTicket(world, x, y, z, i, j, d);
					}
				} else {
					deleteBlockTicket(world, x, y, z, i, j, d);
				}
			}
		}
	}

	/** 指定した座礁のブロックをChunkLoaderとして起動する */
	public static boolean setBlockTicket(World world, int x, int y, int z, int i, int j, int d) {

		DCCoordinate cood = new DCCoordinate(i, j, d);
		boolean b = false;

		Ticket t = ForgeChunkManager.requestTicket(ClimateMain.instance, world, Type.NORMAL);

		if (t == null)
			return false;

		setBlockType(t);
		setBlock(t, x, y, z, i, j, d);
		ticketList.put(cood, t);
		ForgeChunkManager.forceChunk(t, world.getChunkFromChunkCoords(i, j).getChunkCoordIntPair());
		DCLogger.infoLog("Succeed to register chunkloader. Cood: " + i + ", " + j);
		return true;
	}

	/** 指定した座礁のChunkLoaderを停止する */
	public static void deleteBlockTicket(World world, int x, int y, int z, int i, int j, int d) {

		DCCoordinate cood = new DCCoordinate(i, j, d);

		if (ticketList.containsKey(cood)) {

			if (!ForgeChunkManager.getPersistentChunksFor(ticketList.get(cood).world).isEmpty()) {
				ForgeChunkManager.unforceChunk(ticketList.get(cood),
						world.getChunkFromChunkCoords(i, j).getChunkCoordIntPair());
				ForgeChunkManager.releaseTicket(ticketList.get(cood));
			}

			ticketList.remove(cood);
			DCLogger.infoLog("Succeed to delete chunkloader. Cood: " + i + ", " + j);
		}
	}

	public static void setBlockType(Ticket ticket) {
		ticket.getModData().setString("type", "dcblock");
	}

	public boolean isBlockTicket(Ticket ticket) {
		return ticket.getModData().getString("type").equals("dcblock");
	}

	public static void setBlock(Ticket ticket, int x, int y, int z, int i, int j, int d) {
		ticket.getModData().setInteger("x", x);
		ticket.getModData().setInteger("y", y);
		ticket.getModData().setInteger("z", z);
		ticket.getModData().setInteger("i", i);
		ticket.getModData().setInteger("j", j);
		ticket.getModData().setInteger("d", d);
	}

	public Block getBlock(Ticket ticket, World world) {
		BlockPos pos = new BlockPos(ticket.getModData().getInteger("x"), ticket.getModData().getInteger("y"),
				ticket.getModData().getInteger("z"));
		return world.getBlockState(pos).getBlock();
	}

	// Ticketの使い回し
	private static Ticket getTicketFromList(DCCoordinate cood) {
		if (allTicket.isEmpty())
			return null;
		for (Ticket t : allTicket) {
			int i = t.getModData().getInteger("i");
			int j = t.getModData().getInteger("j");
			int d = t.getModData().getInteger("d");

			DCCoordinate cood1 = new DCCoordinate(i, j, d);

			if (cood1.equals(cood))
				return t;
		}
		return null;
	}

	/**
	 * ChunkLoaderに実装するinterface<br>
	 * worldがロードされた時に呼ばれる。trueを返すとChunkLoaderが始まる。
	 */
	public interface IChunkBlock {

		public boolean canLoad(World world, int x, int y, int z);

	}

}
