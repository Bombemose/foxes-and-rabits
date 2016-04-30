import java.util.List;
/**
 * Abstrakt klasse Actor - lav en beskrivelse af klassen her
 * 
 * @author (dit navn her)
 * @version (versions nummer eller dato her)
 */
public abstract class Actor
{
    // instansvariable - erstat eksemplet herunder med dine egne variable
    
    // Whether the actor is alive or not.
    private boolean alive;
    // The actors's field.
    private Field field;
    // The actors's position in the field.
    private Location location;
    // The actors's age.
    private int age;
    
    public Actor(Field field, Location location) {
        //Noth
        this.field = field;
        setLocation(location);
    }

    /**
     * Make this Actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActors A list to add newly born actors to.
     */
    abstract public void act(List<Actor> newActors);

    /**
    * Return the actors location.
    * @return The actors location.
    */
    public Location getLocation()
    {
        return location;
    }
    
    /**
    * Return the actors field.
    * @return The actors field.
    */
    public Field getField()
    {
        return field;
    }
    
    /**
    * Return the actors field.
    * @return The actors field.
    */
    public boolean getAlive()
    {
        return alive;
    }
    /**
    * Place the actor at the new location in the given field.
    * @param newLocation The actors new location.
    */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
    * Indicate that the animal is no longer alive.
    * It is removed from the field.
    */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    /**
    * Indicate that the actor is alive by setting alive = true.
    */
    public void setAlive() {
        alive = true;
    }
    
    /**
    * Check whether the actor is alive or not.
    * @return true if the actor is still alive.
    */
    public boolean isAlive()
    {
        return alive;
    }
    
    /**
    * Return the age of the actor.
    * @return age.
    */
    public int getAge()
    {
        return age;
    }
    
    /**
    * Set the age of the actor.
    * @return age.
    */
    public void setAge(int age)
    {
        this.age = age;;
    }
    
    /**
    * Increase the age.
    * This could result in the rabbit's death.
    */
    public void incrementAge(int maxAge)
    {
        age++;
        if(age > maxAge) {
            setDead();
        }
    }
}
