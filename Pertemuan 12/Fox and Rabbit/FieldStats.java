import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class FieldStats
{
    private Map<Class, Counter> counters;
    private boolean countsValid;

    public FieldStats()
    {
        counters = new HashMap<Class, Counter>();
        countsValid = false;
    }

    public String getPopulationDetails(Field field)
    {
        StringBuffer buffer = new StringBuffer();
        if(!countsValid) {
            generateCounts(field);
        }
        Iterator<Class> it = counters.keySet().iterator();
        while(it.hasNext()) {
            Class key = it.next();
            Counter info = counters.get(key);
            buffer.append(info.toString());
            buffer.append(' ');
        }
        return buffer.toString();
    }

    public void reset()
    {
        countsValid = false;
        for(Counter counter : counters.values()) {
            counter.reset();
        }
    }

    public void incrementCount(Class animalClass)
    {
        Counter counter = counters.get(animalClass);
        if(counter == null) {
            counter = new Counter(animalClass.getSimpleName());
            counters.put(animalClass, counter);
        }
        counter.increment();
    }

    public int getCount(Class animalClass)
    {
        Counter counter = counters.get(animalClass);
        return counter == null ? 0 : counter.getCount();
    }

    public void countFinished()
    {
        countsValid = true;
    }

    public boolean isViable(Field field)
    {
        if(!countsValid) {
            generateCounts(field);
        }
        int nonZero = 0;
        for(Counter counter : counters.values()) {
            if(counter.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }

    private void generateCounts(Field field)
    {
        reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object obj = field.getObjectAt(row, col);
                if(obj != null) {
                    incrementCount(obj.getClass());
                }
            }
        }
        countsValid = true;
    }
}
