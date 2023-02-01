package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    public static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume;

    @Override
    public void clearStorage() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    @Override
    public void doUpdate(Object searchKey, Resume r) {
        storage[(int) searchKey] = r;
    }

    @Override
    public void doSave(Object searchKey, Resume r) {
        if (countResume == storage.length) {
            throw new StorageException(r.getUuid() + " resume cannot be saved there " +
                    "is no place in the database", r.getUuid());
        }
        saveResume(r, (int) searchKey);
    }

    @Override
    public Resume doGet(Object searchKey) {
       return storage[(int) searchKey];
    }

    @Override
    public void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
    }

    @Override
    public Resume[] getAllResume() {
        return Arrays.copyOf(storage, countResume);
    }

    @Override
    public int sizeStorage() {
        return countResume;
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
