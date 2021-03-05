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
	public static final BlockItem ANIMAL_BREEDER_ITEM;
	public static final BlockItem ANIMAL_KILLER_ITEM;

	static
	{
		SHEEP_SHEAR_ITEM = new BlockItem(MineFabrictoryBlocks.SHEEP_SHEAR_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
		ANIMAL_BREEDER_ITEM = new BlockItem(MineFabrictoryBlocks.ANIMAL_BREEDER_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
		ANIMAL_KILLER_ITEM = new BlockItem(MineFabrictoryBlocks.ANIMAL_KILLER_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
	}

	public static void register()
	{
		Registry.register(Registry.ITEM, MineFabrictory.getId("sheep_shear"), SHEEP_SHEAR_ITEM);
		Registry.register(Registry.ITEM, MineFabrictory.getId("animal_breeder"), ANIMAL_BREEDER_ITEM);
		Registry.register(Registry.ITEM, MineFabrictory.getId("animal_killer"), ANIMAL_KILLER_ITEM);
	}
}
