package com.project.shishafan.drivers.main;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

import com.project.shishafan.drivers.AppDriver;
import com.project.shishafan.drivers.MainDriver;

public class DriverClass {

    public static void main(String[] args) {

        AppDriver driver = new MainDriver(args);
        driver.start();
    }
}
