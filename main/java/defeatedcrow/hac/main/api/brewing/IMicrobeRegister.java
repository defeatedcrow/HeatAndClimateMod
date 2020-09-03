package defeatedcrow.hac.main.api.brewing;

import java.util.List;

public interface IMicrobeRegister {

	List<IMicrobe> getList();

	void registerSpecies(IMicrobe species);

	IMicrobe getRecipe(String name);

}
