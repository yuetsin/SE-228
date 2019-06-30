package com.yue.bookie.server.lib.mongo

import com.mongodb.Mongo
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.gridfs.GridFS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.FileInputStream
import java.util.*


@Configuration
open class MongoDBConfig {

    @Bean
    open fun gridFS(): GridFS {
        return GridFS(mongo().getDB("bookie_imgs"))
    }

    @Bean
    open fun mongo(): Mongo {

        // create file input stream object for the properties file
        val fis = ClassPathResource(
                "application.properties").file.inputStream()
        // create Properties class object to access properties file
        val prop = Properties()
        // load the properties file
        prop.load(fis)
        // get the property of "url" using getProperty()
        val uri = prop.getProperty("spring.data.mongodb.uri")

        return MongoClient(MongoClientURI(uri))
    }
}