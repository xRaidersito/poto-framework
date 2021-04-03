package me.raider.poto.commons.serializer;

import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;
import me.raider.poto.commons.serializer.bind.Binder;
import me.raider.poto.commons.serializer.factory.InstanceFactoryManager;
import me.raider.poto.commons.serializer.repository.FindableRepository;

public class SimpleSerializerManager implements SerializerManager {

    private final Binder binder;
    private final SerializeAnnotationProcessor annotationProcessor;
    private final FindableRepository<?> findableRepository;
    private final InstanceFactoryManager factoryManager;

    public SimpleSerializerManager(Binder binder, SerializeAnnotationProcessor annotationProcessor,
                                   FindableRepository<?> findableRepository, InstanceFactoryManager factoryManager) {
        this.binder = binder;
        this.annotationProcessor = annotationProcessor;
        this.findableRepository = findableRepository;
        this.factoryManager = factoryManager;
    }

    @Override
    public <T> void serialize(T instance, String key) {
        serialize(instance, key, this.findableRepository);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String key) {
        return deserialize(clazz, key, this.findableRepository);
    }

    @Override
    public <T> void serialize(T instance, String key, FindableRepository<?> findableRepository) {
        Serializer<T> serializer = new SimpleSerializer<>(findableRepository, annotationProcessor, binder);
        serializer.serialize(instance, key);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String key, FindableRepository<?> findableRepository) {
        Serializer<T> serializer = new SimpleSerializer<>(findableRepository, annotationProcessor, binder);
        SerializedObject serializedObject = serializer.deserialize(clazz, key);
        return factoryManager.getFactory(clazz).create(serializedObject);
    }

    @Override
    public Binder getBinder() {
        return binder;
    }

    @Override
    public FindableRepository<?> getRepository() {
        return findableRepository;
    }

    @Override
    public InstanceFactoryManager getFactory() {
        return factoryManager;
    }

    @Override
    public SerializeAnnotationProcessor getProcessor() {
        return annotationProcessor;
    }
}
