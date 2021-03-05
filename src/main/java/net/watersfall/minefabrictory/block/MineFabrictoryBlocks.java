package net.watersfall.minefabrictory.block;

import net.minecraft.util.registry.Registry;
import net.watersfall.minefabrictory.MineFabrictory;

public class MineFabrictoryBlocks
{
	public static final SheepShearBlock SHEEP_SHEAR_BLOCK;
	public static final AnimalBreederBlock ANIMAL_BREEDER_BLOCK;
	public static final AnimalKillerBlock ANIMAL_KILLER_BLOCK;

	static
	{
		SHEEP_SHEAR_BLOCK = new SheepShearBlock();
		ANIMAL_BREEDER_BLOCK = new AnimalBreederBlock();
		ANIMAL_KILLER_BLOCK = new AnimalKillerBlock();
	}

	public static void register()
	{
		Registry.register(Registry.BLOCK, MineFabrictory.getId("sheep_shear"), SHEEP_SHEAR_BLOCK);
		Registry.register(Registry.BLOCK, MineFabrictory.getId("animal_breeder"), ANIMAL_BREEDER_BLOCK);
		Registry.register(Registry.BLOCK, MineFabrictory.getId("animal_killer"), ANIMAL_KILLER_BLOCK);
	}
}
