package oneSpringBoot;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liucaijin.dao.QuerySelect;

@Controller
@ComponentScan("com.liucaijin")
@EnableAutoConfiguration
public class MyApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{

	@Autowired
	QuerySelect querySelect;

//	static {
//		try{
//			// 初始化log4j
//			String log4jPath = "";
//			log4jPath = MyApplication.class.getClassLoader().getResource("").getPath()+"log4j.properties";
//			PropertyConfigurator.configure(log4jPath);
//		}catch (Exception e){
//			System.out.println(e.getMessage());
//		}
//	}

	private static Logger logger = Logger.getLogger(MyApplication.class);


	@RequestMapping("/")
	@ResponseBody
	String home() {
		System.out.println(":::::::::"+querySelect.queryCount());
		logger.info("Hello World!!!");
		return "Hello World!";
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(MyApplication.class, args);
        if (context instanceof EmbeddedWebApplicationContext) {
            int port = ((EmbeddedWebApplicationContext) context).getEmbeddedServletContainer().getPort();
            String contextPath = context.getApplicationName();
            String url = String.format(Locale.US, "http://localhost:%d%s", port, contextPath);
            System.out.println("url: " + url);

        }
		 
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(8081);
	}

}
