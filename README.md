spring3-jpa-jta-hibernate4-annotation
=====================================
實作多條datasource連線

參考環境：

  * Tomcat 7.x
  * JDK 1.8
  * Eclipse(compiler 1.8)
  * Gradle
  * Spring 3.2.10
  * Atomikos 3.9.3
  * Hibernate <strike>4.3.6 Final</strike> 4.2.15.Final
  * MYSQL 5.x
  
==========================
使用方式：(請修改以下符合你的datasource)

在com.gn.sub.config的JpaHibernateAConfig 跟 JpaHibernateBConfig


         @Bean(name = "dataSource1", initMethod = "init" , destroyMethod = "close")
         public AtomikosDataSourceBean setAtomikosDataSourceBean() {
             AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
             atomikosDataSourceBean.setUniqueResourceName("DataSource1");
             
             
             
             
             
             
         }
         
==========================
參考鏈結：<br>
http://www.byteslounge.com/tutorials/spring-jta-multiple-resource-transactions-in-tomcat-with-atomikos-example<br>
http://fabiomaffioletti.me/blog/2014/04/15/distributed-transactions-multiple-databases-spring-boot-spring-data-jpa-atomikos/<br>
http://sundoctor.iteye.com/blog/1928279#bc2357040<br>

