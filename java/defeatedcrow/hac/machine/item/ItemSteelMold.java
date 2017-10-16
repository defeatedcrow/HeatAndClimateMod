package defeatedcrow.hac.machine.item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemSteelMold extends DCItem implements IPressMold {

	private final int maxMeta;

	private static String[] names = {
			"steel"
	};

	public ItemSteelMold() {
		super();
		maxMeta = 0;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		if (!DCUtil.isEmpty(stack) && stack.hasTagCompound()) {
			ItemStack output = this.getOutput(stack);
			if (!DCUtil.isEmpty(output)) {
				tooltip.add(TextFormatting.BOLD.toString() + "Output: " + output.getDisplayName());
				if (ClimateCore.isDebug) {
					tooltip.add(TextFormatting.BOLD.toString() + "Recipe ID: " + this.getRecipeNumber(stack));
				}
			} else {
				tooltip.add("Please register an item on the anvil.");
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return !DCUtil.isEmpty(stack) && stack.hasTagCompound();
	}

	@Override
	public ItemStack setOutput(ItemStack mold, ItemStack output, int num) {
		if (!DCUtil.isEmpty(output) && mold != null && mold.getItem() instanceof IPressMold) {
			ItemStack next = new ItemStack(mold.getItem(), mold.getCount(), mold.getItemDamage());
			IPressMold mol = (IPressMold) next.getItem();
			ItemStack current = mol.getOutput(mold);
			if (DCUtil.isEmpty(mol.getOutput(mold)) || DCUtil.isStackable(output, current)) {
				// レシピ検索
				RecipePair recipe = this.getInputList(output, num);
				if (recipe != null) {
					NBTTagCompound tag = new NBTTagCompound();

					NBTTagList nbttaglist1 = new NBTTagList();
					NBTTagCompound tag1 = new NBTTagCompound();
					ItemStack ret = new ItemStack(output.getItem(), recipe.amount, output.getItemDamage());
					ret.writeToNBT(tag1);
					nbttaglist1.appendTag(tag1);
					tag.setTag("MoldOutput", nbttaglist1);

					NBTTagList nbttaglist2 = new NBTTagList();
					for (int j = 0; j < recipe.requiers.size(); j++) {
						NBTTagCompound tag2 = new NBTTagCompound();
						tag2.setByte("Input", (byte) j);
						recipe.requiers.get(j).writeToNBT(tag2);
						nbttaglist2.appendTag(tag2);
					}
					tag.setTag("MoldRecipe", nbttaglist2);
					tag.setInteger("MoldRecipeNum", recipe.number);
					next.setTagCompound(tag);
					return next;
				}
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getOutput(ItemStack mold) {
		if (!DCUtil.isEmpty(mold) && mold.getItem() instanceof IPressMold) {
			NBTTagCompound tag = new NBTTagCompound();
			if (mold.hasTagCompound()) {
				tag = mold.getTagCompound();
			}
			NBTTagList nbttaglist = tag.getTagList("MoldOutput", 10);
			if (nbttaglist.tagCount() > 0) {
				NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(0);
				ItemStack ret = new ItemStack(tag1);
				if (!DCUtil.isEmpty(ret))
					return ret;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public int getRecipeNumber(ItemStack mold) {
		if (!DCUtil.isEmpty(mold) && mold.getItem() instanceof IPressMold) {
			NBTTagCompound tag = new NBTTagCompound();
			if (mold.hasTagCompound()) {
				tag = mold.getTagCompound();
			}
			int num = tag.getInteger("MoldRecipeNum");
			return num;
		}
		return 0;
	}

	@Override
	public List<ItemStack> getInputs(ItemStack mold) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		if (!DCUtil.isEmpty(mold) && mold.getItem() instanceof IPressMold) {
			NBTTagCompound tag = new NBTTagCompound();
			if (mold.hasTagCompound()) {
				tag = mold.getTagCompound();
			}
			NBTTagList nbttaglist = tag.getTagList("MoldRecipe", 10);
			for (int i = 0; i < nbttaglist.tagCount(); i++) {
				NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
				ItemStack ret = new ItemStack(tag1);
				if (!DCUtil.isEmpty(ret)) {
					list.add(ret);
				}
			}
		}
		return list;
	}

	private RecipePair getInputList(ItemStack output, int num) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		List<ItemStack> empty = new ArrayList<ItemStack>();
		Iterator<IRecipe> targetRecipes = CraftingManager.REGISTRY.iterator();

		ShapedRecipes s = null;
		ShapedOreRecipe sOre = null;
		ShapelessRecipes sl = null;
		ShapelessOreRecipe slOre = null;

		List<IRecipe> targets = new ArrayList<IRecipe>();

		while (targetRecipes.hasNext()) {
			IRecipe rec = targetRecipes.next();
			if (rec.getRecipeOutput() != null && DCUtil.isSameItem(output, rec.getRecipeOutput(), false)) {
				targets.add(rec);
			}
		}

		if (targets.isEmpty())
			return null;

		int n = num;
		if (num >= targets.size()) {
			n = 0;
		}

		IRecipe rec = targets.get(n);
		NonNullList<Ingredient> ings = NonNullList.create();
		if (!rec.getRecipeOutput().isEmpty() && DCUtil.isSameItem(output, rec.getRecipeOutput(), false)) {
			ings.addAll(rec.getIngredients());
		}

		if (!ings.isEmpty()) {
			for (Ingredient ing : ings) {
				if (ing == null || ing == ing.EMPTY) {
					continue;
				}

				ItemStack item = ItemStack.EMPTY;
				boolean b = false;
				for (ItemStack oreItem : ing.getMatchingStacks()) {
					if (!DCUtil.isEmpty(oreItem)) {
						item = new ItemStack(oreItem.getItem(), 1, oreItem.getItemDamage());
						break;
					}
				}

				if (!DCUtil.isEmpty(item)) {
					// damageable tools
					if (item.getItem().isDamageable() && item.getItemDamage() > 0) {
						item = new ItemStack(item.getItem(), 1, 1);
					}

					if (list.isEmpty()) {
						list.add(item);
						continue;
					} else {
						boolean c = false;
						for (int i = 0; i < list.size(); i++) {
							if (DCUtil.isSameItem(item, list.get(i), false)) {
								list.get(i).grow(1);
								c = true;
								break;
							}
						}

						if (!c) {
							list.add(item);
						}
					}
				}
			}
			if (!list.isEmpty())
				return new RecipePair(rec.getRecipeOutput().getCount(), list, n);
		}

		return null;
	}

	protected class RecipePair {

		protected final int amount;
		protected final int number;
		protected final List<ItemStack> requiers = new ArrayList<ItemStack>();

		protected RecipePair(int amo, List<ItemStack> list, int num) {
			amount = amo;
			number = num;
			if (list != null && !list.isEmpty()) {
				requiers.addAll(list);
			}
		}
	}

}
