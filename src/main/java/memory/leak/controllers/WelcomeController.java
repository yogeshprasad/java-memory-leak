package memory.leak.controllers;

import memory.leak.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class WelcomeController {

	private List<Test> objects = new ArrayList<>();

	@RequestMapping("/threads")
	public String threads() {
		ExecutorService service = Executors.newFixedThreadPool(10, new CustomizableThreadFactory("findme-"));
		for(int i=0;i<=10;i++) {
			service.execute(new Runnable() {

				@Override
				public void run() {}
			});
		}
		return "Added Threads!";
	}
	@RequestMapping("/objects")
	public String objects() throws IOException{
		String str = new Scanner(new File("test.txt"), "UTF-8").useDelimiter("\\A").next();
		for(int x=0;x<=1000;x++) {
			str.intern();
			objects.add(new Test(str));
		}
		return "Added Objects!";
	}
}
