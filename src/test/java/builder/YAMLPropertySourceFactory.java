package builder;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

public class YAMLPropertySourceFactory implements PropertySourceFactory {

	Log log;
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {

		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(encodedResource.getResource());	
		
		Properties properties = factory.getObject();
		
		return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
		
	}

}
