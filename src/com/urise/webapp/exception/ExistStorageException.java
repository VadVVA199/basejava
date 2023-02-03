package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super(uuid + " is already in the database", uuid);
    }
}
