package net.watersfall.minefabrictory.block;

import net.minecraft.util.registry.Registry;
import net.watersfall.minefabrictory.MineFabrictory;

public class MineFabrictoryBlocks
{
	public static final SheepShearBlock SHEEP_SHEAR_BLOCK;

	static
	{
		SHEEP_SHEAR_BLOCK = new SheepShearBlock();
	}

	public static void register()
	{
		Registry.register(Registry.BLOCK, MineFabrictory.getId("sheep_shear"), SHEEP_SHEAR_BLOCK);
	}
}
