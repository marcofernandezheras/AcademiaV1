package tests;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.FileObjectReader;

import org.junit.BeforeClass;
import org.junit.Test;

public class FileObjectReaderTest {

	private static final String TEST_FILE = "src/tests/FileObjectReaderTestFile.dat";
	private static final String TEST_OBJECT_ONE = "Hi";
	private static final String TEST_OBJECT_TWO = "Bye";
	@BeforeClass
	public static void createTestFile(){
		try (  FileOutputStream fileStream = new FileOutputStream(TEST_FILE);
			   ObjectOutputStream outStream = new ObjectOutputStream(fileStream))
		{
			outStream.writeObject(TEST_OBJECT_ONE);
			outStream.writeObject(TEST_OBJECT_TWO);
		} catch (IOException e) {			
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void testNextObject() {
		try(FileObjectReader reader = new FileObjectReader(TEST_FILE))
		{
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}				
	}
	
	@Test
	public void testNextObjectUntilEnd() {
		try(FileObjectReader reader = new FileObjectReader(TEST_FILE))
		{
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());		
			reader.nextObject();
			fail();
		}
		catch (EOFException e) {
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}				
	}

	@Test
	public void testClose() {
		try {
			FileObjectReader reader = new FileObjectReader(TEST_FILE);
			reader.close();
			reader.nextObject();
			fail();
		} 
		catch (IOException e) {
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
