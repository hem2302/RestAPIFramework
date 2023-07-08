package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.io.FileInputStream;

public class Utils {
	public static RequestSpecification req;

	public  RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseuri"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		} else
			return req;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\Selenium\\MyRestAPIFramework\\src\\test\\java\\resources\\GlobalProperties.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public static String parseJsonResponse(Response response, String key) {
		String jsonResponse = response.asString();
		JsonPath js = new JsonPath(jsonResponse);
		return js.get(key).toString();
	}
	

}
