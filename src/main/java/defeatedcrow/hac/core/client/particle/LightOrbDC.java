package defeatedcrow.hac.core.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LightOrbDC extends BaseAshSmokeParticle {

	private final double movY;

	protected LightOrbDC(ClientLevel level, double x, double y, double z, double sx, double sy, double sz, float f, SpriteSet sprite) {
		super(level, x, y, z, 0.0F, 0.1F, 0.0F, sx, sy, sz, f, sprite, 1.0F, 8, 0F, true);
		this.rCol = 1.0F;
		this.gCol = 1.0F;
		this.bCol = 1.0F;
		this.lifetime = 5 + level.random.nextInt(8);
		this.lifetime = Math.max(this.lifetime, 1);
		movY = this.yd;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@Override
	public float getQuadSize(float p) {
		float count = this.lifetime - this.age - p;
		float r = 1F + this.random.nextFloat() * 0.1F;
		if (count > 8F) {
			return this.quadSize * r;
		} else {
			float a = r * count;
			return this.quadSize * Mth.clamp((a * a) / 64, 0.0F, this.quadSize);
		}
	}

	@Override
	public int getLightColor(float p) {
		float f = 0.9F + this.random.nextFloat() * 0.1F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		int i = super.getLightColor(p);
		int j = i & 255;
		int k = i >> 16 & 255;
		j += (int) (15.0F * 16.0F);
		if (j > 240) {
			j = 240;
		}

		return j | k << 16;
	}

	@Override
	public void tick() {
		super.tick();
		if (age > 8) {
			this.yd = movY;
		} else {
			this.yd = 0.01D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet sprite) {
			this.sprites = sprite;
		}

		@Override
		public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double sx, double sy, double sz) {
			return new LightOrbDC(level, x, y, z, sx, sy, sz, 1.0F, this.sprites);
		}
	}

}
