package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class EntityTeaCupSilver extends DCEntityBase {

	protected static final DataParameter<String> FLUID = EntityDataManager.<String> createKey(EntityTeaCupSilver.class,
			DataSerializers.STRING);
	protected static final DataParameter<Integer> AMOUNT = EntityDataManager
			.<Integer> createKey(EntityTeaCupSilver.class, DataSerializers.VARINT);

	protected static final DataParameter<Byte> MILK = EntityDataManager.<Byte> createKey(EntityTeaCupSilver.class,
			DataSerializers.BYTE);
	protected static final DataParameter<Byte> SUGAR = EntityDataManager.<Byte> createKey(EntityTeaCupSilver.class,
			DataSerializers.BYTE);

	public EntityTeaCupSilver(World worldIn) {
		super(worldIn);
	}

	public EntityTeaCupSilver(World worldIn, double posX, double posY, double posZ) {
		super(worldIn, posX, posY, posZ);
	}

	public EntityTeaCupSilver(World worldIn, double posX, double posY, double posZ, @Nullable EntityPlayer player) {
		super(worldIn, posX, posY, posZ, player);
	}

	public EntityTeaCupSilver setFluid(FluidStack fluid) {
		if (fluid == null || fluid.getFluid() == null) {
			this.dataManager.set(FLUID, "empty");
			this.dataManager.set(AMOUNT, 0);
		} else {
			this.dataManager.set(FLUID, FluidRegistry.getFluidName(fluid));
			this.dataManager.set(AMOUNT, fluid.amount);
		}
		return this;
	}

	public EntityTeaCupSilver setCustom(DrinkMilk milk, DrinkSugar sugar) {
		this.dataManager.set(MILK, (byte) milk.id);
		this.dataManager.set(SUGAR, (byte) sugar.id);
		return this;
	}

	@Override
	protected ItemStack drops() {
		ItemStack drop = new ItemStack(FoodInit.cupSilver, 1, 0);
		IFluidHandler cont = drop.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		IDrinkCustomize drink = drop.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
		Fluid f = FluidRegistry.getFluid(getFluidName());
		int amo = getAmount();
		if (cont != null && f != null && amo > 0) {
			cont.fill(new FluidStack(f, amo), true);
		}
		if (drink != null) {
			drink.setMilk(DrinkMilk.getFromId(getMilk()));
			drink.setSugar(DrinkSugar.getFromId(getSugar()));
		}
		return drop;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FLUID, "empty");
		this.dataManager.register(AMOUNT, 0);
		this.dataManager.register(MILK, (byte) 0);
		this.dataManager.register(SUGAR, (byte) 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		String s = tag.getString("dcs.entityfluid");
		int a = tag.getInteger("dcs.entityamo");
		byte m = tag.getByte("dcs.entitymilk");
		byte su = tag.getByte("dcs.entitysugar");
		this.dataManager.set(FLUID, s);
		this.dataManager.set(AMOUNT, a);
		this.dataManager.set(MILK, m);
		this.dataManager.set(SUGAR, su);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		String s = getFluidName();
		int a = getAmount();
		byte m = this.getMilk();
		byte su = this.getSugar();
		tag.setInteger("dcs.entityamo", a);
		tag.setString("dcs.entityfluid", s);
		tag.setByte("dcs.entitymilk", m);
		tag.setByte("dcs.entitysugar", su);
	}

	public String getFluidName() {
		return this.dataManager.get(FLUID);
	}

	public int getAmount() {
		return this.dataManager.get(AMOUNT);
	}

	public byte getMilk() {
		return this.dataManager.get(MILK);
	}

	public byte getSugar() {
		return this.dataManager.get(SUGAR);
	}
}
