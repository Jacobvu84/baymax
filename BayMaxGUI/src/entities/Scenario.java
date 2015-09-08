/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import interfaces.TestCase;
import java.util.ArrayList;

/**
 *
 * @author Hyungin Choi
 */
public class Scenario {
    private ArrayList<TestCase> cases;
    private String title;

    public Scenario(String title) {
        cases = new ArrayList<TestCase>();
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }   
    
    public void addNewCase(TestCase testCase){
        cases.add(testCase);
    }
    
    public ArrayList<TestCase> getAllTestCases(){
        return cases;
    }
    
    public TestCase getTestCaseByTitle(String title){
        for (TestCase testCase : cases) {
            if(title.equals(testCase.toString())){
                return testCase;
            }
        }
        return null;
    }
    
    public TestCase getTestCaseByIndex(int index){
        return cases.get(index);
    }
    public int childCount(){
        return cases.size();
    }
    
    public String toString(){
        return title;
    }
}
