package me.raider.poto.internal;

import me.raider.poto.Factory;
import me.raider.poto.storage.types.Storable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PotoSerializableObject<T extends Storable> implements SerializableObject<T> {

    private final Factory<T> objectFactory;

    public PotoSerializableObject(Factory<T> objectFactory) {
        this.objectFactory=objectFactory;
    }

    @Override
    public Map<String, Object> serialize(Class<?> clazz, Object instance, boolean yaml) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            if (Storable.class.isAssignableFrom(field.getType())) {

                serializeMap.putAll(serializeSubClasses(field.getType(), field.get(instance), field.getAnnotation(Serialize.class).path(), yaml));
                continue;
            }
            if (!yaml && field.get(instance) instanceof Map) {
                serializeMap.putAll((Map<? extends String, ?>) field.get(instance));
                break;
            }

            serializeMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));
        }
        return serializeMap;
    }

    private Map<String, Object> serializeSubClasses(Class<?> clazz, Object instance, String previousKey, boolean yaml) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            String newKey = previousKey +  "." + field.getAnnotation(Serialize.class).path();

            if (Storable.class.isAssignableFrom(field.getType())) {

                serializeMap.putAll(serializeSubClasses(field.getType(), field.get(instance), newKey, yaml));
                continue;
            }

            if (yaml) {
                serializeMap.put(newKey, field.get(instance));
                continue;
            }

            if (field.get(instance) instanceof Map) {
                serializeMap.putAll((Map<? extends String, ?>) field.get(instance));
                break;
            }

            serializeMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));

        }
        return serializeMap;
    }

    @Override
    public T deserialize(Map<String, Object> map) {
        return objectFactory.create(map);
    }
}
