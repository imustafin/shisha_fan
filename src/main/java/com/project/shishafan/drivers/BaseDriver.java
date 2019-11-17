package com.project.shishafan.drivers;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

public abstract class BaseDriver implements AppDriver {

    private String[] args;

    public BaseDriver(String[] args) {
        this.args = args;
    }
}
