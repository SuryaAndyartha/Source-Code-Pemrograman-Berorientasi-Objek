import java.util.List;

public abstract class Animal
{
    protected boolean alive = true;
    protected Field field;
    protected Location location;

    public Animal(Field field, Location location)
    {
        this.field = field;
        setLocation(location);
    }

    public boolean isAlive()
    {
        return alive;
    }

    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Setiap hewan akan bertindak per satu langkah simulasi.
     */
    public abstract void act(List<Animal> newAnimals);
}
