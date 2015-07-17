package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileObjectReader implements ObjectReader {

	private final ObjectInputStream inStream;
	
	public FileObjectReader(String file) throws IOException{
		inStream = new ObjectInputStream(new FileInputStream(file));
	}
		
	@Override
	public Object nextObject() throws IOException {
		try {
			return inStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new IOException("Invalid Class");
		}
	}
	
	@Override
	public void close() throws Exception {
		inStream.close();
	}

}
