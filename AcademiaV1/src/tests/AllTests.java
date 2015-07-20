package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
		FileObjectReaderTest.class, 
		FileObjectWriterTest.class,
		FileStudentManagerTest.class })
public class AllTests {

}
