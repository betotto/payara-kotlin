package config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

val ds = createDataSource()


private fun createDataSource(): HikariDataSource {
    val config = HikariConfig()

    config.jdbcUrl = "jdbc:mysql://localhost:3306/questions?serverTimezone=UTC&useSSL=false"
    config.username = "betotto"
    config.password = "123456"
    config.driverClassName = "com.mysql.cj.jdbc.Driver"
    config.isAutoCommit = false
    config.maximumPoolSize = 5
    config.poolName = "questions"
    return HikariDataSource(config)
}

fun getDataSource(): DataSource = ds