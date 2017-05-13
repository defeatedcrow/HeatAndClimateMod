package defeatedcrow.hac.machine.item;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.IPressMold;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
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
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack != null && stack.hasTagCompound()) {
			ItemStack output = this.getOutput(stack);
			if (output != null) {
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
		return stack != null && stack.hasTagCompound();
	}

	@Override
	public ItemStack setOutput(ItemStack mold, ItemStack output, int num) {
		if (output != null && mold != null && mold.getItem() instanceof IPressMold) {
			ItemStack next = new ItemStack(mold.getItem(), mold.stackSize, mold.getItemDamage());
			IPressMold mol = (IPressMold) next.getItem();
			if (mol.getOutput(mold) == null) {
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
		return null;
	}

	@Override
	public ItemStack getOutput(ItemStack mold) {
		if (mold != null && mold.getItem() instanceof IPressMold) {
			NBTTagCompound tag = new NBTTagCompound();
			if (mold.hasTagCompound()) {
				tag = mold.getTagCompound();
			}
			NBTTagList nbttaglist = tag.getTagList("MoldOutput", 10);
			if (nbttaglist.tagCount() > 0) {
				NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(0);
				ItemStack ret = ItemStack.loadItemStackFromNBT(tag1);
				if (ret != null)
					return ret;
			}
		}
		return null;
	}

	@Override
	public int getRecipeNumber(ItemStack mold) {
		if (mold != null && mold.getItem() instanceof IPressMold) {
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
		if (mold != null && mold.getItem() instanceof IPressMold) {
			NBTTagCompound tag = new NBTTagCompound();
			if (mold.hasTagCompound()) {
				tag = mold.getTagCompound();
			}
			NBTTagList nbttaglist = tag.getTagList("MoldRecipe", 10);
			for (int i = 0; i < nbttaglist.tagCount(); i++) {
				NBTTagCompound tag1 = nbttaglist.getCompoundTagAt(i);
				ItemStack ret = ItemStack.loadItemStackFromNBT(tag1);
				if (ret != null) {
					list.add(ret);
				}
			}
		}
		return list;
	}

	private RecipePair getInputList(ItemStack output, int num) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		List<ItemStack> empty = new ArrayList<ItemStack>();
		List<IRecipe> targetRecipes = CraftingManager.getInstance().getRecipeList();

		ShapedRecipes s = null;
		ShapedOreRecipe sOre = null;
		ShapelessRecipes sl = null;
		ShapelessOreRecipe slOre = null;

		List<IRecipe> targets = new ArrayList<IRecipe>();

		for (IRecipe rec : targetRecipes) {
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
		if (rec.getRecipeOutput() != null && DCUtil.isSameItem(output, rec.getRecipeOutput(), false)) {
			if (s == null && rec instanceof ShapedRecipes) {
				s = (ShapedRecipes) rec;
			}
			if (sOre == null && rec instanceof ShapedOreRecipe) {
				sOre = (ShapedOreRecipe) rec;
			}
			if (sl == null && rec instanceof ShapelessRecipes) {
				sl = (ShapelessRecipes) rec;
			}
			if (slOre == null && rec instanceof ShapelessOreRecipe) {
				slOre = (ShapelessOreRecipe) rec;
			}
		}

		if (sOre != null) {
			for (Object obj : sOre.getInput()) {
				if (obj == null) {
					continue;
				}

				ItemStack item = null;
				boolean b = false;
				if (obj instanceof ItemStack) {
					item = new ItemStack(((ItemStack) obj).getItem(), 1, ((ItemStack) obj).getItemDamage());
				} else if (obj instanceof Item) {
					item = new ItemStack((Item) obj);
				} else if (obj instanceof Block) {
					item = new ItemStack((Block) obj, 1, 0);
				} else if (obj instanceof List) {
					if ((List<ItemStack>) obj != null && !((List<ItemStack>) obj).isEmpty()) {
						List<ItemStack> ores = (List<ItemStack>) obj;
						for (ItemStack oreItem : ores) {
							if (oreItem != null) {
								item = new ItemStack(oreItem.getItem(), 1, oreItem.getItemDamage());
								break;
							}
						}
					}
				}

				if (item != null) {
					if (item.getItem().isDamageable() && item.getItemDamage() > 0) {
						item = new ItemStack(item.getItem(), 1, 1);
					}
					if (list.isEmpty()) {
						list.add(item);
						// DCLogger.debugLog("set reqs " + 0 + ": " + item.getDisplayName() + ", " +
						// item.stackSize);
						continue;
					} else {
						boolean c = false;
						for (int i = 0; i < list.size(); i++) {
							if (DCUtil.isSameItem(item, list.get(i), false)) {
								list.get(i).stackSize++;
								// DCLogger.debugLog(
								// "add reqs " + i + ": " + item.getDisplayName() + ", " +
								// list.get(i).stackSize);
								c = true;
								break;
							}
						}
						if (!c) {
							list.add(item);
							// DCLogger.debugLog("set reqs " + (list.size() - 1) + ": " +
							// item.getDisplayName() + ", "
							// + item.stackSize);
						}
					}
				}
			}
			if (!list.isEmpty())
				return new RecipePair(sOre.getRecipeOutput().stackSize, list, n);
		} else if (slOre != null) {
			for (Object obj : slOre.getInput()) {
				if (obj == null) {
					continue;
				}
				ItemStack item = null;
				boolean b = false;
				if (obj instanceof ItemStack) {
					item = new ItemStack(((ItemStack) obj).getItem(), 1, ((ItemStack) obj).getItemDamage());
				} else if (obj instanceof Item) {
					item = new ItemStack((Item) obj);
				} else if (obj instanceof Block) {
					item = new ItemStack((Block) obj, 1, 0);
				} else if (obj instanceof List) {
					if ((List<ItemStack>) obj != null && !((List<ItemStack>) obj).isEmpty()) {
						List<ItemStack> ores = (List<ItemStack>) obj;
						for (ItemStack oreItem : ores) {
							if (oreItem != null) {
								item = new ItemStack(oreItem.getItem(), 1, oreItem.getItemDamage());
								break;
							}
						}
					}
				}

				if (item != null) {
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
								list.get(i).stackSize++;
								c = true;
								break;
							}
						}
						if (!c) {
							list.add(item);
						}
					}
				} else
					return null;
			}
			if (!list.isEmpty())
				return new RecipePair(slOre.getRecipeOutput().stackSize, list, n);
		} else if (s != null) {
			for (ItemStack obj : s.recipeItems) {
				if (obj == null) {
					continue;
				}
				ItemStack item = new ItemStack(obj.getItem(), 1, obj.getItemDamage());
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
							list.get(i).stackSize++;
							c = true;
							break;
						}
					}
					if (!c) {
						list.add(item);
					}
				}
			}
			if (!list.isEmpty())
				return new RecipePair(s.getRecipeOutput().stackSize, list, n);
		} else if (sl != null) {
			for (ItemStack obj : sl.recipeItems) {
				if (obj == null) {
					continue;
				}
				ItemStack item = new ItemStack(obj.getItem(), 1, obj.getItemDamage());
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
							list.get(i).stackSize++;
							c = true;
							break;
						}
					}
					if (!c) {
						list.add(item);
					}
				}
			}
			if (!list.isEmpty())
				return new RecipePair(sl.getRecipeOutput().stackSize, list, n);
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
