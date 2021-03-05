package net.watersfall.minefabrictory.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.watersfall.minefabrictory.block.entity.SheepShearBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SheepShearBlock extends Block implements InventoryProvider, BlockEntityProvider
{
	public SheepShearBlock()
	{
		super(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockView world)
	{
		return new SheepShearBlockEntity();
	}

	@Override
	public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos)
	{
		BlockEntity test = world.getBlockEntity(pos);
		if(test instanceof SheepShearBlockEntity)
		{
			return (SidedInventory)test;
		}
		return null;
	}
}
