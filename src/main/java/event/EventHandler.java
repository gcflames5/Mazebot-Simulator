package event;

import java.lang.annotation.*;

/**
 * Placed before a method to indicate that this method handles events (containing class must be registered in Event class prior)
 *
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * Method priority.
     * @return String
     */
    String priority() default "LOW";
}