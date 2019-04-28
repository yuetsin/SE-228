# SE-228
Project Repository for SE-228，「Web 开发技术」。

# Configure
数据库后端基于 MySQL。

* 执行 `db ▸ init_db.sql` 初始化数据库。如有需要，执行 `db ▸ add_example_data.sql` 灌入示例数据。

* 在 `server⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨resources ▸ ⁨jdbc.properties⁩` 和 `application.properties` 文件中配置 MySQL 数据库之账户及密码。

* 执行 `server⁩ ▸ ⁨mvnw⁩` 或 `server⁩ ▸ ⁨mvnw⁩.cmd` 来开启后端服务器。

* 访问 `localhost:8080` 来查看前端网页。
