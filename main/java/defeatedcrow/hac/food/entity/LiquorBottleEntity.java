package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.FoodEntityBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import defeatedcrow.hac.food.item.brewing.ItemLiquorBottle;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class LiquorBottleEntity extends FoodEntityBase {

	protected int meta;

	protected static final DataParameter<Integer> META = EntityDataManager
			.<Integer>createKey(LiquorBottleEntity.class, DataSerializers.VARINT);

	protected static final DataParameter<Byte> MILK = EntityDataManager
			.<Byte>createKey(LiquorBottleEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> SUGAR = EntityDataManager
			.<Byte>createKey(LiquorBottleEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> AGING = EntityDataManager
			.<Byte>createKey(LiquorBottleEntity.class, DataSerializers.BYTE);

	public LiquorBottleEntity(World worldIn) {
		super(worldIn);
		this.setSize(0.375F, 0.75F);
	}

	public LiquorBottleEntity(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
		this.setSize(0.375F, 0.75F);
	}

	public LiquorBottleEntity(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
		this.setSize(0.375F, 0.75F);
	}

	@Override
	protected ItemStack[] drops() {
		ItemStack ret = new ItemStack(FoodInit.liquorBottle, 1, meta);
		IDrinkCustomize drink = ret.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		if (drink != null) {
			drink.setMilk(DrinkMilk.getFromId(getMilk()));
			drink.setSugar(DrinkSugar.getFromId(getSugar()));
			drink.setAging(getAging());
		}
		return new ItemStack[] { ret, ret.copy() };
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(META, 0);
		this.dataManager.register(MILK, (byte) 0);
		this.dataManager.register(SUGAR, (byte) 0);
		this.dataManager.register(AGING, (byte) 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		setMeta(tag.getInteger("dcs.entitymeta"));
		byte m = tag.getByte("dcs.entitymilk");
		byte su = tag.getByte("dcs.entitysugar");
		byte ag = tag.getByte("dcs.entityaging");
		this.dataManager.set(MILK, m);
		this.dataManager.set(SUGAR, su);
		this.dataManager.set(AGING, ag);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		int i = this.getMeta();
		byte m = this.getMilk();
		byte su = this.getSugar();
		byte ag = this.getAging();
		tag.setInteger("dcs.entitymeta", i);
		tag.setByte("dcs.entitymilk", m);
		tag.setByte("dcs.entitysugar", su);
		tag.setByte("dcs.entityaging", ag);
	}

	public void setMeta(int i) {
		meta = i & 255;
		this.dataManager.set(META, meta);
	}

	public int getMeta() {
		return this.dataManager.get(META);
	}

	public byte getMilk() {
		return this.dataManager.get(MILK);
	}

	public byte getSugar() {
		return this.dataManager.get(SUGAR);
	}

	public byte getAging() {
		return this.dataManager.get(AGING);
	}

	public LiquorBottleEntity setMetadata(int meta) {
		setMeta(meta);
		return this;
	}

	public LiquorBottleEntity setCustom(DrinkMilk milk, DrinkSugar sugar, int aging) {
		this.dataManager.set(MILK, (byte) milk.id);
		this.dataManager.set(SUGAR, (byte) sugar.id);
		this.dataManager.set(AGING, (byte) aging);
		return this;
	}

	@Override
	public String getName() {
		if (this.hasCustomName()) {
			return this.getCustomNameTag();
		} else {
			String s = EntityList.getEntityString(this);
			int i = this.getMeta();
			String type = ItemLiquorBottle.getTypeName(i);
			return I18n.format("entity." + s + "_" + type + ".name");
		}
	}
}
