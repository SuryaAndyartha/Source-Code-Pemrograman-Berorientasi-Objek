public class Location
{
    private int row;
    private int col;

    public Location(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode()
    {
        return 31 * row + col;
    }

    @Override
    public String toString()
    {
        return row + "," + col;
    }
}
