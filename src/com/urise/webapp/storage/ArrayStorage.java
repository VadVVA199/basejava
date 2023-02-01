package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r, int index) {
        storage[countResume] = r;
        countResume++;
    }

    @Override
    protected void deleteResume(int index) {
        storage[index] = storage[countResume - 1];
        storage[countResume - 1] = null;
        countResume--;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        int index = -1;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return index;
    }

    @Override
    protected boolean isExist(String uuid) {
        int index = (int) getSearchKey(uuid);
        return index != -1;
    }
}
