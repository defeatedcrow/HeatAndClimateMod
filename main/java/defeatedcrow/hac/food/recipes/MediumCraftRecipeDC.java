package defeatedcrow.hac.food.recipes;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.food.item.brewing.ItemMedium;
import defeatedcrow.hac.food.item.brewing.ItemUnidentified;
import defeatedcrow.hac.food.item.brewing.MicrobeItem;
import defeatedcrow.hac.main.api.brewing.IMediumItem;
import defeatedcrow.hac.main.api.brewing.IMicrobe;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class MediumCraftRecipeDC extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements
		IRecipe {

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		ItemStack medium = ItemStack.EMPTY;
		ItemStack microbe = ItemStack.EMPTY;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack check = inv.getStackInSlot(i);

			if (!check.isEmpty()) {
				if (check.getItem() == FoodInit.unidentified) {
					microbe = check;
				} else if (check.getItem() instanceof MicrobeItem) {
					microbe = check;
				} else if (check.getItem() instanceof IMediumItem) {
					medium = check;
				}
			}
		}

		if (!DCUtil.isEmpty(medium) && !DCUtil.isEmpty(microbe)) {
			IMediumItem med = (IMediumItem) medium.getItem();
			if (microbe.getItem() == FoodInit.unidentified) {
				IMicrobe type = ItemUnidentified.getSpecies(microbe);
				if (type != null && type.getMediums().contains(med.getMedium(medium))) {
					return true;
				}
			} else if (microbe.getItem() instanceof MicrobeItem) {
				IMicrobe type = ((IMicrobe) microbe.getItem());
				if (type != null && type.getMediums().contains(med.getMedium(medium))) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack medium = ItemStack.EMPTY;
		ItemStack microbe = ItemStack.EMPTY;
		ItemStack output = ItemStack.EMPTY;

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack check = inv.getStackInSlot(i);

			if (!check.isEmpty()) {
				if (check.getItem() == FoodInit.unidentified) {
					microbe = check;
				} else if (check.getItem() instanceof MicrobeItem) {
					microbe = check;
				} else if (check.getItem() instanceof IMediumItem) {
					medium = check;
				}
			}
		}

		if (!DCUtil.isEmpty(medium) && !DCUtil.isEmpty(microbe)) {
			if (!DCUtil.isEmpty(medium) && !DCUtil.isEmpty(microbe)) {
				IMediumItem med = (IMediumItem) medium.getItem();
				if (microbe.getItem() == FoodInit.unidentified) {
					IMicrobe type = ItemUnidentified.getSpecies(microbe);
					output = medium.copy();
					ItemMedium.setMicrobeItem(output, type, null);
					return output;
				} else if (microbe.getItem() instanceof MicrobeItem) {
					MicrobeItem type = ((MicrobeItem) microbe.getItem());
					EnumRarity rarity = type.getRarity(microbe);
					output = medium.copy();
					ItemMedium.setMicrobeItem(output, type, rarity);
					return output;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
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
