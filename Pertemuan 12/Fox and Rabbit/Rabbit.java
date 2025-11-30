import java.util.List;
import java.util.Random;

public class Rabbit extends Animal
{
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private static final double BREEDING_PROBABILITY = 0.16;
    private static final int MAX_LITTER_SIZE = 5;

    private int age = 0;
    private static final Random rand = Randomizer.getRandom();

    public Rabbit(Field field, Location location)
    {
        super(field, location);
    }

    @Override
    public void act(List<Animal> newRabbits)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newRabbits);
            Location newLoc = field.freeAdjacentLocation(location);
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

    private void giveBirth(List<Animal> newAnimals)
    {
        if(age >= BREEDING_AGE && rand.nextDouble() <= BREEDING_PROBABILITY) {
            List<Location> free = field.getFreeAdjacentLocations(location);
            int births = rand.nextInt(MAX_LITTER_SIZE) + 1;
            for(int b = 0; b < births && free.size() > 0; b++) {
                Location loc = free.remove(0);
                newAnimals.add(new Rabbit(field, loc));
            }
        }
    }
}
