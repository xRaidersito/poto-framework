package me.raider.plib.commons.serializer;

import me.raider.plib.commons.serializer.bind.Binder;
import me.raider.plib.commons.serializer.factory.InstanceFactoryManager;
import me.raider.plib.commons.serializer.annotated.SerializeAnnotationProcessor;
import me.raider.plib.commons.serializer.repository.FindableRepository;

public interface SerializerManager {

    /**
     * Serialize the given instance with the identifier and the default findable repository.
     *
     * @param instance The instance to serialize.
     * @param key The identifier.
     */
    <T> void serialize(T instance, String key);

    <T> T deserialize(Class<T> clazz, String key);

    <T> void serialize(T instance, String key, FindableRepository<?> findableRepository);

    <T> T deserialize(Class<T> clazz, String key, FindableRepository<?> findableRepository);

    /**
     * Gets the interface binder.
     *
     * @return The {@link Binder} of the manager.
     */
    Binder getBinder();

    /**
     * Gets the field processor.
     *
     * @return The {@link SerializeAnnotationProcessor}.
     */
    SerializeAnnotationProcessor getProcessor();

    /**
     * Gets the default findable repository.
     *
     * @return The default {@link FindableRepository} of the manager.
     */
    FindableRepository<?> getRepository();

    /**
     * Gets the factory manager that has all the registered factories.
     *
     * @return The linked {@link InstanceFactoryManager}.
     */
    InstanceFactoryManager getFactory();

}
