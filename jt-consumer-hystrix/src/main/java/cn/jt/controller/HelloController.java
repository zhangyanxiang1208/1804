package cn.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.jt.feign.EurekaServiceFeign;
import cn.jt.feign.SidecarServiceFeign;

@RestController
public class HelloController {
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	@Autowired
	private SidecarServiceFeign sidecarServiceFeign;
	
	@RequestMapping("/hello/{name}")
	//支持断路器hystrix,fallbachmethod参数指定失败时方法名
	@HystrixCommand(fallbackMethod="fallbackHello")  
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}
	
	public String fallbackHello(String name){
		return "失败了，略略略"; //设置一个默认返回值
	}
	
	@RequestMapping("/index")
	public String node(){
		return sidecarServiceFeign.node();
	}
	
}
