package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    public static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int countResume;

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
        System.out.println("There is no resume in the database");
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            printResumeMissing(r.getUuid());
            return;
        }
        storage[index] = r;
        System.out.println(r.getUuid() + " this resume has been update");
    }

    public abstract void save(Resume r);

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            System.out.println(uuid + " resume sent");
            return storage[index];
        }
        printResumeMissing(uuid);
        return null;
    }

    public abstract void delete(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    protected void printResumeMissing(String uuid) {
        System.out.println(uuid + " there is no resume in the database");
    }
    protected abstract int getIndex(String uuid);
}
