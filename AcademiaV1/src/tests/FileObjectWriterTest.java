package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import model.FileObjectReader;
import model.FileObjectWriter;
import model.ObjectReader;

import org.junit.Before;
import org.junit.Test;

public class FileObjectWriterTest {

	private static final String TEST_FILE = "src/tests/FileObjectWriterTestFile.dat";
	private static final String TEST_OBJECT_ONE = "Hi";
	private static final String TEST_OBJECT_TWO = "Bye";
	
	@Before
	@Test
	public void deleteTestFiles()
	{
		File testFile = new File(TEST_FILE);
		if (testFile.exists())
			testFile.delete();
		assertFalse(testFile.exists());
	}
	

	@Test
	public void testWriteObject() {		
		try (FileObjectWriter writer = new FileObjectWriter(TEST_FILE)) {
			writer.writeObject(TEST_OBJECT_ONE);
			writer.writeObject(TEST_OBJECT_TWO);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e1) {
			e1.printStackTrace();
			fail();
		}
		
		File testFile = new File(TEST_FILE);
		assertTrue(testFile.exists());
		
		try(ObjectReader reader = new FileObjectReader(TEST_FILE))
		{
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAppendObject(){
		try (FileObjectWriter writer = new FileObjectWriter(TEST_FILE)) {
			writer.writeObject(TEST_OBJECT_ONE);
			writer.writeObject(TEST_OBJECT_TWO);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e1) {
			e1.printStackTrace();
			fail();
		}
		
		//Append 2 more object 
		try (FileObjectWriter writer = new FileObjectWriter(TEST_FILE)) {
			writer.writeObject(TEST_OBJECT_ONE);
			writer.writeObject(TEST_OBJECT_TWO);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		} catch (Exception e1) {
			e1.printStackTrace();
			fail();
		}
		
		File testFile = new File(TEST_FILE);
		assertTrue(testFile.exists());
		
		try(ObjectReader reader = new FileObjectReader(TEST_FILE))
		{
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());	
			assertEquals(TEST_OBJECT_ONE, reader.nextObject());
			assertEquals(TEST_OBJECT_TWO, reader.nextObject());	
		} catch (IOException e) {	
			e.printStackTrace();
			fail();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testClose() {
		try {
			FileObjectWriter writer = new FileObjectWriter(TEST_FILE);
			writer.close();		
			try {
				writer.writeObject(TEST_OBJECT_ONE);
				fail("Write on a closed FileObjectWriter must throw an IOException");
			}
			catch(IOException e)
			{
				//Empty because we wait an exception here
				//cause you can't write an object on a closed writer. 
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
	}

}
