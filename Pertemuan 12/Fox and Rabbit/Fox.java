import java.util.List;
import java.util.Random;

public class Fox extends Animal
{
    private static final int BREEDING_AGE = 10;
    private static final int MAX_AGE = 50;
    private static final double BREEDING_PROBABILITY = 0.6;
    private static final int MAX_LITTER_SIZE = 2;
    private static final int RABBIT_FOOD_VALUE = 7;

    private int age = 0;
    private int foodLevel = RABBIT_FOOD_VALUE;
    private static final Random rand = Randomizer.getRandom();

    public Fox(Field field, Location location)
    {
        super(field, location);
    }

    @Override
    public void act(List<Animal> newFoxes)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newFoxes);
            Location newLoc = findFood();
            if(newLoc == null) {
                newLoc = field.freeAdjacentLocation(location);
            }
            if(newLoc != null) {
                setLocation(newLoc);
            } else {
                setDead();
            }
        }
    }

    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }

    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    private Location findFood()
    {
        for(Location loc : field.adjacentLocations(location)) {
            Object obj = field.getObjectAt(loc);
            if(obj instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) obj;
                if(rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return loc;
                }
            }
        }
        return null;
    }

    private void giveBirth(List<Animal> newAnimals)
    {
        if(age >= BREEDING_AGE && rand.nextDouble() <= BREEDING_PROBABILITY) {
            List<Location> free = field.getFreeAdjacentLocations(location);
            int births = rand.nextInt(MAX_LITTER_SIZE) + 1;
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                newAnimals.add(new Fox(field, loc));
            }
        }
    }
}
