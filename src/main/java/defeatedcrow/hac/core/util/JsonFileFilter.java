package defeatedcrow.hac.core.util;

import java.io.File;
import java.io.FileFilter;

public class JsonFileFilter implements FileFilter {

	@Override
	public boolean accept(File name) {
		if (name != null && !name.isDirectory()) {
			return name.getName().endsWith(".json");
		}
		return false;
	}

}
