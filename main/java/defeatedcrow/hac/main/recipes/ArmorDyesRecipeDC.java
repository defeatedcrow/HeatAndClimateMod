package defeatedcrow.hac.main.recipes;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.item.equip.ItemArmorDC;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.DyeUtils;

public class ArmorDyesRecipeDC extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements
		IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		ItemStack itemstack = ItemStack.EMPTY;
		ItemStack dye = ItemStack.EMPTY;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack check = inv.getStackInSlot(i);

			if (!check.isEmpty()) {
				if (check.getItem() instanceof ItemArmorDC) {
					ItemArmorDC armor = (ItemArmorDC) check.getItem();

					if (DCUtil.isEmpty(check)) {
						return false;
					}
					itemstack = check;

				} else {
					if (!DyeUtils.isDye(check) && !DCUtil.matchDicName("soap", check)) {
						return false;
					}
					dye = check;
				}
			}
		}

		if (!DCUtil.isEmpty(itemstack) && !DCUtil.isEmpty(dye)) {
			return true;
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack stack = ItemStack.EMPTY;
		ItemStack dye = ItemStack.EMPTY;
		ItemArmorDC armor = null;

		for (int k = 0; k < inv.getSizeInventory(); ++k) {
			ItemStack check = inv.getStackInSlot(k);

			if (!check.isEmpty()) {
				if (check.getItem() instanceof ItemArmorDC) {
					if (DCUtil.isEmpty(check)) {
						return ItemStack.EMPTY;
					}
					armor = (ItemArmorDC) check.getItem();
					stack = check.copy();
					stack.setCount(1);

				} else {
					if (!DyeUtils.isDye(check) && !DCUtil.matchDicName("soap", check)) {
						return ItemStack.EMPTY;
					}
					dye = check.copy();
				}
			}
		}

		if (armor == null) {
			return ItemStack.EMPTY;
		} else {
			EnumDyeColor color = armor.getItemColor(dye);
			if (color == null || color == EnumDyeColor.WHITE) {
				armor.removeColor(stack);
			} else {
				armor.setColor(stack, color.getMetadata());
			}
			return stack;
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
			nonnulllist.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack));
		}

		return nonnulllist;
	}

	@Override
	public boolean isDynamic() {
		return true;
	}

	@Override
	public boolean canFit(int width, int height) {
		return width * height >= 2;
	}
}
