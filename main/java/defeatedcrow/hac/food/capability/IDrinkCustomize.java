package defeatedcrow.hac.food.capability;

/**
 * 飲み物Entity、ItemStack共通のインターフェイス。<br>
 * いずれもNBTによって保持している
 */
public interface IDrinkCustomize {

	DrinkMilk getMilk();

	DrinkSugar getSugar();

	int getAgingLevel();

	boolean setMilk(DrinkMilk milk);

	boolean setSugar(DrinkSugar sugar);

	boolean setAging(int level);

}
