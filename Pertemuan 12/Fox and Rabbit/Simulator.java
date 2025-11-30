import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

public class Simulator
{
    private static final int DEFAULT_DEPTH = 50;
    private static final int DEFAULT_WIDTH = 50;

    private static final double RABBIT_CREATION_PROBABILITY = 0.12;
    private static final double FOX_CREATION_PROBABILITY = 0.05;

    private List<Animal> animals;
    private Field field;
    private int step;
    private SimulatorView view;

    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    public Simulator(int depth, int width)
    {
        if(depth <= 0 || width <= 0) {
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<Animal>();
        field = new Field(depth, width);
        view = new SimulatorView(depth, width);
        view.setColor(Rabbit.class, Color.ORANGE);
        view.setColor(Fox.class, Color.BLUE);

        reset();
    }

    public void reset()
    {
        step = 0;
        animals.clear();
        field.clear();
        populate();
        view.showStatus(step, field);
    }

    public void simulate(int numSteps)
    {
        for(int i = 0; i < numSteps && view.isViable(field); i++) {
            simulateOneStep();
        }
    }

    public void simulateOneStep()
    {
        step++;
        List<Animal> newAnimals = new ArrayList<Animal>();

        for(Iterator<Animal> it = animals.iterator(); it.hasNext(); ) {
            Animal animal = it.next();
            animal.act(newAnimals);
            if(!animal.isAlive()) {
                it.remove();
            }
        }

        animals.addAll(newAnimals);
        view.showStatus(step, field);
    }

    public void runLongSimulation()
    {
        simulate(4000);
    }

    private void populate()
    {
        animals.clear();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                double rand = Randomizer.getRandom().nextDouble();
                Location loc = new Location(row, col);
                if(rand <= FOX_CREATION_PROBABILITY) {
                    Animal fox = new Fox(field, loc);
                    animals.add(fox);
                }
                else if(rand <= FOX_CREATION_PROBABILITY + RABBIT_CREATION_PROBABILITY) {
                    Animal rabbit = new Rabbit(field, loc);
                    animals.add(rabbit);
                }
                // else kosong
            }
        }
    }
}
