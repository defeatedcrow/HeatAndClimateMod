package defeatedcrow.hac.core.json;

import java.util.Map;

/**
 * JSON出力用クラス
 */
public class JsonModelDC {

	final String parent;
	final Map<String, String> textures;

	public JsonModelDC(String p, Map<String, String> tex) {
		parent = p;
		textures = tex;
	}

}
