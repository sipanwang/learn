# Spring Boot使用java poi操作excel原理


功能说明：
    
    1、通过读取readExcel.xlsx对需要脱敏的字段进行脱敏处理，将脱敏后的数据输出到exportExcel.xlss中
    1、配置文件application.yml中配置了excel读取和导出excel路径，和数据脱敏的秘钥，脱敏方法根据加密方法而定
    2、启动app类，自动执行load方法，自动解析脱敏并输出到指定路径
    
问题总结：

    1、把application.yml中参数注入到@Component属性的类，例如@Value("${poi.excel.readPath}"))，取不到值
       
       解决办法：因为spring boot启动后默认只扫描带有@SpringBootApplication注解的启动类同级和子级的包下的注解
       而我需要注入yml属性的类是根据spring boot规范分包存放的，因此和启动类均不同级，若需要注入yml属性，需要将
       每个@Component属性类所在的包，均使用注解@ComponentScan("com.sipw.loader")配置到带有@SpringBootApplication
       注解的启动类，若不配置，则@Value注解取值均取不到，为null,这是个很常见的错误，需要注意。
       
    2、启动spring boot报错：Failed to configure a DataSource: 'url' attribute is not specified and no embedd
     
        翻译就是：无法配置DataSource：未指定'url'属性，也无法配置嵌入数据源。很明显，就是在yml中
        没有配置datasource的一些相关属性，例如：地址值啊，数据库驱动啊，用户名啊，密码等，但是也可以不配置，
        需要在启动类头部声明@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})就可以了
        
    3、spring boot 启动加载 CommandLineRunner和 @PostConstruct
     
        在项目中，经常有这样的需求，我们需要在项目启动完立即初始化一些数据（比如缓存等），以便后面调用使用。
        spring boot可以通过CommandLineRunner接口实现启动加载功能。
        新建一个Java文件，类需要用Component声明下，需要实现CommandLineRunner接口，然后重写run方法，在run方法内编写需要加载的内容。
       
        总结：
        CommandLineRunner会在服务启动之后被立即执行。
        CommandLineRunner可以有多个，且多个直接可以用order注解进行排序。
        参考链接：https://blog.csdn.net/cx1110162/article/details/87866633
        
        @PostConstruct是项目还没启动的时候，在Init类注入完成后立马执行的，它并不依赖于项目的启动，更针对性于当前类文件，
        通常作为为类初始化数据用的。而CommandLineRunner更服务于整个项目。
      
    4、静态方法引用非静态方法报错"non-static method cannot be referenced from a static context"
      
        报错的意思就是非静态方法不能被静态上下文引用，同理非静态变量也不能被静态方法引用。
        静态方法和非静态方法的区别：
        
        1.静态方法可以直接调用同类中的静态成员，但是不能直接调用非静态成员，因为静态成员在对象创建之前就要写入内存，
        所以它在内存中是实实在在的存在的，而非静态还不存在内存中，所以不能调用,也就是静态方法使用的东西比较受限制，
        如果需要在静态方法中调用非静态成员，需要通过创建该类的对象来调用，即new一个类的对象，通过对象.属性来调用。
        而普通的成员方法则可以直接调用，静态非静态成员变量，无太大要求。
        
        那什么时候才会用到static修饰，以及它的优缺点是什么呢？
        
        当一个方法或者变量需要初始化加载，或者是经常被调用的时候可以加上static。
        用static修饰的方法可以用类名直接调用，不用的一定要先实例化一个对象然后才可以调用
        比如 person这个类里面有一个方法public static add(){}
        那么可以直接用person类调用 person.add();当然也可以用下面的方法先new出一个对象在调用也是可以
        如果这个方法前面没有static 比如 public add(){}
        那么先要person p=new person();然后用p.add();
        类加载器在加载这个类的时候就已经实例化了这个静态变量。
        坏处：初始化加载，比较占内存，所以不经常用的方法，不建议加此关键字。
        
        类和对象的区别：
        
        类，顾名思义，是某一类事物的模板，描述一类对象的行为和状态；
        对象，对象是类的一个实例，有状态和行为。
        例如，小明是一个对象，小明属于的学生群体是一个类。
        一个类是由属性和方法组成的。属性相当于变量，而方法相当于函数。
        
       4.1、类的结构
        
            访问权限 class 类名{
                访问权限 数据类型 属性名；
                ...
                访问权限 返回值类型 方法名（参数类型 参数名,,）{
                    方法体....
                }
            }
            例如：
              public class Student{
                 //学生的姓名,分数属性
                 public String name;
                 public int score;
                 
                 //学生的学习方法
                 pbulic void study(){
                    score+-5;
                    System.out.println(name+"正在学习，分数是"+socre);
                 }
              }
       4.2、对象的定义
              
              定义了类之后，必须创建一个该类的对象才能实现类中的具体功能
              创建对象的格式如下：
              类名 对象名 = new 类名();
              定义了对象后就可以调用类的属性与方法了
              调用属性; 对象名.属性名=值;
              当属性的访问权限设置为private时要注意此时的属性只能类内部调用，要想外部调用必须加入方法：
              调用方法：对象名.方法名(实参,,);
    
    5、使用for循环时报错java.lang.IndexOutOfBoundsException: Index: 2, Size: 2
       
       解决办法：数组越界，检查数组的size()大小，以及调用是否超过了数组的最大index，这个错误是因为数组的size()是2，也就是index从0,1开始
       而我取index的时候取的2，数据根本没有这个下标可以拿，因此报了数组越界错误。
       
    6、spring boot 通过@Value给静态变量注入yml值
        我们知道spring boot @Value注解只能给普通变量注入值，不能直接给静态变量注入值，直接注入的值都是null,但是有时候我们需要把变量值传入静态方法，
        因此必须把@Value值注入到静态变量里，若要给静态变量赋值，需要在类上使用@Component注解声明，并将@Value注解声明在set方法之上，
        记得启动类要扫描@Component所在包，否则注入不了。
        例如：
        @Component
        @PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
        public class ExcelReader {
            private static String secretKey;
            
            //给静态变量注入@value,生成set方法后需删除static
            @Value("${poi.excel.secretKey}")
            public void setSecretKey(String secretKey) {
               ExcelReader.secretKey = secretKey;
            }
        }
        
        classpath路径默认是spring boot生成的resource目录下
        ignoreResourceNotFound = true,表示当找不到这个配置文件时，则跳过，这样就不会抛出FileNotFoundException异常了，默认为false，设置为false时找不到就会报错。
        实际开发中建议配置false.
        
    7、excel日期转换时异常  parse (java.lang.String) in DateFormat cannot be applied to (java.util.Date)(接收类型问题)
        和Cannot format given Object as a Date(不能把赋值转换成Date类型，传值问题)
     
        我在做excel日期处理时，想把时间字段按照固定格式输出，如 12/26/2019 15:53:48
        我用了SimpleDateFormat这个类去设置时间格式，如下
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        我在使用这个方法时报了错cell.setCellValue(sdf.parse(data.getTransTime()));
        经debug调试，我取到的data.getTransTime()格式是 Wed Dec 25 16:39:04 CST 2019,这是一个典型的date格式，
        而我定义的sdf格式为MM/dd/yyyy HH:mm:ss,因此parse的时候便报错了无法转换。
        后来我换成了sdf.format,将日期转换成字符串输出,问题解决了,代码如下
        cell.setCellValue(sdf.format(data.getTransTime())); 
        总结：sdf.parse是将字符串转换成日期，字符串的格式必须和new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");中定义的一致，
        而且必须用Date类型接收，传入的参数必须是String类型。另外sdf.parse().getTime(),是获取毫秒单位的时间戳。
        而sdf.format是将Date类型的日期格式化成字符串，格式化的效果和new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");中定义的一致，
        需要用String类型接收，传入的参数必须是Date类型。
     
    8、读取excel，日期转换成了double类型，带小数点的数值，如4387.2132121444
        
       原因：因为使用poi工具类读取excel的时候，会把日期转换成double类型，因此在读取excel的时候要做sdf.parse()处理。
    
    9、maven依赖
        9.1 Java使用POI读取和写入Excel 
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi</artifactId>
                <version>3.17</version>
            </dependency>
            <dependency>
                <groupId>org.apache.poi</groupId>
                <artifactId>poi-ooxml</artifactId>
                <version>3.17</version>
            </dependency>
            
        9.2 使用单元测试类@Test所需依赖
        
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.10</version>
            </dependency>
            
        9.3 logger.info所属java类
            import java.util.logging.Logger;