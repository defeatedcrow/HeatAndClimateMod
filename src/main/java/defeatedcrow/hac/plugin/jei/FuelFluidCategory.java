package defeatedcrow.hac.plugin.jei;

import java.util.List;

import org.joml.Matrix4f;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import defeatedcrow.hac.api.recipe.IDeviceFuel;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.machine.material.MachineInit;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public class FuelFluidCategory implements IRecipeCategory<IDeviceFuel> {
	protected IDrawable background;
	protected IDrawable icon;

	public FuelFluidCategory(IGuiHelper guiHelper) {
		icon = guiHelper.createDrawableItemStack(new ItemStack(MachineInit.FUEL_BURNER.get()));
		background = guiHelper.drawableBuilder(PluginTexDC.FUEL.getLocation(), 32, 20, 100, 25).addPadding(0, 0, 0, 0).build();
	}

	@Override
	public RecipeType<IDeviceFuel> getRecipeType() {
		return JEIPluginDC.FLUID_FUEL_DATA;
	}

	@Override
	public Component getTitle() {
		return Component.translatable("dcs.gui.jei.fluid_fuel_data");
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, IDeviceFuel recipe, IFocusGroup focuses) {
		if (recipe.getInputFluid() != null) {
			TagKey<Fluid> tag = recipe.getInputFluid();
			List<Fluid> list = TagUtil.getFluidList(tag);
			if (!list.isEmpty()) {
				Fluid f = list.get(0);
				builder.addSlot(RecipeIngredientRole.INPUT, 6, 5).addFluidStack(f, 1000);
			}
		}
	}

	@Override
	public void getTooltip(ITooltipBuilder tooltip, IDeviceFuel recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {}

	@Override
	public void draw(IDeviceFuel recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;

		MutableComponent time = Component.literal(recipe.getBurnTime() + "Tick");
		graphics.drawString(font, time, 50, 12, 0xFF000000, false);
	}

	private static void drawTexturedModalRect(Matrix4f mat, int x, int y, int tX, int tY, int wid, int hei) {
		float f = 1F / 256F;
		float f1 = 1F / 256F;
		BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(mat, x + 0, y + hei, 90.0F).uv((tX + 0) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + hei, 90.0F).uv((tX + wid) * f, (tY + hei) * f1).endVertex();
		bufferbuilder.vertex(mat, x + wid, y + 0, 90.0F).uv((tX + wid) * f, (tY + 0) * f1).endVertex();
		bufferbuilder.vertex(mat, x + 0, y + 0, 90.0F).uv((tX + 0) * f, (tY + 0) * f1).endVertex();
		Tesselator.getInstance().end();
	}

}
