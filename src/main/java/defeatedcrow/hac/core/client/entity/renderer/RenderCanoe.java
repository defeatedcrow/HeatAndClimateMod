package defeatedcrow.hac.core.client.entity.renderer;

import com.mojang.datafixers.util.Pair;

import defeatedcrow.hac.core.client.entity.model.CanoeModel;
import defeatedcrow.hac.core.material.item.tool.CanoeItem;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCanoe extends BoatRenderer {
	private final CanoeModel model;

	public RenderCanoe(EntityRendererProvider.Context cont) {
		super(cont, false);
		this.model = new CanoeModel(cont.bakeLayer(CanoeItem.KUKUI.getLayerLocation()));
	}

	@Override
	public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
		return Pair.of(CanoeItem.KUKUI.getTextureLocation(), model);
	}
}
