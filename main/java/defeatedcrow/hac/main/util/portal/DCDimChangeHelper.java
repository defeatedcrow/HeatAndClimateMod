package defeatedcrow.hac.main.util.portal;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import defeatedcrow.hac.core.DCLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class DCDimChangeHelper {

	public static final DCDimChangeHelper INSTANCE = new DCDimChangeHelper();
	public static final Map<UUID, DCCoord> PLAYER_MAP = Maps.newHashMap();

	public static void addCoord(EntityPlayer player, int dim, BlockPos pos) {
		if (!player.world.isRemote && player.world instanceof WorldServer) {
			UUID id = player.getUniqueID();
			DCCoord c = INSTANCE.new DCCoord(dim, pos.getX(), pos.getY(), pos.getZ());
			INSTANCE.PLAYER_MAP.put(id, c);
		}
	}

	public static boolean inWarpProcess(EntityPlayer player) {
		if (!player.world.isRemote && player.world instanceof WorldServer) {
			UUID id = player.getUniqueID();
			DCCoord c = INSTANCE.PLAYER_MAP.get(id);
			return c != null;
		}
		return false;
	}

	public static boolean warp(EntityPlayer player) {
		if (!player.world.isRemote && player.world instanceof WorldServer) {
			UUID id = player.getUniqueID();
			DCCoord c = INSTANCE.PLAYER_MAP.get(id);
			if (c != null && player instanceof EntityPlayerMP) {
				EntityPlayerMP mp = (EntityPlayerMP) player;
				PlayerList playerList = mp.mcServer.getPlayerList();
				WorldServer worldServer = mp.mcServer.getWorld(c.dim);
				DCLogger.debugInfoLog("try warp: " + c.dim);
				playerList.transferPlayerToDimension(mp, c.dim, new DCPortal(worldServer));
				mp.connection.setPlayerLocation(c.x, c.y, c.z, mp.rotationYaw, mp.rotationPitch);
			}
			INSTANCE.PLAYER_MAP.remove(id);
		}
		return false;
	}

	public class DCCoord {
		final int dim;
		final int x;
		final int y;
		final int z;

		public DCCoord(int d, int xI, int yI, int zI) {
			dim = d;
			x = xI;
			y = yI;
			z = zI;
		}
	}
}
