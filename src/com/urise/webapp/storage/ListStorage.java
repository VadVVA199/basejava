package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> storage = new ArrayList<>();

    @Override
    public void clearStorage() {
        storage.clear();
    }

    @Override
    public void doUpdate(Object searchKey, Resume r) {
        storage.set((int) searchKey, r);
    }

    @Override
    public void doSave(Object searchKey, Resume r) {
        storage.add(r);
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    public void doDelete(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    public List<Resume> getAllResume() {
        return storage;
    }

    @Override
    public int sizeStorage() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(String uuid) {
        int index = (int) getSearchKey(uuid);
        return index >= 0;
    }
}
