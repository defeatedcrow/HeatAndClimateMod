package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.capability.DrinkCapabilityHandler;
import defeatedcrow.hac.food.capability.DrinkMilk;
import defeatedcrow.hac.food.capability.DrinkSugar;
import defeatedcrow.hac.food.capability.IDrinkCustomize;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class DrinkCustomRecipeDC extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements
		IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		ItemStack drink = ItemStack.EMPTY;
		ItemStack milk = ItemStack.EMPTY;
		ItemStack sugar = ItemStack.EMPTY;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack check = inv.getStackInSlot(i);

			if (!check.isEmpty()) {
				if (DrinkMilk.isMilkItem(check) != DrinkMilk.NONE) {
					milk = check;
				} else if (DrinkSugar.isSugarItem(check) != DrinkSugar.NONE) {
					sugar = check;
				} else if (check.hasCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null)) {
					drink = check;
				}
			}
		}

		if (!DCUtil.isEmpty(drink) && (!DCUtil.isEmpty(milk) || !DCUtil.isEmpty(sugar))) {
			IDrinkCustomize custom = drink.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
			if (!DCUtil.isEmpty(milk)) {
				DrinkMilk tm = DrinkMilk.isMilkItem(milk);
				if (custom.getMilk() != DrinkMilk.NONE && custom.getMilk() != tm) {
					return false;
				}
			}
			if (!DCUtil.isEmpty(sugar)) {
				DrinkSugar ts = DrinkSugar.isSugarItem(sugar);
				if (custom.getSugar() != DrinkSugar.NONE && custom.getSugar() != ts) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack drink = ItemStack.EMPTY;
		ItemStack milk = ItemStack.EMPTY;
		ItemStack sugar = ItemStack.EMPTY;
		IDrinkCustomize custom = null;

		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack check = inv.getStackInSlot(k);

			if (!check.isEmpty()) {
				if (DrinkMilk.isMilkItem(check) != DrinkMilk.NONE) {
					milk = check;
				} else if (DrinkSugar.isSugarItem(check) != DrinkSugar.NONE) {
					sugar = check;
				} else if (check.hasCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null)) {
					drink = check.copy();
					drink.setCount(1);
					custom = drink.getCapability(DrinkCapabilityHandler.DRINK_CUSTOMIZE_CAPABILITY, null);
				}
			}
		}

		if (custom == null || DCUtil.isEmpty(drink)) {
			return ItemStack.EMPTY;
		} else {
			if (!DCUtil.isEmpty(milk)) {
				DrinkMilk tm = DrinkMilk.isMilkItem(milk);
				custom.setMilk(tm);
			}
			if (!DCUtil.isEmpty(sugar)) {
				DrinkSugar ts = DrinkSugar.isSugarItem(sugar);
				custom.setSugar(ts);
			}
			return drink;
		}
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);

		for (int i = 0; i < nonnulllist.size(); ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			if (DrinkMilk.isMilkItem(itemstack) != DrinkMilk.NONE || DrinkSugar
					.isSugarItem(itemstack) != DrinkSugar.NONE) {
				nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
			}
		}

		return nonnulllist;
	}

	@Override
	public boolean isDynamic() {
		return true;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= 4;
	}
}
