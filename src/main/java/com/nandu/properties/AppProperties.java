package com.nandu.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.github.andrewoma.dexx.collection.HashMap;
import com.github.andrewoma.dexx.collection.Map;

import lombok.Data;

// any properies we need for this application we need this
@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="admin") // use of this line from where configaration properties are started
public class AppProperties {
	
	// this responsible for load messages 
	
	private Map<String,String> messages = new HashMap<>();

}
