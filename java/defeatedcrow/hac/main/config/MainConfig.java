package defeatedcrow.hac.main.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class MainConfig {

	private MainConfig() {
	}

	public static final MainConfig INSTANCE = new MainConfig();

	public void load(File file) {
		File cfgFile = new File(file, "defeatedcrow/climate/worldgen.cfg");
		WorldGenConfig.INSTANCE.load(new Configuration(cfgFile));
	}

}