# rate-limiter


**Configuration File** - RateLimiter can take configuration in YAML and JSON file format. Any other file format (i.e XML) can be plugged in easily by just providing implementtion.

**Repository** - Current implementation is having In-Memory repository to store rate data. Redis, Aerospike or any other repository can be plugged in by just providing implementation to make Rate Limiter work in distributed environment.

**Interceptor** - Spring boot microservices interceptor implementation provided with current implementation. Other Interceptor can be plugged in by just providing implementation.
 
**Sample code to add interceptor** : addInterceptor(new SpringBootInterceptor(new YamlConfReader(),new InMemoryRepository()));

# maven
```
<dependency>
   <groupId>com.rakesh.ratelimiter</groupId>
   <artifactId>rate-limiter</artifactId>
   <version>1.0-SNAPSHOT</version>
</dependency>
 ```
 
 # Sample Configuration File
 
 ```
 clients:
  dot.com:
    limit:
      hour: 20
    specialization:
      api:
        "/pay":
          limit:
            min: 5
        "/catalog":
          limit:
            min: 5
      method:
        post:
          limit:
            hour: 5
        get:
          limit:
            min: 15
  dot.org:
    limit:
      min: 2
      hour: 10
    specialization:
      api:
        "/pay":
          limit:
            min: 9
            sec: 3
        "/details":
          limit:
            min: 9
            sec: 3
      method:
        post:
          limit:
            min: 9
            sec: 3
        get:
          limit:
            min: 9
            sec: 3
            
 ```
 
