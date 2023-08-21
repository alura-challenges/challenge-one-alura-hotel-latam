package config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;

@RunWith(JUnit4.class)
public class SettingTest {
	
	private Setting setting = new Setting();

	@Test
	public void loadPropertiesTest() {
		String dbUrl = setting.getProperty("db_url");
		Assert.assertNotNull("db_url must be notnull", dbUrl);
	}
}