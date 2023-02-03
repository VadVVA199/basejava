package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void clear() {
        clearStorage();
        System.out.println("There is no resume in the database");
    }

    @Override
    public void update(Resume r) {
        doUpdate(getExistingSearchKey(r.getUuid()), r);
        System.out.println(r.getUuid() + " this resume has been update");
    }

    @Override
    public void save(Resume r) {
        doSave(getNotExistingSearchKey(r.getUuid()), r);
        System.out.println(r.getUuid() + " resume saved to database");
    }

    @Override
    public Resume get(String uuid) {
        Resume resume = doGet(getExistingSearchKey(uuid));
        System.out.println(uuid + " resume sent");
        return resume;
    }

    @Override
    public void delete(String uuid) {
        doDelete(getExistingSearchKey(uuid));
        System.out.println(uuid + " resume has been deleted");
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    @Override
    public int size() {
        return sizeStorage();
    }

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
       if (isExist(uuid)) {
           return searchKey;
       }
        throw new NotExistStorageException(uuid);
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract void clearStorage();

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume[] getAllResume();

    protected abstract int sizeStorage();

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(String uuid);
}
