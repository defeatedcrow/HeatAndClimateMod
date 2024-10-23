package defeatedcrow.hac.core.network.packet.message;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.network.packet.DCPacket;
import defeatedcrow.hac.core.network.packet.IPacketDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class MsgEffectToC implements IPacketDC {

	protected float x;
	protected float y;
	protected float z;
	protected int type;

	public MsgEffectToC() {}

	public MsgEffectToC(BlockPos pos, int i) {
		x = pos.getX() + 0.5F;
		y = pos.getY() + 0.5F;
		z = pos.getZ() + 0.5F;
		type = i;
	}

	public MsgEffectToC(Vec3 pos, int i) {
		x = (float) pos.x;
		y = (float) pos.y;
		z = (float) pos.z;
		type = i;
	}

	public MsgEffectToC(float f1, float f2, float f3, int t) {
		x = f1;
		y = f2;
		z = f3;
		type = t;
	}

	@Override
	public void encode(FriendlyByteBuf buf) {
		buf.writeFloat(x);
		buf.writeFloat(y);
		buf.writeFloat(z);
		buf.writeInt(type);
	}

	public static MsgEffectToC decode(FriendlyByteBuf buf) {
		float x1 = buf.readFloat();
		float y1 = buf.readFloat();
		float z1 = buf.readFloat();
		int t = buf.readInt();
		return new MsgEffectToC(x1, y1, z1, t);
	}

	public static void sendToClient(ServerLevel level, Vec3 pos, int i) {
		if (level != null) {
			MsgEffectToC packet = new MsgEffectToC(pos, i);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

	public static void sendToClient(ServerLevel level, BlockPos pos, int i) {
		if (level != null) {
			MsgEffectToC packet = new MsgEffectToC(pos, i);
			level.players().forEach(player -> {
				DCPacket.INSTANCE.getChannel().sendTo(packet, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			});
		}
	}

	@Override
	public void handle(NetworkEvent.Context ctx) {
		if (FMLEnvironment.dist.isClient() && ClimateCore.proxy.getClientLevel().isPresent()) {
			Vec3 pos = new Vec3(x, y, z);
			Level level = ClimateCore.proxy.getClientLevel().get();
			Player player = ClimateCore.proxy.getClientPlayer().orElse(null);
			int particle = type % 10;
			int se = type / 10;
			switch (particle) {
			case 0: // No Particle
				break;
			case 1: // sparkle splash
				for (int i = 0; i < 5; i++) {
					double d0 = -0.5D + level.getRandom().nextDouble();
					double d1 = -0.5D + level.getRandom().nextDouble();
					double d2 = -0.5D + level.getRandom().nextDouble();
					level.addParticle(CoreInit.SPARKLE.get(), x + d0, y + d1, z + d2, d0 * 0.02D, d1 * 0.02D, d2 * 0.02D);
				}
				break;
			case 2: // orb
				for (int i = 0; i < 5; i++) {
					double d0 = -0.5D + level.getRandom().nextDouble();
					double d1 = -0.5D + level.getRandom().nextDouble();
					double d2 = -0.5D + level.getRandom().nextDouble();
					level.addParticle(CoreInit.LIGHT_ORB_WHITE.get(), x + d0, y + d1, z + d2, 0.0D, 0.01D, 0.0D);
				}
				break;
			case 3: // Bread Particle
				level.addParticle(CoreInit.FOOD_PARTICLE.get(), x, y, z, 0.0D, 0.0D, 0.0D);
				break;
			case 4: // Smoke Particle
				level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
				break;
			case 5: // Explosion Particle
				level.addParticle(ParticleTypes.EXPLOSION, x, y, z, 1.0D, 0.0D, 0.0D);
				break;
			case 6: // Small Smoke Particle
				level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.05D, 0.0D);
				break;
			}
			switch (se) {
			case 0: // No SE
				break;
			case 1: // Shears SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			case 2: // Fire SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			case 3: // Magic SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.5F);
				break;
			case 4: // POP SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.5F);
				break;
			case 5: // BOMB SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.0F);
				break;
			case 6: // FUSE SE
				level.playSound(player, pos.x, pos.y, pos.z, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
				break;
			}
		}
	}

}
