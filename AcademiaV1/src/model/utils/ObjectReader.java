package model.utils;

import model.exceptions.ReadException;
import model.exceptions.crud.NotFoundException;

public interface ObjectReader<T> extends AutoCloseable {	
	public T nextObject() throws ReadException, NotFoundException;
}
