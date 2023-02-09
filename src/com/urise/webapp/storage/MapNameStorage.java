package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapNameStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove((String) searchKey);
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
        for (Map.Entry<String, Resume> resume : storage.entrySet()) {
            if (resume.getValue().getUuid().equals(uuid)) {
                return resume.getKey();
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(String uuid) {
        for (Map.Entry<String, Resume> resume : storage.entrySet()) {
            if (resume.getValue().getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
