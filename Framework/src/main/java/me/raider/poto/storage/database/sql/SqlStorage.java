package me.raider.poto.storage.database.sql;

import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.Storable;
import me.raider.poto.storage.StorageType;

public abstract class SqlStorage<T extends Storable> extends AbstractStorage<T> {

    private AbstractSqlDatabase abstractSqlDatabase;

    public SqlStorage(String name, AbstractSqlDatabase abstractSqlDatabase) {
        super(name, StorageType.MYSQL);
        this.abstractSqlDatabase=abstractSqlDatabase;
    }

    public AbstractSqlDatabase getSqlDatabase() {
        return abstractSqlDatabase;
    }
}
