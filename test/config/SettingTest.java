package config;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class SettingTest {
	
	private Setting setting = new Setting();

	@Test
	public void loadPropertiesTest() {
		String dbUrl = setting.getUrl("db_url");
		Assert.assertNotNull("db_url must be notnull", dbUrl);
		System.out.println("Setting Test");
	}

}