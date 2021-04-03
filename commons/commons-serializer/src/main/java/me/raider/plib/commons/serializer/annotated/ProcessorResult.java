package me.raider.plib.commons.serializer.annotated;

import me.raider.plib.commons.BuildableObject;

import java.util.Map;

public interface ProcessorResult extends BuildableObject {

    Map<SerializedKey, SerializedField> getFields();

    interface Builder extends me.raider.plib.commons.Builder<ProcessorResult> {

        Builder add(SerializedKey key, SerializedField field);


    }

}
