package me.raider.poto.commons.serializer.annotated;

import me.raider.poto.commons.BuildableObject;

import java.util.Map;

public interface ProcessorResult extends BuildableObject {

    Map<SerializedKey, SerializedField> getFields();

    interface Builder extends me.raider.poto.commons.Builder<ProcessorResult> {

        Builder add(SerializedKey key, SerializedField field);


    }

}
