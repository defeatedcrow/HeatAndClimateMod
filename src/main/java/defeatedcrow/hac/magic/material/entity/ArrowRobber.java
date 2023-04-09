package defeatedcrow.hac.magic.material.entity;

import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ArrowRobber extends AbstractArrow {

	private boolean dealtDamage;

	public ArrowRobber(EntityType<? extends ArrowRobber> type, Level level) {
		super(type, level);
	}

	public ArrowRobber(Level level, LivingEntity shooter) {
		super(MagicInit.ARROW_ROBBER_ENTITY.get(), shooter, level);
	}

	public ArrowRobber(Level level, double x, double y, double z) {
		super(MagicInit.ARROW_ROBBER_ENTITY.get(), x, y, z, level);
	}

	@Override
	public void tick() {
		if (this.dealtDamage || this.inGroundTime > 2 || this.getY() < 0 || this.isInLava()) {
			this.discard();
		}
		super.tick();

	}

	public void dropItem(Vec3 pos) {}

	private boolean isAcceptibleReturnOwner() {
		return false;
	}

	@Override
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec, Vec3 vec2) {
		return this.dealtDamage ? null : super.findHitEntity(vec, vec2);
	}

	@Override
	protected void onHitEntity(EntityHitResult res) {
		Entity entity = res.getEntity();
		Entity owner = this.getOwner();

		if (entity instanceof LivingEntity) {
			LivingEntity liv = (LivingEntity) entity;

			this.dealtDamage = true;
			if (liv != null) {
				List<EquipmentSlot> list = Lists.newArrayList();
				if (!liv.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
					list.add(EquipmentSlot.HEAD);
				}
				if (!liv.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
					list.add(EquipmentSlot.CHEST);
				}
				if (!liv.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
					list.add(EquipmentSlot.LEGS);
				}
				if (!liv.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
					list.add(EquipmentSlot.FEET);
				}
				if (list.size() > 0) {
					if (list.size() == 1) {
						ItemStack item = liv.getItemBySlot(list.get(0));
						if (!level.isClientSide && !item.isEmpty()) {
							ItemEntity drop = new ItemEntity(level, liv.position().x, liv.position().y + 0.1D, liv.position().z, item.copy());
							level.addFreshEntity(drop);
							liv.setItemSlot(list.get(0), ItemStack.EMPTY);
						}
					} else {
						int i = level.getRandom().nextInt(list.size());
						ItemStack item = liv.getItemBySlot(list.get(i));
						if (!level.isClientSide && !item.isEmpty()) {
							ItemEntity drop = new ItemEntity(level, liv.position().x, liv.position().y + 0.1D, liv.position().z, item.copy());
							level.addFreshEntity(drop);
							liv.setItemSlot(list.get(i), ItemStack.EMPTY);
						}

					}
				}
			}

			this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.discard();
				return;
			}
		}

		super.onHitEntity(res);
	}

	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected float getWaterInertia() {
		return 0.99F;
	}

}
