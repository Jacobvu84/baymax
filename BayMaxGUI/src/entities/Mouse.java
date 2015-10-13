/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import interfaces.TestCase;

/**
 *
 * @author Hyungin Choi
 */
public class Mouse implements TestCase {

    private String target;

    public Mouse(String target) {
        this.target = target;
    }

    public Mouse() {
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String toString() {
        return "Click on \"" + target+"\"";
    }
}
