//
//  Book.swift
//  Bookie
//
//  Created by 法好 on 2019/7/1.
//  Copyright © 2019 yuetsin. All rights reserved.
//

import UIKit
import Alamofire

struct Book {
    var title: String
    var isbn: String
    var author: String
    var description: String
    var coverUrl: String
    var storage: Int
    var price: Double
    var couponPrice: Double
    
    func getImage(handler: @escaping (UIImage) -> ()) {
        let params: Parameters = [
            "fileId": coverUrl
        ]
        Alamofire.request(BookieUri.imageCoverGetUri, method: .get, parameters: params)
            .responseData(completionHandler: { responseData in
                if responseData.data == nil {
                    return
                }
                let image = UIImage(data: responseData.data!)
                if image != nil {
                    handler(image!)
                }
            })
    }
}
