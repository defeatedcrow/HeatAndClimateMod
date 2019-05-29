package defeatedcrow.hac.main.api;

import java.util.List;

public interface IDCInfoDataRegister {

	List<IDCInfoData> getList();

	void registerInfo(IDCInfoData data);

}
