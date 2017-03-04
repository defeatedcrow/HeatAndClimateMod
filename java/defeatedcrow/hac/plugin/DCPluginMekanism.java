package defeatedcrow.hac.plugin;

import defeatedcrow.hac.food.FoodInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInterModComms;

public class DCPluginMekanism {

	public static final DCPluginMekanism INSTANCE = new DCPluginMekanism();

	private DCPluginMekanism() {}

	public static void load() {
		Item bio = Item.REGISTRY.getObject(new ResourceLocation("Mekanism", "BioFuel"));
		if (bio != null) {
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 0), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 1), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 2), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 3), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 4), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 5), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 6), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 7), new ItemStack(bio, 4, 0));
			// RecipeHelper.addCrusherRecipe(new ItemStack(FoodInit.crops, 1, 8), new ItemStack(bio, 2, 0));
		}
	}

	public static void sendIMC() {
		Item bio = Item.REGISTRY.getObject(new ResourceLocation("Mekanism", "BioFuel"));
		if (bio != null) {
			NBTTagCompound crop0 = new NBTTagCompound();
			crop0.setTag("input", new ItemStack(FoodInit.crops, 1, 0).writeToNBT(new NBTTagCompound()));
			crop0.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop0);

			NBTTagCompound crop1 = new NBTTagCompound();
			crop1.setTag("input", new ItemStack(FoodInit.crops, 1, 1).writeToNBT(new NBTTagCompound()));
			crop1.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop1);

			NBTTagCompound crop2 = new NBTTagCompound();
			crop2.setTag("input", new ItemStack(FoodInit.crops, 1, 2).writeToNBT(new NBTTagCompound()));
			crop2.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop2);

			NBTTagCompound crop3 = new NBTTagCompound();
			crop3.setTag("input", new ItemStack(FoodInit.crops, 1, 3).writeToNBT(new NBTTagCompound()));
			crop3.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop3);

			NBTTagCompound crop4 = new NBTTagCompound();
			crop4.setTag("input", new ItemStack(FoodInit.crops, 1, 4).writeToNBT(new NBTTagCompound()));
			crop4.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop4);

			NBTTagCompound crop5 = new NBTTagCompound();
			crop5.setTag("input", new ItemStack(FoodInit.crops, 1, 5).writeToNBT(new NBTTagCompound()));
			crop5.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop5);

			NBTTagCompound crop6 = new NBTTagCompound();
			crop6.setTag("input", new ItemStack(FoodInit.crops, 1, 6).writeToNBT(new NBTTagCompound()));
			crop6.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop6);

			NBTTagCompound crop7 = new NBTTagCompound();
			crop7.setTag("input", new ItemStack(FoodInit.crops, 1, 7).writeToNBT(new NBTTagCompound()));
			crop7.setTag("output", new ItemStack(bio, 4, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop7);

			NBTTagCompound crop8 = new NBTTagCompound();
			crop8.setTag("input", new ItemStack(FoodInit.crops, 1, 8).writeToNBT(new NBTTagCompound()));
			crop8.setTag("output", new ItemStack(bio, 2, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop8);

			NBTTagCompound crop9 = new NBTTagCompound();
			crop9.setTag("input", new ItemStack(FoodInit.crops, 1, 9).writeToNBT(new NBTTagCompound()));
			crop9.setTag("output", new ItemStack(bio, 2, 0).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage("Mekanism", "CrusherRecipe", crop9);
		}
	}

}
