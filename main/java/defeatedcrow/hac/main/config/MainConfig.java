package defeatedcrow.hac.main.config;

import java.io.File;

import defeatedcrow.hac.main.worldgen.vein.VeinTableJson;
import net.minecraftforge.common.config.Configuration;

public class MainConfig {

	private MainConfig() {}

	public static final MainConfig INSTANCE = new MainConfig();

	public void load(File file) {
		File cfgFile = new File(file, "worldgen.cfg");
		WorldGenConfig.INSTANCE.load(new Configuration(cfgFile));

		File cfgFile2 = new File(file, "module.cfg");
		ModuleConfig.INSTANCE.load(new Configuration(cfgFile2));

		File cfgFile3 = new File(file, "main.cfg");
		MainCoreConfig.INSTANCE.load(new Configuration(cfgFile3));

		File cfgFile4 = new File(file, "oredic.cfg");
		OredicConfig.INSTANCE.load(new Configuration(cfgFile4));

		VeinTableJson.INSTANCE.setDir(file);
	}

}
