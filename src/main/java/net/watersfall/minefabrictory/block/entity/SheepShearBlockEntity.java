package net.watersfall.minefabrictory.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.watersfall.minefabrictory.inventory.BasicInventory;
import net.watersfall.minefabrictory.mixin.SheepEntityAccessor;

import java.util.List;

public class SheepShearBlockEntity extends AreaWorkingMachineEntity implements Tickable, BasicInventory
{
	private final DefaultedList<ItemStack> contents;

	public SheepShearBlockEntity()
	{
		super(MineFabrictoryBlockEntities.SHEEP_SHEAR_ENTITY);
		contents = DefaultedList.ofSize(12, ItemStack.EMPTY);
	}

	@Override
	public void tick()
	{
		if(this.world != null && !this.world.isClient)
		{
			if(this.world.getTime() % 20 == 0)
			{
				List<SheepEntity> entities = this.world.getEntitiesByType(EntityType.SHEEP, this.getWorkingArea(), (entity) -> true);
				for(SheepEntity entity : entities)
				{
					if(entity.isShearable())
					{
						int amount = 1 + entity.getRandom().nextInt(3);
						Item item = SheepEntityAccessor.getDrops().get(entity.getColor()).asItem();
						ItemStack stack = new ItemStack(item, amount);
						for(int i = 0; i < this.size() && !stack.isEmpty(); i++)
						{
							if(this.isValid(i, stack))
							{
								stack = this.addStack(i, stack);
							}
						}
						if(stack.getCount() != amount)
						{
							entity.setSheared(true);
							this.markDirty();
							break;
						}
					}
				}
			}
		}
	}

	@Override
	public void fromTag(BlockState state, CompoundTag tag)
	{
		super.fromTag(state, tag);
		Inventories.fromTag(tag, this.getContents());
	}

	@Override
	public CompoundTag toTag(CompoundTag tag)
	{
		super.toTag(tag);
		return Inventories.toTag(tag, this.getContents());
	}

	@Override
	public DefaultedList<ItemStack> getContents()
	{
		return this.contents;
	}
}
