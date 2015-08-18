package model.utils;

import model.exceptions.WriteException;

public interface ObjectWriter<T> extends AutoCloseable{
	public void writeObject(T input) throws WriteException;
}
