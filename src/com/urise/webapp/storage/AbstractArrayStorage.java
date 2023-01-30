package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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
    public void updateResume(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        storage[index] = r;
    }

    @Override
    public void saveResumeStorage(Resume r) {
        int index = getIndex(r.getUuid());
        if (countResume == storage.length) {
            throw new StorageException(r.getUuid() + " resume cannot be saved there " +
                    "is no place in the database", r.getUuid());
        } else if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r, index);
        }
    }

    @Override
    public Resume getResume(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void deleteResume(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAllResume() {
        return Arrays.copyOf(storage, countResume);
    }

    @Override
    public int sizeStorage() {
        return countResume;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(Resume r, int index);

    protected abstract void deleteResume(int index);
}
