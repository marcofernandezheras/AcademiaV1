package model.utils;

import java.io.IOException;

public interface ObjectReader extends AutoCloseable {	
	public Object nextObject() throws IOException;
}
