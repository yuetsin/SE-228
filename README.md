# BookieBookie!
![](https://img.shields.io/badge/project%20name-BookieBookie!-ff69b4.svg?style=flat-square)
![](https://img.shields.io/badge/kotlin-1.3-f18e33.svg?style=flat-square&logo=kotlin)
![](https://img.shields.io/badge/database-MySQL-4479a1.svg?style=flat-square&logo=mysql)
![](https://img.shields.io/badge/database-MongoDB-47a248.svg?style=flat-square&logo=mongodb)
![](https://img.shields.io/badge/docker-yes-1488c6.svg?style=flat-square&logo=docker)
![](https://img.shields.io/badge/swift-5.0-fa7343.svg?logo=swift&style=flat-square)
![](https://img.shields.io/badge/iOS-13.0-lightgrey.svg?logo=apple&style=flat-square)


Project Repository for SE-228，「Web 开发技术」。
# Configure

* 在 `server⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨resources ▸ ⁨application.properties` 文件中配置 `MySQL` 和 `MongoDB` 服务器 URI 和凭据；

* 清理本地的旧 `docker` 镜像；

* 执行 `dockerize ▸ docker-compose stop` 来停止已有的 Docker Compose 容器；

* 执行 `dockerize ▸ docker-compose rm` 来清理已有的 Docker Compose 容器；

* 执行 `dockerize ▸ build_and_compose.sh` 来构建 Docker 容器并启动。

# ~Configure [deprecated]~
~数据库后端基于 MySQL。~

* ~执行 `db ▸ init_db.sql` 初始化数据库。如有需要，执行 `db ▸ add_example_data.sql` 灌入示例数据。~

* ~在 `server⁩ ▸ ⁨src⁩ ▸ ⁨main⁩ ▸ ⁨resources ▸ ⁨jdbc.properties⁩` 和 `application.properties` 文件中配置 MySQL 数据库之账户及密码。~

* ~执行 `fresh_start.sh` 来编译并启动程序。~

* ~访问 `localhost:8080` 来查看前端网页。~

# iOS Client Screenshots

## 书籍管理

<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_AddNewBook.PNG?raw=true"/>
  <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_BookManager_2.PNG?raw=true"/>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_BookManager_3.PNG?raw=true"/>
</div>

## 新用户注册
<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Register_Success.PNG?raw=true"/>
</div>


## 用户管理
<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_ActivateUser.PNG?raw=true"/>
  <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_UserManager.PNG?raw=true"/>
</div>

## 订单管理

<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_OrderManager.PNG?raw=true"/>
  <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Admin_OrderStats.PNG?raw=true"/>
</div>

## 搜索书籍
<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/User_Search.PNG?raw=true"/>
</div>

## 书籍详情

<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/Book_Detail.PNG?raw=true"/>
</div>

## 购物车、购买

<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/User_Cart.PNG?raw=true"/>
  <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/User_Buy.PNG?raw=true"/>
</div>

## 查看历史订单

<div align=center>
    <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/User_Purchased.PNG?raw=true"/>
  <img width="450" height="800" src="https://github.com/yuetsin/SE-228/blob/master/screenshots/User_PurchasedStats.PNG?raw=true"/>
</div>

