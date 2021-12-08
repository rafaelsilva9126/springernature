package constants;

import org.springframework.core.env.ConfigurableEnvironment;

public interface IProperties {

	public ConfigurableEnvironment loadProperties();
}
