package model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileObjectWriter implements ObjectWriter {
	
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
	public void writeObject(Object input) throws IOException {
		if(!open)
			throw new IOException("Can't write on a closed stream");
		outstream.writeObject(input);
	}

}
