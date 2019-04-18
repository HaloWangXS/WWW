## JDBC必知必会

### 1 单数据源
#####a.手动配置数据源(代码配置)
    配置DateSource
    配置相关事务 PlatformTransactionManager

    

### PS: CommandLineRunner
    在Spring 项目启动时,需要一些预先数据的加载,Springboot为我们提供一个简单的实现 -- CommandLineRunner
    CommandLineRunner 是一个接口, 我们只需要实现接口就可以实现数据的预加载,对于多个加载数据,我们可以使用@Order来排序
    
    例子:
    @Component
    @Order(value = 2)
    public class Runner1 implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 Runner1 order 2 <<<<<<<<<<<<<");
        }
    }
    
    @Component
    @Order(value = 1)
    public class Runner2 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 Runner2 order 1 <<<<<<<<<<<<<");
        }
    }
    
    结果:
    >>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 Runner2 order 1 <<<<<<<<<<<<<
    >>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 Runner1 order 2 <<<<<<<<<<<<<
    
    
    
    