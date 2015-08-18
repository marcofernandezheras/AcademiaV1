package tests;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.exceptions.ReadException;
import model.exceptions.crud.NotFoundException;
import model.utils.FileObjectReader;

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
		try(FileObjectReader<String> reader = new FileObjectReader<String>(TEST_FILE))
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
		try(FileObjectReader<String> reader = new FileObjectReader<String>(TEST_FILE))
		{
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());		
			reader.nextObject();
			fail();
		}
		catch (NotFoundException e) {
			
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}				
	}

	@Test
	public void testClose() {
		try {
			FileObjectReader<String> reader = new FileObjectReader<String>(TEST_FILE);
			reader.close();
			reader.nextObject();
			fail();
		} 		
		catch (ReadException e) 
		{			
		}catch (Exception e) {
			fail();
			e.printStackTrace();
		}
	}

}
