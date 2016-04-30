import java.util.List;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public abstract class Animal extends Actor
{
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        super(field, location);
        setAlive();
    }
    
    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimals A list to add newly born animals to.
     */
    abstract public void act(List<Actor> newActors);
    
    /**
     * An Animal can breed if it has reached the breeding age.
     * @return true if the Animal can breed, false otherwise.
     */
    public boolean canBreed()
    {
        return getAge() >= getBreedingAge();
    }
    
    /**
     * Return the breeding age for an Animal.
     * @return the breeding age for an Animal.
     */
    abstract public int getBreedingAge();
}
