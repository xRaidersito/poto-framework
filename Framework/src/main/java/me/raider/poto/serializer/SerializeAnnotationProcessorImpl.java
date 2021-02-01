package me.raider.poto.serializer;

import me.raider.poto.storage.StorageType;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.utils.Utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SerializeAnnotationProcessorImpl implements SerializeAnnotationProcessor{

    @Override
    public Map<String, Object> serialize(Class<?> clazz, Object instance, StorageType type) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();

        for (Field field : Utils.getAllFields(clazz)) {

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            if (Storable.class.isAssignableFrom(field.getType())) {

                serializeMap.putAll(serializeChild(field.getType(), field.get(instance), field.getAnnotation(Serialize.class).path(), type));
                continue;
            }

            serializeMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));
        }
        return serializeMap;
    }

    @Override
    public Map<String, Object> serializeChild(Class<?> clazz, Object instance, String previousKey, StorageType type) throws IllegalAccessException {

        Map<String, Object> serializeMap = new HashMap<>();

        for (Field field : Utils.getAllFields(clazz)) {

            if (!field.isAnnotationPresent(Serialize.class) || field.get(instance)==null) {
                continue;
            }

            String newKey = previousKey +  "." + field.getAnnotation(Serialize.class).path();

            if (Storable.class.isAssignableFrom(field.getType())) {

                serializeMap.putAll(serializeChild(field.getType(), field.get(instance), newKey, type));
                continue;
            }

            if (type==StorageType.YAML) {
                serializeMap.put(newKey, field.get(instance));
                continue;
            }

            serializeMap.put(field.getAnnotation(Serialize.class).path(), field.get(instance));
        }
        return serializeMap;
    }


}
