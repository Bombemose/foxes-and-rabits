import java.util.List;
import java.util.Iterator;
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
    private static final int MAX_AGE = 750;
    
    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 250;
    
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.5;
    
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 7;
    
    private static final int INITIAL_FOOD_VALUE = 300;
    
    //
    
    // Individual characteristics (instance fields).
    private int foodLevel;
    
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
            foodLevel = rand.nextInt(INITIAL_FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = INITIAL_FOOD_VALUE;
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
        incrementAge(MAX_AGE);
        incrementHunger();
        if(isAlive()) {
            giveBirth(newStriders);
            // Move towards a Rabbit to shoot if found.
            Location location = getLocation();
            Location newLocation = locateAndShootRabbit(location);
            if(newLocation == null) { 
                // No Rabbit found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding
                System.out.println("Strider død af over-crowding");
                setDead();
            }
        }
    }
    
    /**
    * Tell the Strider to look for rabbits adjacent to its current location.
    * Only the first live rabbit is shot.
    * @param location Where in the field it is located.
    * @return Where Rabbit was found, or null if it wasn't.
    */
    private Location locateAndShootRabbit(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    //System.out.println("Skød en kanin på position " + where.toString());
                    // Remove the dead rabbit from the field.
                    return where;
                }
            }
        }
        return null;
    }

    /**
    * Check whether or not this rabbit is to give birth at this step.
    * New births will be made into free adjacent locations.
    * @param newRabbits A list to add newly born rabbits to.
    */ 
    private void giveBirth(List<Actor> newStriders)
    {
        // New striders are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Strider young = new Strider(false, field, loc);
            newStriders.add(young);
        }
    }
        
    /**
    * Generate a number representing the number of births,
    * if it can breed.
    * @return The number of births (may be zero).
    */ 
    private int breed()
    {
        int births = 0;
        if(getAge() >= BREEDING_AGE  && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
    * Return the breeding age for a Rabbit.
    * @return the breeding age for a Rabbit.
    */ 
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    
    /**
    * Make this Strider more hungry. This could result in the Striders death.
    */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            System.out.println("Strider død af sult");
            setDead();
        }
    }
}
