package cn.jt.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//这个接口相当于把原来的服务提供者项目当成一个Service类
@FeignClient(value="provider-user")
public interface EurekaServiceFeign {
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name);
}
