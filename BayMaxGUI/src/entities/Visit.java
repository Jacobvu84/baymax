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
public class Visit implements TestCase{

    private boolean isHomepage = true;
    private String url;
    private String linkText;
    private boolean isNewWindow;

    public Visit() {
    }

    public boolean isIsHomepage() {
        return isHomepage;
    }

    public void setIsHomepage(boolean isHomepage) {
        this.isHomepage = isHomepage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public boolean isIsNewWindow() {
        return isNewWindow;
    }

    public void setIsNewWindow(boolean isNewWindow) {
        this.isNewWindow = isNewWindow;
    }
    
    @Override
    public String toString(){
        String str="Visit ";
        
        if(linkText!=null&&!"".equals(linkText)){
            str=str+"link text \""+linkText.trim()+"\"";
        }
        if(isHomepage){
            str=str+"homepage";
        }
        if(url!=null&&!"".equals(url)){
            str=str+"link \""+url.trim()+"\"";
        }
        if(isNewWindow){
            str=str+" in new window";
        }
        
        return str.trim();
    }
}
