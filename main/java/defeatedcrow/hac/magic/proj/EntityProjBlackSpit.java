package defeatedcrow.hac.magic.proj;

import java.util.List;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.entity.EntityProjBase;
import defeatedcrow.hac.main.util.EntitySelectorsDC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class EntityProjBlackSpit extends EntityProjBase {

	public float damage = 10.0F;
	public float range = 4.0F;

	public EntityProjBlackSpit(World worldIn) {
		super(worldIn);
	}

	public EntityProjBlackSpit(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityProjBlackSpit(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
	}

	public EntityProjBlackSpit setExplodeRange(float f) {
		range = 4.0F * f;
		return this;
	}

	@Override
	public ItemStack getDropStack() {
		return ItemStack.EMPTY;
	}

	@Override
	protected ItemStack getArrowStack() {
		return getDropStack();
	}

	// 爆発発生

	@Override
	protected boolean onGroundHit() {
		AxisAlignedBB aabb = new AxisAlignedBB(posX - 1, posY - 1, posZ - 1, posX + 1, posY + 1, posZ + 1);
		List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityMob.class, aabb, EntitySelectorsDC.NOT_TAMED);
		if (list.isEmpty()) {
			this.setDead();
			return false;
		} else {
			for (EntityLivingBase mob : list) {
				if (mob.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
					IItemHandler handler = mob.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
					for (int i = 0; i < handler.getSlots(); i++) {
						ItemStack item = handler.extractItem(i, 64, false);
						if (!DCUtil.isEmpty(item)) {
							EntityItem drop = new EntityItem(mob.world, mob.posX, mob.posY + 0.5F, mob.posZ, item);
							drop.motionX = world.rand.nextFloat() * 0.5F - 0.25F;
							drop.motionZ = world.rand.nextFloat() * 0.5F - 0.25F;
							drop.motionY = world.rand.nextFloat() * 0.25F + 0.15F;
							drop.setDefaultPickupDelay();
							world.spawnEntity(drop);
						}
					}
				}
			}
		}
		this.setDead();
		return true;
	}

	@Override
	protected boolean onEntityHit(Entity entity) {
		setStart(true);
		this.inGround = true;
		return true;
	}

	@Override
	protected void onGroundClient() {}

	// no gravity
	@Override
	public boolean hasNoGravity() {
		return true;
	}

	@Override
	public boolean isInWater() {
		return false;
	}

}
