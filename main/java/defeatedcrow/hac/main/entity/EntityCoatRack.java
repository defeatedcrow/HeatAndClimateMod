package defeatedcrow.hac.main.entity;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCoatRack extends EntityArmorStand {

	public EntityCoatRack(World worldIn) {
		super(worldIn);
	}

	public EntityCoatRack(World worldIn, double posX, double posY, double posZ) {
		this(worldIn);
		this.setPosition(posX, posY, posZ);
	}

	/* 着用中の防具と入れ替える */
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {

		if (!this.hasMarker()) {
			if (!this.world.isRemote && !player.isSpectator()) {
				EntityEquipmentSlot head = EntityEquipmentSlot.HEAD;
				EntityEquipmentSlot chest = EntityEquipmentSlot.CHEST;

				swapItem(player, head);
				swapItem(player, chest);
			}
		}
		return EnumActionResult.SUCCESS;
	}

	private void swapItem(EntityPlayer player, EntityEquipmentSlot slot) {
		ItemStack current = this.getItemStackFromSlot(slot).isEmpty() ? ItemStack.EMPTY : this
				.getItemStackFromSlot(slot).copy();
		ItemStack target = player.getItemStackFromSlot(slot).isEmpty() ? ItemStack.EMPTY : player
				.getItemStackFromSlot(slot).copy();

		player.setItemStackToSlot(slot, current);
		player.inventory.markDirty();
		this.setItemStackToSlot(slot, target);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (DamageSource.OUT_OF_WORLD.equals(source)) {
			this.setDead();
			return false;
		} else if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source.isFireDamage() || source.isMagicDamage() || source.isExplosion()) {
			return false;
		} else if (source instanceof EntityDamageSource) {
			this.markVelocityChanged();
			dropAsItem(posX, posY + 0.25D, posZ);
			dropContents(EntityEquipmentSlot.MAINHAND);
			dropContents(EntityEquipmentSlot.OFFHAND);
			dropContents(EntityEquipmentSlot.HEAD);
			dropContents(EntityEquipmentSlot.CHEST);
			dropContents(EntityEquipmentSlot.LEGS);
			dropContents(EntityEquipmentSlot.FEET);
			this.setDead();
		}
		return false;
	}

	public ItemStack getDropItem() {
		return new ItemStack(MainInit.coatRack, 1, 0);
	}

	protected void dropAsItem(double x, double y, double z) {
		if (!world.isRemote && !DCUtil.isEmpty(getDropItem())) {
			ItemStack item = this.getDropItem();
			EntityItem drop = new EntityItem(world, x, y, z, item);
			drop.motionY = 0.025D;
			world.spawnEntity(drop);
		}
	}

	private void dropContents(EntityEquipmentSlot slot) {
		if (!this.getItemStackFromSlot(slot).isEmpty()) {
			ItemStack item = this.getItemStackFromSlot(slot);
			Block.spawnAsEntity(this.world, (new BlockPos(this)).up(), item);
			this.setItemStackToSlot(slot, ItemStack.EMPTY);
		}
	}

}
