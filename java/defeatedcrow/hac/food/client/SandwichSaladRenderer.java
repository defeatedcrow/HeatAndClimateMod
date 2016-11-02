package defeatedcrow.hac.food.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SandwichSaladRenderer extends SandwichAppleRenderer {

	public SandwichSaladRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	protected int meta() {
		return 2;
	}

}
