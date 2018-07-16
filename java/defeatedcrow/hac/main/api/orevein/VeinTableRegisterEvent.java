package defeatedcrow.hac.main.api.orevein;

import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * VainTableの登録後に発火するイベント<br>
 * 内容編集用
 */
public class VeinTableRegisterEvent extends Event {

	private final IVeinTableRegister register;

	public VeinTableRegisterEvent(IVeinTableRegister prev) {
		this.register = prev;
	}

	public IVeinTableRegister getRegister() {
		return register;
	}
}
