package defeatedcrow.hac.main.packet;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
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
			double x = message.x;
			double y = message.y;
			double z = message.z;
			for (int i = 0; i < 8; i++) {
				double x1 = player.world.rand.nextDouble() * 1.5D - 0.75D;
				double y1 = 0.25D + player.world.rand.nextDouble();
				double z1 = player.world.rand.nextDouble() * 1.5D - 0.75D;

				Particle p = new ParticleBlink.Factory()
						.createParticle(0, player.world, x + x1, y + y1, z + z1, 0.0D, 0.0D, 0.0D, new int[0]);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
			}
		}
		return null;
	}
}
