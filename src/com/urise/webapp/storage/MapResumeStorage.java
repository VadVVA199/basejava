package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        storage.replace(((Resume) searchKey).getUuid(), r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
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
        return storage.get(uuid);
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
