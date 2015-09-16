/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.AssertElement;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Hyungin Choi
 */
public class AssertGUI extends javax.swing.JPanel {

    /**
     * Creates new form AssertGUI
     */
    public AssertGUI(AssertElement assertElement) {
        initComponents();
        this.assertElement = assertElement;
        prepareGUI();
        this.lblAssert.setText(assertElement.toString());
    }

    private void prepareGUI() {
        if (assertElement.getElement() != null && !"".equals(assertElement.getElement())) {
            txtElement.setText(assertElement.getElement());
        }
        if (assertElement.isHasAttribute()) {
            cbElementAttribute.setSelected(true);
            if (assertElement.isAttributeComparator()) {
                cbbAttributeComparators.setSelectedIndex(attributeComparatorIndexOfHas);
            } else {
                cbbAttributeComparators.setSelectedIndex(attributeComparatorIndexOfNotHas);
            }
            txtAttribute.setText(assertElement.getAttribute());
            if (assertElement.isAttributeHasValue()) {
                cbAttributeValue.setSelected(true);
                cbbAttributeValueComparators.setSelectedItem(assertElement.getAttributeValueComparator());
                txtAttributeValue.setText(assertElement.getAttributeValue());
            } else {
                cbAttributeValue.setSelected(false);
            }
        } else {
            cbElementAttribute.setSelected(false);
        }
        if (!"-".equals(assertElement.getElementComparator())) {
            cbbElementComparators.setSelectedItem(assertElement.getElementComparator());
            if (assertElement.getText() != null && assertElement.getText() != "") {
                rdText.setSelected(true);
                txtText.setText(assertElement.getText());
            } else {
                for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
                    JRadioButton rd = (JRadioButton) radios.nextElement();
                    if (assertElement.getElementState().equals(rd.getText())) {
                        rd.setSelected(true);
                    }
                }
            }
        } else {
            cbbElementComparators.setSelectedItem("-");
        }
    }

    private void setEnableAttributeSection() {
        cbbAttributeComparators.setEnabled(true);
        lblAttribute.setEnabled(true);
        cbAttributeValue.setEnabled(true);
        txtAttribute.setEnabled(true);
    }

    private void setDisabledAttributeSection() {
        cbbAttributeComparators.setEnabled(false);
        lblAttribute.setEnabled(false);
        cbAttributeValue.setSelected(false);
        cbAttributeValue.setEnabled(false);
        txtAttribute.setText("");
        txtAttribute.setEnabled(false);
    }

    private void setEnabledAttributeValueSection() {
        cbbAttributeValueComparators.setEnabled(true);
        txtAttributeValue.setEnabled(true);
    }

    private void setDisabledAttributeValueSection() {
        cbbAttributeValueComparators.setEnabled(false);
        txtAttributeValue.setEnabled(false);
    }

    private void setEnabledElementStateSection() {
        for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
            JRadioButton rd = (JRadioButton) radios.nextElement();
            rd.setEnabled(true);
        }
    }

    private void setDisabledElementStateSection() {
        bgElementStates.clearSelection();
        for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
            JRadioButton rd = (JRadioButton) radios.nextElement();
            rd.setEnabled(false);
        }
    }

    private void save() throws InstantiationException {
        assertElement.setElement(txtElement.getText());
        if (cbElementAttribute.isSelected()) {
            assertElement.setHasAttribute(true);
            assertElement.setAttribute(txtAttribute.getText());
            if (cbbAttributeComparators.getSelectedIndex() == 0) {
                assertElement.setAttributeComparator(true);
            } else {
                assertElement.setAttributeComparator(false);
            }
            if (cbAttributeValue.isSelected()) {
                assertElement.setAttributeHasValue(true);
                assertElement.setAttributeValueComparator(cbbAttributeValueComparators.getSelectedItem().toString());
                assertElement.setAttributeValue(txtAttributeValue.getText());
            } else {
                assertElement.setAttributeHasValue(false);
                assertElement.setAttributeValue("");
            }
        } else {
            assertElement.setAttribute("");
            assertElement.setHasAttribute(false);
            assertElement.setAttributeHasValue(false);
            assertElement.setAttributeValue("");
        }

        if (cbbElementComparators.getSelectedIndex() != 0) {
            assertElement.setElementComparator(cbbElementComparators.getSelectedItem().toString());
            if (rdText.isSelected()) {
                if (cbbElementComparators.getSelectedIndex() == 1 || cbbElementComparators.getSelectedIndex() == 2) {
                    showErrorMessage("Should use \"is\" or \"is not\" only when asserting element state!");
                } else {
                    assertElement.setElementState("text");
                    assertElement.setText(txtText.getText());
                }
            } else {
                if (cbbElementComparators.getSelectedIndex() != 1 && cbbElementComparators.getSelectedIndex() != 2) {
                    showErrorMessage("Should use \"is\" or \"is not\" only when asserting element state!");
                } else {
                    for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
                        JRadioButton rd = (JRadioButton) radios.nextElement();
                        if (rd.isSelected()) {
                            assertElement.setElementState(rd.getText());
                            break;
                        }
                    }
                }
            }
        }

    }

    private void showErrorMessage(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        bgElementStates = new javax.swing.ButtonGroup();
        lblAssert = new javax.swing.JLabel();
        lblElement = new javax.swing.JLabel();
        txtElement = new javax.swing.JTextField();
        cbbElementComparators = new javax.swing.JComboBox();
        rdText = new javax.swing.JRadioButton();
        txtText = new javax.swing.JTextField();
        rdDisabled = new javax.swing.JRadioButton();
        rdEnabled = new javax.swing.JRadioButton();
        rdPresent = new javax.swing.JRadioButton();
        rdAbsent = new javax.swing.JRadioButton();
        rdVisible = new javax.swing.JRadioButton();
        rdHidden = new javax.swing.JRadioButton();
        txtAttribute = new javax.swing.JTextField();
        cbAttributeValue = new javax.swing.JCheckBox();
        cbbAttributeValueComparators = new javax.swing.JComboBox();
        txtAttributeValue = new javax.swing.JTextField();
        cbElementAttribute = new javax.swing.JCheckBox();
        cbbAttributeComparators = new javax.swing.JComboBox();
        lblAttribute = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        lblAssert.setText("Assert");

        lblElement.setText("Element:");

        cbbElementComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "is", "is not", "has", "does not have", "includes", "does not include" }));
        cbbElementComparators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbElementComparatorsItemStateChanged(evt);
            }
        });

        bgElementStates.add(rdText);
        rdText.setText("text");
        rdText.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdTextStateChanged(evt);
            }
        });

        bgElementStates.add(rdDisabled);
        rdDisabled.setText("disabled");

        bgElementStates.add(rdEnabled);
        rdEnabled.setText("enabled");

        bgElementStates.add(rdPresent);
        rdPresent.setText("present");

        bgElementStates.add(rdAbsent);
        rdAbsent.setText("absent");

        bgElementStates.add(rdVisible);
        rdVisible.setText("visible");

        bgElementStates.add(rdHidden);
        rdHidden.setText("hidden");

        cbAttributeValue.setText("with the value that");
        cbAttributeValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbAttributeValueStateChanged(evt);
            }
        });

        cbbAttributeValueComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "is", "is not", "includes", "does not include" }));

        cbElementAttribute.setText("which");
        cbElementAttribute.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbElementAttributeStateChanged(evt);
            }
        });

        cbbAttributeComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "has", "does not have" }));

        lblAttribute.setText("attribute");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblElement)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtElement, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(cbAttributeValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbAttributeValueComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAttributeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbElementAttribute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbAttributeComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAttribute)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblAssert))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbElementComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdDisabled)
                                            .addComponent(rdPresent)
                                            .addComponent(rdVisible))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rdHidden)
                                            .addComponent(rdAbsent)
                                            .addComponent(rdEnabled)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdText)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblAssert)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblElement)
                    .addComponent(txtElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbElementAttribute)
                    .addComponent(cbbAttributeComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAttribute))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAttributeValue)
                    .addComponent(cbbAttributeValueComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAttributeValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cbbElementComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdDisabled)
                    .addComponent(rdEnabled))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdPresent)
                    .addComponent(rdAbsent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdVisible)
                    .addComponent(rdHidden))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdText)
                    .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSave)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            // TODO add your handling code here:
            save();
        } catch (InstantiationException ex) {
            Logger.getLogger(AssertGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbElementAttributeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbElementAttributeStateChanged
        // TODO add your handling code here:
        if (cbElementAttribute.isSelected()) {
            setEnableAttributeSection();
        } else {
            setDisabledAttributeSection();
        }
    }//GEN-LAST:event_cbElementAttributeStateChanged

    private void cbAttributeValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbAttributeValueStateChanged
        // TODO add your handling code here:
        if (cbAttributeValue.isSelected()) {
            setEnabledAttributeValueSection();
        } else {
            setDisabledAttributeValueSection();
        }
    }//GEN-LAST:event_cbAttributeValueStateChanged

    private void rdTextStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdTextStateChanged
        // TODO add your handling code here:
        if (rdText.isSelected()) {
            txtText.setEnabled(true);            
        } else {
            txtText.setEnabled(false);
            txtText.setText("");           
        }
    }//GEN-LAST:event_rdTextStateChanged

    private void cbbElementComparatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbElementComparatorsItemStateChanged
        // TODO add your handling code here:
        if ("-".equals(cbbElementComparators.getSelectedItem().toString())) {
            setDisabledElementStateSection();
        } else {
            setEnabledElementStateSection();
        }
    }//GEN-LAST:event_cbbElementComparatorsItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgElementStates;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbAttributeValue;
    private javax.swing.JCheckBox cbElementAttribute;
    private javax.swing.JComboBox cbbAttributeComparators;
    private javax.swing.JComboBox cbbAttributeValueComparators;
    private javax.swing.JComboBox cbbElementComparators;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel lblAssert;
    private javax.swing.JLabel lblAttribute;
    private javax.swing.JLabel lblElement;
    private javax.swing.JRadioButton rdAbsent;
    private javax.swing.JRadioButton rdDisabled;
    private javax.swing.JRadioButton rdEnabled;
    private javax.swing.JRadioButton rdHidden;
    private javax.swing.JRadioButton rdPresent;
    private javax.swing.JRadioButton rdText;
    private javax.swing.JRadioButton rdVisible;
    private javax.swing.JTextField txtAttribute;
    private javax.swing.JTextField txtAttributeValue;
    private javax.swing.JTextField txtElement;
    private javax.swing.JTextField txtText;
    // End of variables declaration//GEN-END:variables
    private AssertElement assertElement;
    private final int attributeComparatorIndexOfNone = 0;
    private final int attributeComparatorIndexOfHas = 1;
    private final int attributeComparatorIndexOfNotHas = 2;
}
