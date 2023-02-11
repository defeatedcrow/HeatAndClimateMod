package defeatedcrow.hac.core.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigClientBuilder {

	public static final ForgeConfigSpec CONFIG_CLIENT;
	public static final ConfigClientBuilder INSTANCE;

	public final ForgeConfigSpec.BooleanValue showAltTip;
	public final ForgeConfigSpec.BooleanValue showDamEffect;
	public final ForgeConfigSpec.BooleanValue showHUD;

	public final ForgeConfigSpec.IntValue HUD_c;

	public final ForgeConfigSpec.IntValue offset_x;
	public final ForgeConfigSpec.IntValue offset_y;

	public final ForgeConfigSpec.IntValue key_HUD;
	public final ForgeConfigSpec.IntValue key_Charm;

	public static int HUD_type = 0;

	ConfigClientBuilder(ForgeConfigSpec.Builder builder) {

		builder.comment("========= Render Setting ========", "Gives some additional tooltips to the item.").push("render_config");

		this.showAltTip = builder
			.define("Show Additional Tooltips", true);

		this.showDamEffect = builder
			.comment("= Set the screen effect when climate damage occurs.")
			.define("Show Damage Effect", true);

		this.showHUD = builder
			.comment(" Show climate info on HUD.")
			.define("Show Climate HUD", true);

		builder.pop();

		builder.comment("======== HUD Setting ========", "Select a color number for the climate HUD.").push("hud_config");

		this.HUD_c = builder
			.defineInRange("HUD Color Select", 0, 0, 4);

		this.offset_x = builder
			.comment(" Adjust the display position of the HUD.")
			.defineInRange("HUD X Offset", 3, -100, 400);

		this.offset_y = builder
			.comment(" Adjust the display position of the HUD.")
			.defineInRange("HUD Y Offset", 160, -100, 240);

		builder.pop();

		builder.comment("======== Keybind Setting ========", "Sets the HUD display switching key.").push("key_config");

		this.key_HUD = builder
			.comment(" Set the key to switch HUD design. default:L-shift")
			.defineInRange("HUD Switch Key", 340, 0, 350);

		this.key_Charm = builder
			.comment(" Sets the using charm effect key. default:X")
			.defineInRange("Charm Using Key", 88, 0, 350);

		builder.pop();
	}

	static {
		Pair<ConfigClientBuilder, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder()
			.configure(ConfigClientBuilder::new);
		CONFIG_CLIENT = pair.getRight();
		INSTANCE = pair.getLeft();
	}
}
