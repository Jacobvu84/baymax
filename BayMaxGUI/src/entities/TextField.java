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
public class TextField implements TestCase {

    private String text;
    private String target;
    private boolean clear;

    public TextField(String text, String target, boolean clear) {
        this.text = text;
        this.target = target;
        this.clear = clear;
    }

    public TextField() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public String toString() {
        String str = "";
        if (clear) {
            str = "Clear and type \"" + text + "\" into \"" + target + "\" field.";
        } else {
            str = "Type \"" + text + "\" into \"" + target + "\" field.";
        }
        return str;
    }
}
