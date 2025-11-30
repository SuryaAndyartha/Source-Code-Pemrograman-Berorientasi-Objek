import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Field
{
    private int depth;
    private int width;
    private Object[][] field;

    public Field(int depth, int width)
    {
        this.depth = depth;
        this.width = width;
        field = new Object[depth][width];
    }

    public void clear()
    {
        for(int r=0; r<depth; r++) {
            for(int c=0; c<width; c++) {
                field[r][c] = null;
            }
        }
    }

    public void clear(Location location)
    {
        field[location.getRow()][location.getCol()] = null;
    }

    public void place(Object obj, Location location)
    {
        field[location.getRow()][location.getCol()] = obj;
    }

    public Object getObjectAt(Location location)
    {
        return getObjectAt(location.getRow(), location.getCol());
    }

    public Object getObjectAt(int row, int col)
    {
        return field[row][col];
    }

    public int getDepth()
    {
        return depth;
    }

    public int getWidth()
    {
        return width;
    }

    public List<Location> adjacentLocations(Location location)
    {
        List<Location> locations = new ArrayList<Location>();
        int row = location.getRow();
        int col = location.getCol();
        for(int r = row-1; r <= row+1; r++) {
            for(int c = col-1; c <= col+1; c++) {
                if(r >= 0 && r < depth && c >= 0 && c < width &&
                   (r != row || c != col)) {
                    locations.add(new Location(r, c));
                }
            }
        }
        Collections.shuffle(locations, Randomizer.getRandom());
        return locations;
    }

    public List<Location> getFreeAdjacentLocations(Location location)
    {
        List<Location> free = new ArrayList<Location>();
        for(Location loc : adjacentLocations(location)) {
            if(getObjectAt(loc) == null) {
                free.add(loc);
            }
        }
        return free;
    }

    public Location freeAdjacentLocation(Location location)
    {
        List<Location> free = getFreeAdjacentLocations(location);
        if(free.size() > 0) {
            return free.get(0);
        }
        return null;
    }
}
