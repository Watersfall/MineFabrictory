package net.watersfall.minefabrictory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.watersfall.minefabrictory.block.MineFabrictoryBlocks;
import net.watersfall.minefabrictory.block.entity.MineFabrictoryBlockEntities;
import net.watersfall.minefabrictory.item.MineFabrictoryItems;

public class MineFabrictory implements ModInitializer
{
	public static Identifier getId(String name)
	{
		return new Identifier("minefabrictory", name);
	}

	@Override
	public void onInitialize()
	{
		MineFabrictoryBlocks.register();
		MineFabrictoryItems.register();
		MineFabrictoryBlockEntities.register();
	}
}
