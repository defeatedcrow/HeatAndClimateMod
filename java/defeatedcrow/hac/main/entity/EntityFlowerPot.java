package defeatedcrow.hac.main.entity;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class EntityFlowerPot extends DCEntityBase {

	private static final DataParameter<Optional<ItemStack>> FLOWER = EntityDataManager
			.<Optional<ItemStack>>createKey(DCEntityBase.class, DataSerializers.OPTIONAL_ITEM_STACK);
	private static final DataParameter<Boolean> COLOR = EntityDataManager.<Boolean>createKey(DCEntityBase.class,
			DataSerializers.BOOLEAN);

	public EntityFlowerPot(World worldIn) {
		super(worldIn);
	}

	public EntityFlowerPot(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityFlowerPot(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FLOWER, Optional.<ItemStack>absent());
		this.dataManager.register(COLOR, false);
	}

	@Override
	protected ItemStack drops() {
		int meta = getColor() ? 1 : 0;
		return new ItemStack(MainInit.flowerPot, 1, meta);
	}

	public void setFlower(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			this.dataManager.set(FLOWER, Optional.of(item));
		}
	}

	public ItemStack getFlower() {
		ItemStack item = (ItemStack) ((Optional) this.dataManager.get(FLOWER)).orNull();
		return item;
	}

	public void setBrown(boolean f) {
		this.dataManager.set(COLOR, f);
	}

	public boolean getColor() {
		return this.dataManager.get(COLOR);
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
		if (player != null && player.isSneaking()) {
			ItemStack hold = player.getHeldItem(hand);
			ItemStack flower = (ItemStack) ((Optional) this.dataManager.get(FLOWER)).orNull();
			if (isFlower(hold)) {
				setFlower(hold);
				this.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1.0F, 1.0F);
				return true;
			}
		}
		return super.processInitialInteract(player, stack, hand);
	}

	public boolean isFlower(ItemStack item) {
		if (!DCUtil.isEmpty(item)) {
			if (item.getItem() instanceof ItemBlock) {
				Block b = ((ItemBlock) item.getItem()).getBlock();
				int i = item.getItemDamage();
				if (b instanceof IPlantable || b.getStateFromMeta(i).getMaterial() == Material.PLANTS)
					return true;
			}
		}
		return false;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		ItemStack itemstack = (ItemStack) ((Optional) this.dataManager.get(FLOWER)).orNull();

		if (itemstack != null) {
			compound.setTag("FlowerItem", itemstack.writeToNBT(new NBTTagCompound()));
		}

		compound.setBoolean("isBrown", this.dataManager.get(COLOR));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		NBTTagCompound nbttagcompound = compound.getCompoundTag("FlowerItem");

		if (nbttagcompound != null) {
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

			if (itemstack != null) {
				this.dataManager.set(FLOWER, Optional.of(itemstack));
			}
		}

		if (compound.hasKey("isBrown")) {
			this.dataManager.set(COLOR, compound.getBoolean("isBrown"));
		}
	}

}
