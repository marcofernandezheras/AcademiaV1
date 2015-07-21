package model.utils;

import java.io.IOException;

public interface ObjectWriter extends AutoCloseable{
	public void writeObject(Object input) throws IOException;
}
