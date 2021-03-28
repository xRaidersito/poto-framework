package me.raider.poto.commons.serializer;

import me.raider.poto.commons.serializer.annotated.SerializeAnnotationProcessor;
import me.raider.poto.commons.serializer.bind.Binder;
import me.raider.poto.commons.serializer.repository.FindableRepository;

public interface SerializerManager {

    <T> void serialize(T instance);

    <T> T deserialize(Class<T> clazz, String key);

    Binder getBinder();

    FindableRepository getRepository();

    void setRepository(FindableRepository repository);

    SerializeAnnotationProcessor getProcessor();

}
