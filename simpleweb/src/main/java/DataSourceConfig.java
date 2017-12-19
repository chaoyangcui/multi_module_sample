import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by cuiguiyang on 2017/5/29 10:39.
 * Desc:
 */
@Configuration
@MapperScan("com.sssarm.mapper")
@PropertySource(value = "classpath:config/db.properties")
// @Import(SessionFactoryConfig.class)
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    /**
     * 驱动名称
     */
    @Value("${spring.datasource.driverClassName}")
    private String driverClass;
    /**
     * 数据库连接url
     */
    @Value("${spring.datasource.url}")
    private String url;
    /**
     * 数据库用户名
     */
    @Value("${spring.datasource.username}")
    private String userName;
    /**
     * 数据库密码
     */
    @Value("${spring.datasource.password}")
    private String password;

    // @Bean
    private DataSource dataSource() throws SQLException {
        return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
                .driverClassName(driverClass)
                .url(url)
                .username(userName)
                .password(password)
                .build();
    }

    /**
     * mybatis 配置路径
     */
    private static final String MYBATIS_CONFIG = "config/mybatis-config.xml";
    /**
     * mybatis mapper resource 路径
     */
    private static final String MAPPER_PATH = "mapper/BlogMapper.xml";

    private static final String TYPE_ALIAS_PACKAGE = "com.sssarm.domain";

    /**
     * 创建sqlSessionFactoryBean 实例
     * 并且设置configtion 如驼峰命名.等等
     * 设置mapper 映射路径
     * 设置datasource数据源
     *
     * @return
     */
    @Bean
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException, SQLException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /* 设置mybatis configuration 扫描路径 */
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /* 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
        /* 设置datasource */
        sqlSessionFactoryBean.setDataSource(dataSource());
        /* 设置typeAlias 包扫描路径 */
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIAS_PACKAGE);
        return sqlSessionFactoryBean;
    }
    // @Bean
    // public SqlSessionFactoryBean createSqlSessionFactoryBean(@Autowired DataSource dataSource) throws IOException {
    //     SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    //     /* 设置mybatis configuration 扫描路径 */
    //     sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
    //     /* 添加mapper 扫描路径 */
    //     PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
    //     String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
    //     sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
    //     /* 设置datasource */
    //     sqlSessionFactoryBean.setDataSource(dataSource);
    //     /* 设置typeAlias 包扫描路径 */
    //     sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIAS_PACKAGE);
    //     return sqlSessionFactoryBean;
    // }

}