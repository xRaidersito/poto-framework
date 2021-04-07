package me.raider.plib.commons.serializer.annotated;

import me.raider.commons.utils.BuildableObject;

import java.util.Map;

public interface ProcessorResult extends BuildableObject {

    Map<SerializedKey, SerializedField> getFields();

    interface Builder extends me.raider.commons.utils.Builder<ProcessorResult> {

        Builder add(SerializedKey key, SerializedField field);

    }
}
