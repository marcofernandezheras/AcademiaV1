package model.utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import model.exceptions.ReadException;
import model.exceptions.crud.NotFoundException;

public class FileObjectReader<T extends Serializable> implements ObjectReader<T> {

	private final ObjectInputStream inStream;
	private boolean opened = false;
	
	public FileObjectReader(String file) throws IOException{
		inStream = new ObjectInputStream(new FileInputStream(file));
		opened = true;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public T nextObject() throws ReadException, NotFoundException {
		if(!opened)
			throw new ReadException("Can't read on a closed FileObjectReader");
		try {
			return (T) inStream.readObject();
		} catch (ClassNotFoundException e) {
			throw new ReadException("Invalid Class");			
		}
		catch (EOFException e) {
			throw new NotFoundException("End of file");
		}
		catch (IOException e) {
			throw new ReadException(e.getMessage());
		}
	}
	
	@Override
	public void close() throws Exception {
		opened = false;
		inStream.close();
	}

}
