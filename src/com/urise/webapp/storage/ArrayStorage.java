package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume r) {
        if (countResume == storage.length) {
            System.out.println(r.getUuid() + " resume cannot be saved there is no place in the database");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println(r.getUuid() + " is already in the database");
        } else {
            storage[countResume] = r;
            countResume++;
            System.out.println(r.getUuid() + " resume saved to database");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
            System.out.println(uuid + " resume has been deleted");
            return;
        }
        printResumeMissing(uuid);
    }

    protected int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return index;
    }
}
