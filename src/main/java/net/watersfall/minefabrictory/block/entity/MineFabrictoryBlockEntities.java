package net.watersfall.minefabrictory.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.watersfall.minefabrictory.MineFabrictory;
import net.watersfall.minefabrictory.block.MineFabrictoryBlocks;

public class MineFabrictoryBlockEntities
{
	public static final BlockEntityType<SheepShearBlockEntity> SHEEP_SHEAR_ENTITY;

	static
	{
		SHEEP_SHEAR_ENTITY = BlockEntityType.Builder.create(SheepShearBlockEntity::new, MineFabrictoryBlocks.SHEEP_SHEAR_BLOCK).build(null);
	}

	public static void register()
	{
		Registry.register(Registry.BLOCK_ENTITY_TYPE, MineFabrictory.getId("sheep_shear_entity"), SHEEP_SHEAR_ENTITY);
	}
}
