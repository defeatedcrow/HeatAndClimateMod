package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.item.DrinkItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class DrinkEntity extends FoodEntityBase {

	protected int meta;

	protected static final DataParameter<Integer> META = EntityDataManager
			.<Integer>createKey(DrinkEntity.class, DataSerializers.VARINT);

	public DrinkEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.2F, 0.375F);
	}

	public DrinkEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.2F, 0.375F);
	}

	public DrinkEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.2F, 0.375F);
	}

	@Override
	protected ItemStack[] drops() {
		ItemStack ret = new ItemStack(FoodInit.drink, 1, meta);
		return new ItemStack[] {
				ret,
				ret.copy()
		};
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(META, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		setMeta(tag.getInteger("dcs.entitymeta"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		int i = this.getMeta();
		tag.setInteger("dcs.entitymeta", i);
	}

	public void setMeta(int i) {
		meta = i & 255;
		this.dataManager.set(META, meta);
	}

	public int getMeta() {
		return this.dataManager.get(META);
	}

	public DrinkEntity setMetadata(int meta) {
		setMeta(meta);
		return this;
	}

	@Override
	public String getName() {
		String s = EntityList.getEntityString(this);
		int i = this.getMeta();
		String type = DrinkItem.getTypeName(i);
		return I18n.format("entity." + s + "_" + type + ".name");
	}
}
