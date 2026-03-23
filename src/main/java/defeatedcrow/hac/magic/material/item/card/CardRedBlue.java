package defeatedcrow.hac.magic.material.item.card;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.magic.MagicColor;
import defeatedcrow.hac.core.material.entity.ChairEntity;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class CardRedBlue extends MagicCardBase {

	public CardRedBlue() {
		super(MagicColor.RED_BLUE, Rarity.COMMON, TagDC.ItemTag.MAGIC_CARD);
	}

	@Override
	public boolean onUsing(Level level, Player player, BlockPos pos, Direction dir, ItemStack card, float f) {
		float boost = f < 0 ? 0.5F : f;
		double d = 32D + 16D * f;

		List<Mob> list = level.getNearbyEntities(Mob.class, TargetingConditions.forCombat().range(d).ignoreLineOfSight(), player, player.getBoundingBox().inflate(d));
		list.stream().filter(mob -> mob instanceof Enemy && !mob.getType().is(Tags.EntityTypes.BOSSES) || mob.isInWater()).forEach(mob -> {
			mob.hurt(DamageSource.LIGHTNING_BOLT, 12.0F * boost);
			if (mob.getVehicle() != null) {
				mob.removeVehicle();
			}
			ChairEntity bind = MagicInit.BIND_ELECTRIC_ENTITY.get().create(level);
			bind.setPos(mob.position());
			bind.setDeltaMovement(0D, 0D, 0D);
			bind.setMaxAge(Mth.floor(200 * boost));
			bind.setOwner(player.getUUID());
			mob.startRiding(bind);
			level.addFreshEntity(bind);
		});

		if (player.isInWater() && !level.isClientSide) {
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			boolean flag = false;
			int chance = Mth.ceil(8 * boost);
			for (int c = 0; c < chance; c++) {
				int x = level.getRandom().nextInt(5) * DCUtil.getRandomSign();
				int y = level.getRandom().nextInt(2) * DCUtil.getRandomSign();
				int z = level.getRandom().nextInt(5) * DCUtil.getRandomSign();
				mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
				if (level.getFluidState(mpos).isSourceOfType(Fluids.WATER)) {
					List<ItemStack> replace = Lists.newArrayList();
					Holder<Biome> biome = level.getBiome(pos);
					replace.addAll(DCUtil.getFish(level, biome, player, card));
					if (!replace.isEmpty()) {
						ItemStack fish = ItemStack.EMPTY;
						if (replace.size() > 1) {
							int i = level.random.nextInt(replace.size());
							fish = replace.get(i).copy();
						} else {
							fish = replace.get(0).copy();
						}

						ItemEntity drop = new ItemEntity(level, mpos.getX() + 0.5D, mpos.getY() + 0.5D, mpos.getZ() + 0.5D, fish);
						double d0 = 0D;
						double d1 = 0.1D;
						double d2 = 0D;
						drop.setDeltaMovement(d0, d1, d2);
						level.addFreshEntity(drop);
					}
				}
			}

			// 自分に落ちる
			LightningBolt thunder = EntityType.LIGHTNING_BOLT.create(level);
			thunder.moveTo(player.position());
			level.addFreshEntity(thunder);

		} else {
			BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
			boolean flag = false;
			int range = 8 + Mth.floor(boost);
			for (int x = -range; x < range; x++) {
				for (int z = -range; z < range; z++) {
					for (int y = 0; y < range * 2; y++) {
						mpos.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
						if (mpos.getY() > level.getMinBuildHeight() && mpos.getY() < level.getMaxBuildHeight()) {
							BlockState state = level.getBlockState(mpos);
							if (state.getBlock() instanceof LightningRodBlock) {
								LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
								lightningbolt.moveTo(Vec3.atBottomCenterOf(mpos.above()));
								lightningbolt.setCause(player instanceof ServerPlayer s ? s : null);
								level.addFreshEntity(lightningbolt);
								flag = true;
							}
						}
					}
				}
			}
			if (!flag) {
				for (int i = 0; i < 3; i++) {
					double d1 = 2.0D + level.random.nextDouble() * 6.0D * DCUtil.getRandomSign();
					double d2 = 2.0D + level.random.nextDouble() * 6.0D * DCUtil.getRandomSign();
					LightningBolt thunder = EntityType.LIGHTNING_BOLT.create(level);
					thunder.moveTo(player.position().add(d1, 0D, d2));
					level.addFreshEntity(thunder);
					flag = true;
				}
			}
			player.playSound(SoundEvents.TRIDENT_THUNDER, 2.0F, 1.0F);
		}

		return true;
	}

}
