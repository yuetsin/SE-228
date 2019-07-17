//
//  DarkTabBarController.swift
//  Bookie
//
//  Created by 法好 on 2019/7/2.
//  Copyright © 2019 yuetsin. All rights reserved.
//

import UIKit

class DarkTabBarController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()

        overrideUserInterfaceStyle = .dark
        // Do any additional setup after loading the view.
    }
}



class LightTabBarController: UITabBarController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        overrideUserInterfaceStyle = .light
        // Do any additional setup after loading the view.
    }
}
