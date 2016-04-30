import java.util.List;
import java.util.Random;

/**
 * A simple model of a strider.
 * Striders age and move.
 * 
 * @author Lars Birkmose
 * @version 2016.04.30
 */
public class Strider extends Actor
{
    //Static fields
    // A shared random number generator to control breeding.
    
    private static final Random rand = Randomizer.getRandom();
    
    // The age to which a Strider can live.
    private static final int MAX_AGE = 80;
    // Individual characteristics (instance fields).
    
    /**
     * Create a new STRIDER. A STRIDER may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Strider(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(MAX_AGE));
        }
        else {
            setAge(0);
        }
        setAlive();
    }
    
    /**
     * This is what the strider does most of the time - it moves 
     * around.
     * @param newRabbits A list to add newly born rabbits to.
    */ 
    public void act(List<Actor> newStriders)
    {
        //incrementAge(MAX_AGE);
        if(isAlive()) {
            //giveBirth(newRabbits);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.do nothing
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    */
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to add newly born rabbits to.
     
    private void giveBirth(List<Actor> newRabbits)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, field, loc);
            newRabbits.add(young);
        }
    }
    */
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }
    */
    /**
     * Return the breeding age for a Rabbit.
     * @return the breeding age for a Rabbit.
     
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    */
}
