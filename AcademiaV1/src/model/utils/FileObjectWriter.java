package model.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import model.exceptions.WriteException;

public class FileObjectWriter<T extends Serializable> implements ObjectWriter<T> {
	
	private final ObjectOutputStream outstream;
	private boolean open;
	
	public FileObjectWriter(String file) throws IOException {
		outstream = getOutputStream(file);
		open = true;
	}
	
	@SuppressWarnings("resource")
	private ObjectOutputStream getOutputStream(String file) throws IOException
	{
		File outfile = new File(file);
		boolean exists = outfile.exists();
		FileOutputStream stream = new FileOutputStream(file,exists);
		BufferedOutputStream buffer = new BufferedOutputStream(stream);
		return exists ? new MyObjectOutputStream(buffer) 
					  : new ObjectOutputStream(buffer);
	}
	
	@Override
	public void close() throws Exception {
		outstream.close();
		open = false;
	}

	@Override
	public void writeObject(T input) throws WriteException {
		if(!open)
			throw new WriteException("Can't write on a closed stream");
		try {
			outstream.writeObject(input);
		} catch (IOException e) {
			throw new WriteException(e.getMessage());
		}
	}	

}
