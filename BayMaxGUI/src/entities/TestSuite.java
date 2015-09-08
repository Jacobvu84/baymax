/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author Hyungin Choi
 */
public class TestSuite {
    private String title;
    private ArrayList<Scenario> scenarios;

    public TestSuite(String title) {
        scenarios = new ArrayList<Scenario>();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void addScenario(Scenario scenario){
        scenarios.add(scenario);
    }
    
    public ArrayList<Scenario> getAllScenarios(){
        return scenarios;
    }
    
    public Scenario getScenarioByTitle(String title){
        for (Scenario scenario : scenarios) {
            if(title.equals(scenario.getTitle())){
                return scenario;
            }
        }
        return null;
    }
    
    public Scenario getScenarioByIndex(int index){
        return scenarios.get(index);
    }
    
    public int childCount(){
        return scenarios.size();
    }
    
    public String toString(){
        return title;
    }
}
