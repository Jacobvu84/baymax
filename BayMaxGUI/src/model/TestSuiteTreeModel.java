/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.Scenario;
import entities.TestSuite;
import interfaces.TestCase;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Hyungin Choi
 */
public class TestSuiteTreeModel implements TreeModel {

    TestSuite root;

    public TestSuiteTreeModel(TestSuite root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof TestSuite) {
            return ((TestSuite) parent).getScenarioByIndex(index);
        }
        if (parent instanceof Scenario) {
            return ((Scenario) parent).getTestCaseByIndex(index);
        }
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof TestSuite) {
            return ((TestSuite) parent).childCount();
        }
        if (parent instanceof Scenario) {
            return ((Scenario) parent).childCount();
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        if ((node instanceof TestSuite) || (node instanceof Scenario)) {
            return false;
        }
        if (node instanceof TestSuite) {
            return ((TestSuite) node).childCount() == 0;
        }
        if (node instanceof Scenario) {
            return ((Scenario) node).childCount() == 0;
        }
        return true;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
//        MutableTreeNode aNode = (MutableTreeNode) path.getLastPathComponent();
//
//        aNode.setUserObject(newValue);
//        nodeChanged(aNode);
    }  

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (child == null || (parent instanceof TestCase)) {
            return -1;
        }
        ArrayList alist = null;
        if (parent instanceof TestSuite) {
            alist = ((TestSuite) parent).getAllScenarios();
        }
        if (parent instanceof Scenario) {
            alist = ((Scenario) parent).getAllTestCases();
        }
        int count = 0;
        for (Object object : alist) {
            if (object == child) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
