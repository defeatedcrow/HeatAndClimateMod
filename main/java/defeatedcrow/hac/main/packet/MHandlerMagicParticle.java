package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MHandlerMagicParticle implements IMessageHandler<MessageMagicParticle, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageMagicParticle message, MessageContext ctx) {
		EntityPlayer player = ClimateCore.proxy.getPlayer();
		if (player != null) {
			double x1 = message.x;
			double y1 = message.y;
			double z1 = message.z;
			byte id = message.num;
			double fx = message.dx;
			double fy = message.dy;
			double fz = message.dz;

			if (id == 50) {
				for (int i = 0; i < 8; i++) {
					double x2 = player.world.rand.nextDouble() * 1.5D - 0.75D;
					double y2 = 0.25D + player.world.rand.nextDouble();
					double z2 = player.world.rand.nextDouble() * 1.5D - 0.75D;

					Particle p = new ParticleBlink.Factory()
							.createParticle(0, player.world, x1 + x2, y1 + y2, z1 + z2, 0.0D, 0.0D, 0.0D, new int[0]);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
				}
			} else if (id < 50) {
				EnumParticleTypes type = EnumParticleTypes.getParticleFromId(id);
				if (type != null) {
					World world = ClimateCore.proxy.getClientWorld();
					world.spawnParticle(type, x1, y1, z1, fx, fy, fz, new int[0]);
				}
			}
		}
		return null;
	}
}
