package model.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class MyObjectOutputStream extends ObjectOutputStream {

	public MyObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		//Vacio para que no escriba la cabecera
	}
}
