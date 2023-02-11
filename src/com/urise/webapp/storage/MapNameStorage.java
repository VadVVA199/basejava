package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapNameStorage extends AbstractStorage {

    private final Map<Integer, Resume> storage = new HashMap<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getKeyResumeMap(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((Integer) searchKey);
    }

    @Override
    protected List<Resume> getAllResume() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected int sizeStorage() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (Map.Entry<Integer, Resume> resume : storage.entrySet()) {
            if (resume.getValue().getUuid().equals(uuid)) {
                return resume.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(String uuid) {
        for (Map.Entry<Integer, Resume> resume : storage.entrySet()) {
            if (resume.getValue().getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
