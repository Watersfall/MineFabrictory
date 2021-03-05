package net.watersfall.minefabrictory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.watersfall.minefabrictory.block.MachineBlock;
import net.watersfall.minefabrictory.block.MineFabrictoryBlocks;
import net.watersfall.minefabrictory.block.entity.MineFabrictoryBlockEntities;
import net.watersfall.minefabrictory.item.MineFabrictoryItems;

public class MineFabrictory implements ModInitializer
{
	public static Identifier getId(String name)
	{
		return new Identifier("minefabrictory", name);
	}

	private static void registerEvents()
	{
		UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
			ItemStack stack = player.getStackInHand(hand);
			if(stack.getItem() == Items.STICK)
			{
				BlockState state = world.getBlockState(hitResult.getBlockPos());
				if(state.getBlock() instanceof MachineBlock)
				{
					if(hitResult.getSide().getAxis() != Direction.Axis.Y)
					{
						if(!world.isClient)
						{
							MachineBlock block = (MachineBlock)state.getBlock();
							if(player.isSneaking())
							{
								block.setFacing(world, hitResult.getBlockPos(), state, hitResult.getSide().getOpposite());
							}
							else
							{
								block.setFacing(world, hitResult.getBlockPos(), state, hitResult.getSide());
							}
						}
						return ActionResult.success(world.isClient);
					}
				}
			}
			return ActionResult.PASS;
		}));
	}

	@Override
	public void onInitialize()
	{
		MineFabrictoryBlocks.register();
		MineFabrictoryItems.register();
		MineFabrictoryBlockEntities.register();
		registerEvents();
	}
}
