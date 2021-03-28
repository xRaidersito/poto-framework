package me.raider.poto.commons.serializer;

import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;
import me.raider.poto.commons.serializer.bind.Binder;
import me.raider.poto.commons.serializer.repository.FindableRepository;

public class SimpleSerializerManager implements SerializerManager {
    @Override
    public <T> void serialize(T instance) {

    }

    @Override
    public <T> T deserialize(Class<T> clazz, String key) {
        return null;
    }

    @Override
    public Binder getBinder() {
        return null;
    }

    @Override
    public FindableRepository getRepository() {
        return null;
    }

    @Override
    public void setRepository(FindableRepository repository) {

    }

    @Override
    public SerializeAnnotationProcessor getProcessor() {
        return null;
    }
}
