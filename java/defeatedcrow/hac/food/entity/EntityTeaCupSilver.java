package defeatedcrow.hac.food.entity;

import javax.annotation.Nullable;

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
import defeatedcrow.hac.core.base.DCEntityBase;
import defeatedcrow.hac.food.FoodInit;

public class EntityTeaCupSilver extends DCEntityBase {

	protected static final DataParameter<String> FLUID = EntityDataManager.<String> createKey(EntityTeaCupSilver.class,
			DataSerializers.STRING);
	protected static final DataParameter<Integer> AMOUNT = EntityDataManager.<Integer> createKey(
			EntityTeaCupSilver.class, DataSerializers.VARINT);

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

	@Override
	protected ItemStack drops() {
		ItemStack drop = new ItemStack(FoodInit.cupSilver, 1);
		IFluidHandler cont = drop.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
		Fluid f = FluidRegistry.getFluid(getFluidName());
		int amo = getAmount();
		if (cont != null && f != null && amo > 0) {
			cont.fill(new FluidStack(f, amo), true);
		}
		return drop;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FLUID, "empty");
		this.dataManager.register(AMOUNT, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		super.readEntityFromNBT(tag);
		String s = tag.getString("dcs.entityfluid");
		int a = tag.getInteger("dcs.entityamo");
		this.dataManager.set(FLUID, s);
		this.dataManager.set(AMOUNT, a);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		super.writeEntityToNBT(tag);
		String s = getFluidName();
		int a = getAmount();
		tag.setInteger("dcs.entityamo", a);
		tag.setString("dcs.entityfluid", s);
	}

	public String getFluidName() {
		return this.dataManager.get(FLUID);
	}

	public int getAmount() {
		return this.dataManager.get(AMOUNT);
	}
}
