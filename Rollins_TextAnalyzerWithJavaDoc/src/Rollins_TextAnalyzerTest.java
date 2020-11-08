import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JDialog;

import org.junit.jupiter.api.Test;

@SuppressWarnings("serial")
class Rollins_TextAnalyzerTest extends JDialog {

	@Test
	void testSetTitle() {
		
		setTitle("This is a test");
		assertEquals("This is test failed",getTitle());
		
	}

}
