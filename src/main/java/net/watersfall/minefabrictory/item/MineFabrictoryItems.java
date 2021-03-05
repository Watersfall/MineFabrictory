package net.watersfall.minefabrictory.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.watersfall.minefabrictory.MineFabrictory;
import net.watersfall.minefabrictory.block.MineFabrictoryBlocks;

public class MineFabrictoryItems
{
	public static final BlockItem SHEEP_SHEAR_ITEM;

	static
	{
		SHEEP_SHEAR_ITEM = new BlockItem(MineFabrictoryBlocks.SHEEP_SHEAR_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
	}

	public static void register()
	{
		Registry.register(Registry.ITEM, MineFabrictory.getId("sheep_shear"), SHEEP_SHEAR_ITEM);
	}
}
