package me.raider.poto.storage.types.database.sql;

import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.storage.StorageType;

public abstract class SqlStorage<T extends Storable> extends AbstractStorage<T> {

    private AbstractSqlDatabase abstractSqlDatabase;

    public SqlStorage(String name, AbstractSqlDatabase abstractSqlDatabase) {
        super(name, StorageType.MYSQL);
        this.abstractSqlDatabase=abstractSqlDatabase;
    }

    @Override
    public void load(String key) {

    }

    @Override
    public void save(String key) {

    }
}
