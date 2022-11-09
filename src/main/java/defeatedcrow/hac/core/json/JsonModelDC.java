package defeatedcrow.hac.core.json;

import java.util.Map;

/**
 * JSON出力用クラス
 */
public class JsonModelDC extends JsonModelSimpleDC {

	final Map<String, String> textures;

	public JsonModelDC(String p, Map<String, String> tex) {
		super(p);
		textures = tex;
	}

}
