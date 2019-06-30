# SE-228
Project Repository for SE-228，「Web 开发技术」。

# Configure

* 执行 `dockerize ▸ build_and_compose.sh` 来构建 Docker 容器并启动。

# ~Configure [deprecated]~
数据库后端基于 MySQL。

* 执行 `db ▸ init_db.sql` 初始化数据库。如有需要，执行 `db ▸ add_example_data.sql` 灌入示例数据。

* 在 `server⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨resources ▸ ⁨jdbc.properties⁩` 和 `application.properties` 文件中配置 MySQL 数据库之账户及密码。

* 执行 `fresh_start.sh` 来编译并启动程序。

* 访问 `localhost:8080` 来查看前端网页。
