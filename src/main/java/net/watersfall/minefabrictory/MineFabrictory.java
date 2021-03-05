package net.watersfall.minefabrictory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.watersfall.minefabrictory.block.MachineBlock;
import net.watersfall.minefabrictory.block.MineFabrictoryBlocks;
import net.watersfall.minefabrictory.block.entity.AreaWorkingMachineEntity;
import net.watersfall.minefabrictory.block.entity.MineFabrictoryBlockEntities;
import net.watersfall.minefabrictory.client.render.AreaWorkingMachineEntityRenderer;
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
							BlockEntity test = world.getBlockEntity(hitResult.getBlockPos());
							if(test instanceof AreaWorkingMachineEntity)
							{
								AreaWorkingMachineEntity entity = (AreaWorkingMachineEntity)test;
								entity.calculateBox(world.getBlockState(hitResult.getBlockPos()));
								entity.sync();
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
