package defeatedcrow.hac.food.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * 飲み物用の新規Capability。<br>
 * FluidTankのようにこれ自身はNBTを保持せず、EntityとItemStack側に持っている。
 */
public class DrinkCapabilityHandler {

	private DrinkCapabilityHandler() {
	}

	public static final DrinkCapabilityHandler INSTANCE = new DrinkCapabilityHandler();

	@CapabilityInject(IDrinkCustomize.class)
	public static Capability<IDrinkCustomize> DRINK_CUSTOMIZE_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IDrinkCustomize.class, INSTANCE.new StorageDC(), INSTANCE.new CallableDC());
	}

	public class StorageDC implements Capability.IStorage<IDrinkCustomize> {

		@Override
		public NBTBase writeNBT(Capability<IDrinkCustomize> capability, IDrinkCustomize instance, EnumFacing side) {
			NBTTagCompound nbt = new NBTTagCompound();
			DrinkMilk milk = instance.getMilk();
			DrinkSugar sugar = instance.getSugar();

			nbt.setByte(DrinkMilk.getTagKey(), (byte) milk.id);
			nbt.setByte(DrinkSugar.getTagKey(), (byte) sugar.id);
			return nbt;
		}

		@Override
		public void readNBT(Capability<IDrinkCustomize> capability, IDrinkCustomize instance, EnumFacing side,
				NBTBase nbt) {
			NBTTagCompound tag = (NBTTagCompound) nbt;
			byte m = tag.getByte(DrinkMilk.getTagKey());
			byte s = tag.getByte(DrinkSugar.getTagKey());
			DrinkMilk milk = DrinkMilk.getFromId(m);
			DrinkSugar sugar = DrinkSugar.getFromId(s);
			instance.setMilk(milk);
			instance.setSugar(sugar);
		}

	}

	public class CallableDC implements Callable<IDrinkCustomize> {

		@Override
		public IDrinkCustomize call() throws Exception {
			return new DrinkCustomizer();
		}

	}

	/* Customize判定 */

}
