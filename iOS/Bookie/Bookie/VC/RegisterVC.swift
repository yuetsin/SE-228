//
//  RegisterVC.swift
//  Bookie
//
//  Created by 法好 on 2019/7/1.
//  Copyright © 2019 yuetsin. All rights reserved.
//

import UIKit

class RegisterVC: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        onFieldEdited(userNameField)
    }
    
    @IBOutlet weak var userNameField: UITextField!
    @IBOutlet weak var eMailField: UITextField!
    @IBOutlet weak var passWordField: UITextField!
    @IBOutlet weak var confirmPassWordField: UITextField!
    
    @IBOutlet weak var resetButton: UIButton!
    @IBOutlet weak var registerButton: UIButton!
    
    
    @IBAction func onFieldEdited(_ sender: UITextField) {
        if userNameField.text == "" && eMailField.text == "" && passWordField.text == "" && confirmPassWordField.text == "" {
            resetButton.isEnabled = false
        } else {
            resetButton.isEnabled = true
        }
        
        if userNameField.text == "" || eMailField.text == "" || passWordField.text == "" || confirmPassWordField.text == "" || passWordField.text != confirmPassWordField.text {
            resetButton.isEnabled = false
        } else {
            resetButton.isEnabled = true
        }
    }
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
