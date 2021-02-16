package me.raider.poto.serializer.field;

import me.raider.poto.serializer.SerializerException;

import java.util.List;

public class SerializeAnnotatedObjectFields {

    private final List<SerializeAnnotatedField> annotatedFieldList;

    public SerializeAnnotatedObjectFields(List<SerializeAnnotatedField> annotatedFieldList) {
        this.annotatedFieldList=annotatedFieldList;
    }

    public String[] getOrganizedPaths() {

        String[] paths = new String[annotatedFieldList.size()];

        int index = 1;

        int uniqueKeyCount = 0;

        for (SerializeAnnotatedField field : annotatedFieldList) {

            if (field.isUniqueKey()) {
                paths[0]=field.getName();
                uniqueKeyCount++;
                continue;
            }

            paths[index] = field.getName();
            index++;

            if (uniqueKeyCount==2) {
                throw new SerializerException("More than 1 key");
            }

        }
        return paths;
    }
}
