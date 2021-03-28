package me.raider.poto.commons.serializer.annotated;

import java.util.HashMap;
import java.util.Map;

public class ProcessorResultImpl implements ProcessorResult {

    private final Map<SerializedKey, SerializedField> fieldMap;

    private ProcessorResultImpl(Map<SerializedKey, SerializedField> fieldMap) {
        this.fieldMap = fieldMap;
    }

    @Override
    public Map<SerializedKey, SerializedField> getFields() {
        return fieldMap;
    }

    public static class Builder implements ProcessorResult.Builder {

        private final Map<SerializedKey, SerializedField> builderMap = new HashMap<>();

        @Override
        public Builder add(SerializedKey key, SerializedField field) {
            builderMap.put(key, field);
            return this;
        }

        @Override
        public ProcessorResult build() {
            return new ProcessorResultImpl(builderMap);
        }
    }

}
