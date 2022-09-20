package defeatedcrow.hac.core.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;

/**
 * JSON出力用クラス
 */
public class JsonBlockStateDC {

	public static class Build {

		final Map<String, Variant> variants = new HashMap<>();

		public Build(Block block, IJsonDataDC data) {
			List<String> vals = getAllVariants(block);
			List<String> names = getFilenamePath(block);

			if (vals.isEmpty()) {
				variants.put("", new Variant("dcs_climate:block/" + data.getDomain() + "/" + data.getPath()));
			} else {
				for (int i = 0; i < vals.size(); i++) {
					int k = i;
					if (k > names.size())
						k = names.size() - 1;
					variants.put(vals.get(i), new Variant(
							"dcs_climate:block/" + data.getDomain() + "/" + data.getPath() + names.get(k)));
				}
			}
		}

	}

	public static class Variant {

		public final String model;

		public Variant(String filename) {
			model = filename;
		}

	}

	public static List<String> getAllVariants(Block block) {
		List<String> list = Lists.newArrayList();
		Collection<Property<?>> props = block.defaultBlockState().getProperties();
		props.stream().forEach(p -> {
			List<String> list2 = Lists.newArrayList();
			p.getPossibleValues().stream().forEach(val -> {
				if (list.isEmpty()) {
					list2.add(p.getName() + "=" + getName(p, val));
				} else {
					for (String str : list) {
						list2.add(str + "," + p.getName() + "=" + getName(p, val));
					}
				}
			});
			list.clear();
			list.addAll(list2);
		});

		return list;
	}

	public static List<String> getFilenamePath(Block block) {
		List<String> list = Lists.newArrayList();
		Collection<Property<?>> props = block.defaultBlockState().getProperties();
		props.stream().forEach(p -> {
			List<String> list2 = Lists.newArrayList();
			p.getPossibleValues().stream().forEach(val -> {
				if (list.isEmpty()) {
					list2.add("_" + getName(p, val).toLowerCase());
				} else {
					for (String str : list) {
						list2.add(str + "_" + getName(p, val).toLowerCase());
					}
				}
			});
			list.clear();
			list.addAll(list2);
		});

		return list;
	}

	private static <T extends Comparable<T>> String getName(Property<T> prop, Comparable<?> val) {
		return prop.getName((T) val);
	}

}
