#SpringCloud 1.0版本

[借鉴博客](https://blog.csdn.net/dhq_blog/article/details/81260293)

#小结
    1.搭建服务中心 --- eureka模块
    引入eureka依赖
    创建启动类，使用@EnableEurekaServer注解指定该模块为服务组测中心
    配置application.properties
        tomcat服务器端口号 server.port
        服务名称 spring.application.name=eureka-service
                eureka.instance.hostname
        设置注册中心的url eureka.client.serviceUrl.defaultZone = http://${eureka.instance.hostname}:${server.port}/eureka 
        注：
        注册中心不需要注册自己 eureka.client.register-with-eureka = false
        注册中心不需要发现自己 eureka.client.fecth-registry = false
    启动服务并访问 ip:localhost:1111 无问题 ok
    2.注册服务提供者 --- serivce模块
    引入eureka客户端依赖和ribbon依赖实现负载均衡
    创建启动类，使用@EnableDiscoveryClient注解让服务使用eureka服务器，实现服务的注册与发现
    创建controller 注入 DiscoveryClient 由dc.getLocalServiceInstance获取ServiceInstance
    配置application.properties
        tomcat服务器端口号 server.port = 9090
        设置服务名 spring.application.name=hello-service
        向服务注册中心注册自己 eureka.client.serviceUrl.defaultZone=http://localhost:1111/eureka
    启动并测试
    3.服务的注册与发现 --- consumer模块
    引入依赖（比service模块多导入了ribbon依赖实现负载均衡）
    创建启动类 使用@EnableDiscoveryClient注解让服务使用eureka服务器实现服务的注册与发现
        注：@LoadBalanced实现负载均衡
            @Bean注入RestTemplate对象
    创建controller 注入 RestTemplate对象
    接口使用rt.getForObject("http://hello-service/hello",String.class);
    调用服务
    配置 application.properties(和service模块一样)
        配置tomcat服务器端口号 server.port=9999
        配置服务名 spring.application.name=hello-consumen
        配置注册中心的url eureka.client.serviceUrl.defaultZone =http://localhost:1111/eureka
    启动测试
    访问 http://localhost:9999/hello-consumer
    ok