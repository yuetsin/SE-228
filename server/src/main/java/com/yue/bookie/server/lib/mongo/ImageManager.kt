package com.yue.bookie.server.lib.mongo
import com.mongodb.*
import org.bson.types.Binary
import java.io.FileInputStream
import java.util.*
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.conversions.Bson
import sun.plugin2.util.PojoUtil.toJson
import com.mongodb.client.MongoCursor
import com.mongodb.client.FindIterable
import com.mongodb.gridfs.GridFS
import org.json.JSONObject
import java.io.IOException
import java.net.UnknownHostException


object ImageManager {

    private lateinit var mongoClient: MongoClient

    fun getMongoDBUri(): String {

        // create file input stream object for the properties file
        val fis = FileInputStream("../../../resources/application.properties")
        // create Properties class object to access properties file
        val prop = Properties()
        // load the properties file
        prop.load(fis)
        // get the property of "url" using getProperty()
        val uri = prop.getProperty("mongouri")

        return uri
    }

    fun uploadImage(fileName: String, content: Binary): String? {
        val database = mongoClient.getDatabase("bookie_imgs")
        val covers = database.getCollection("covers")
//        covers.
        val newFileName = UUID.randomUUID().toString()
        return null
    }

    fun getImage(fileName: String): Binary? {
        val database = mongoClient.getDatabase("bookie_imgs")
        val covers = database.getCollection("covers")
        // Performing a read operation on the collection.
        return null
    }
}