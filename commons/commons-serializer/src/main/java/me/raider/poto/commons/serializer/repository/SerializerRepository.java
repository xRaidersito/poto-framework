package me.raider.poto.commons.serializer.repository;

public interface SerializerRepository {

    <T> T get(Class<T> clazz, String key);

    <T> T set(Class<T> clazz, String key, Object instance);

    boolean contains(String key);

    default String getString(String key) {
        return get(String.class, key);
    }

    default int getInt(String key) {
        return get(int.class, key);
    }

    default Object get(String key) {
        return get(Object.class, key);
    }

    default double getDouble(String key) {
        return get(double.class, key);
    }

    default boolean getBoolean(String key) {
        return get(boolean.class, key);
    }

    default void setString(String key, Object instance) {
        set(String.class, key, instance);
    }

    default void setInt(String key, Object instance) {
        set(int.class, key, instance);
    }

    default void set(String key, Object instance) {
        set(Object.class, key, instance);
    }

    default void setDouble(String key, Object instance) {
        set(double.class, key, instance);
    }

    default void setBoolean(String key, Object instance) {
        set(boolean.class, key, instance);
    }


}
