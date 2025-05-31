package defeatedcrow.hac.core.client.event;

import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class SoundMufflerEvent {

	private static boolean hasSoundMuffler = false;

	public static void setMuffler(boolean b) {
		hasSoundMuffler = b;
	}

	@SubscribeEvent
	public static void onClientPlaySound(PlaySoundEvent event) {
		if (hasSoundMuffler) {
			SoundInstance sound = event.getOriginalSound();
			if (sound != null && sound instanceof SimpleSoundInstance
					&& sound.getSource() != SoundSource.MASTER
					&& sound.getSource() != SoundSource.AMBIENT) {
				// mute
				SimpleSoundInstance newinstance = new SimpleSoundInstance(sound.getLocation(), sound.getSource(), 0F, 1.0F, SoundInstance.createUnseededRandom(), sound.isLooping(), sound
						.getDelay(), sound.getAttenuation(), sound.getX(), sound.getY(), sound.getZ(), sound.isRelative());
				event.setSound(newinstance);
			}
		}

	}

}
