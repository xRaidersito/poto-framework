package me.raider.poto.serializer;

import me.raider.poto.serializer.field.SerializeAnnotatedFieldsManager;
import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface Serializer<T extends Storable> {

    /**
     * Will create a new {@link SerializedObject} with the given instance.
     *
     * @param instance The instance of the object to serialize.
     * @return A {@link SerializedObject} that will manage the object.
     */
    SerializedObject<T> serialize(T instance);

    /**
     * Will create a new {@link SerializedObject} with the given map.
     *
     * @param map The instance of the map that holds the data.
     * @return A {@link SerializedObject} that will manage the object.
     */
    SerializedObject<T> deserialize(Map<String, Object> map);

    SerializeAnnotatedFieldsManager getAllAnnotatedFields(Class<?> clazz);

}
