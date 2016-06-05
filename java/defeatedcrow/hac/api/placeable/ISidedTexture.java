package defeatedcrow.hac.api.placeable;

/**
 * ModelBakedEventで使用するBlockのTextureパス指定。
 * Jsonに使用するものではない。
 */
public interface ISidedTexture {

	/**
	 * ModelBakedEvent用
	 */
	String getTexture(int meta, int side, boolean face);

}
