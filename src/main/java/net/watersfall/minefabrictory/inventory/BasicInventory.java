package net.watersfall.minefabrictory.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.stream.IntStream;

public interface BasicInventory extends SidedInventory
{
	DefaultedList<ItemStack> getContents();

	@Override
	default int size()
	{
		return getContents().size();
	}

	@Override
	default boolean isEmpty()
	{
		for(int i = 0; i < getContents().size(); i++)
		{
			if(!getContents().get(i).isEmpty())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	default ItemStack getStack(int slot)
	{
		return getContents().get(slot).copy();
	}

	@Override
	default ItemStack removeStack(int slot, int amount)
	{
		ItemStack stack = getStack(slot);
		if(stack.getCount() <= amount)
		{
			return removeStack(slot);
		}
		else
		{
			stack.decrement(amount);
			ItemStack returnStack = new ItemStack(stack.getItem(), amount);
			this.setStack(slot, stack);
			return returnStack;
		}
	}

	@Override
	default ItemStack removeStack(int slot)
	{
		ItemStack stack = getStack(slot);
		this.setStack(slot, ItemStack.EMPTY);
		return stack;
	}

	@Override
	default void setStack(int slot, ItemStack stack)
	{
		getContents().set(slot, stack);
	}

	@Override
	default void markDirty() {}

	@Override
	default boolean canPlayerUse(PlayerEntity player)
	{
		return true;
	}

	@Override
	default boolean isValid(int slot, ItemStack stack)
	{
		ItemStack current = getStack(slot);
		if(current.isEmpty())
		{
			return true;
		}
		else if(current.getItem() == stack.getItem())
		{
			return current.getCount() < 64;
		}
		return false;
	}

	default ItemStack addStack(int slot, ItemStack stack)
	{
		ItemStack current = getStack(slot);
		if(current.isEmpty())
		{
			this.setStack(slot, stack.copy());
			return ItemStack.EMPTY;
		}
		else if(this.getStack(slot).getItem() == stack.getItem())
		{
			if(current.getCount() + stack.getCount() > 64)
			{
				stack.setCount(stack.getCount() - (64 - current.getCount()));
				current.setCount(64);
			}
			else
			{
				current.setCount(current.getCount() + stack.getCount());
				stack.setCount(0);
			}
			this.setStack(slot, current);
		}
		return stack;
	}

	@Override
	default int[] getAvailableSlots(Direction side)
	{
		return IntStream.range(0, this.size()).toArray();
	}

	@Override
	default boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir)
	{
		return true;
	}

	@Override
	default boolean canExtract(int slot, ItemStack stack, Direction dir)
	{
		return true;
	}

	@Override
	default void clear()
	{
		getContents().clear();
	}
}
