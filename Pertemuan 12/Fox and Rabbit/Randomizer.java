import java.util.Random;

public class Randomizer
{
    private static final Random rand = new Random();
    private static final Random sharedRandom = rand;
    private static boolean useShared = true;

    public static Random getRandom()
    {
        if(useShared) {
            return sharedRandom;
        } else {
            return new Random();
        }
    }

    public static void reset()
    {
        sharedRandom.setSeed(1111L);
    }

    public static void setUseShared(boolean shared)
    {
        useShared = shared;
    }
}
