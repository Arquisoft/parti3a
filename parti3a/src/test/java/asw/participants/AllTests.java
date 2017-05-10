package asw.participants;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	GetParticipantInfoControllerTest.class,
	ChangeInfoControllerTest.class
})
public class AllTests {

}
