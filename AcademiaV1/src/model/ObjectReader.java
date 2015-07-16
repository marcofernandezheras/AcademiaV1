package model;

import java.io.EOFException;

public interface ObjectReader extends AutoCloseable {	
	public Object nextObject() throws EOFException;
}
