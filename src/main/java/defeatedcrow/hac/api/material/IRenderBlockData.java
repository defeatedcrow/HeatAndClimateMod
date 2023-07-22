package defeatedcrow.hac.api.material;

import net.minecraft.world.level.block.Block;

/**
 * TESRとBlockを紐付けるインターフェイス。
 */
public interface IRenderBlockData {

	EntityRenderData getRenderData(Block block);

}
