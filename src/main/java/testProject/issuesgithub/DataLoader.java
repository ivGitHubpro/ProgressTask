package testProject.issuesgithub;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
/**
 * Data loader class to load properties
 */
public class DataLoader {
	private static final String RESOURCE_CUSTOMERS_PROPERTY = "Properties/";
	private Properties properties;
	private final String tokenPartOne;
	private final String tokenPartTwo;
	private final String userName;
	private final String password;

	public DataLoader() {
		properties = new Properties();

		try {
			InputStream propertiesStream = this.getClass().getClassLoader()
					.getResourceAsStream(RESOURCE_CUSTOMERS_PROPERTY + "/general.properties");
			properties.load(propertiesStream);

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.tokenPartOne = Objects.requireNonNull(properties.getProperty("tokenPartOne"));
		this.tokenPartTwo = Objects.requireNonNull(properties.getProperty("tokenPartTwo"));
		this.userName = Objects.requireNonNull(properties.getProperty("username"));
		this.password = Objects.requireNonNull(properties.getProperty("password"));
	}


	
	
	public String getToken() {
		return tokenPartOne+tokenPartTwo;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}