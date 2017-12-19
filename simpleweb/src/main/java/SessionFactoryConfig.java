import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by cuiguiyang on 2017/5/29 11:40.
 * Desc:
 */
@Configuration
public class SessionFactoryConfig {
    // /**
    //  * mybatis 配置路径
    //  */
    // private static final String MYBATIS_CONFIG = "config/mybatis-config.xml";
    // /**
    //  * mybatis mapper resource 路径
    //  */
    // private static final String MAPPER_PATH = "mapper/BlogMapper.xml";
    //
    // @Autowired
    // private DataSource dataSource;
    //
    // private static final String TYPE_ALIAS_PACKAGE = "com.sssarm.domain";
    //
    // /**
    //  * 创建sqlSessionFactoryBean 实例
    //  * 并且设置configtion 如驼峰命名.等等
    //  * 设置mapper 映射路径
    //  * 设置datasource数据源
    //  *
    //  * @return
    //  */
    // @Bean
    // public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
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
