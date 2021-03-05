package net.watersfall.minefabrictory.block.entity;

import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.watersfall.minefabrictory.inventory.BasicInventory;

import java.util.List;

public class AnimalBreederBlockEntity extends AreaWorkingMachineEntity implements Tickable, BasicInventory
{
	private final DefaultedList<ItemStack> contents;

	public AnimalBreederBlockEntity()
	{
		super(MineFabrictoryBlockEntities.ANIMAL_BREEDER_ENTITY);
		this.contents = DefaultedList.ofSize(12, ItemStack.EMPTY);
	}

	@Override
	public void tick()
	{
		if(this.world != null && !this.world.isClient && this.world.getTime() % 100 == 0)
		{
			List<AnimalEntity> entities = this.world.getEntitiesByClass(AnimalEntity.class, this.getWorkingArea(), (animal) -> {
				return animal.getBreedingAge() == 0 && animal.canEat() && animal.getLoveTicks() <= 0;
			});
			for(int i = 0; i < entities.size(); i++)
			{
				for(int o = 0; o < entities.size(); o++)
				{
					if(entities.get(i) != entities.get(o))
					{
						if(entities.get(i).getType() == entities.get(o).getType())
						{
							ItemStack stack1 = getBreedingItem(entities.get(i));
							if(!stack1.isEmpty())
							{
								stack1.decrement(1);
								ItemStack stack2 = getBreedingItem(entities.get(o));
								if(!stack2.isEmpty())
								{
									stack2.decrement(1);
									entities.get(i).lovePlayer(null);
									entities.get(o).lovePlayer(null);
									return;
								}
								else
								{
									stack1.increment(1);
								}
							}
						}
					}
				}
			}
		}
	}

	public ItemStack getBreedingItem(AnimalEntity entity)
	{
		for(int i = 0; i < this.size(); i++)
		{
			if(entity.isBreedingItem(this.getStack(i)))
			{
				return getStack(i);
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getStack(int slot)
	{
		return getContents().get(slot);
	}

	@Override
	public DefaultedList<ItemStack> getContents()
	{
		return this.contents;
	}
}
