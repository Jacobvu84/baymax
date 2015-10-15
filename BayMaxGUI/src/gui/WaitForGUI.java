/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.WaitFor;
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
public class WaitForGUI extends javax.swing.JPanel {

    /**
     * Creates new form AssertGUI
     */
    public WaitForGUI(WaitFor waitFor) {
        initComponents();
        this.waitFor = waitFor;
        prepareGUI();
    }

    private void prepareGUI() {
        if (waitFor.isBySeconds()) {
            rdInSeconds.setSelected(true);
            txtSeconds.setText(Integer.toString(waitFor.getSeconds()));
            disableForElementSection();
        } else {
            disableInSecondsSection();
            rdForElement.setSelected(true);
            if (waitFor.getElement() != null && !"".equals(waitFor.getElement())) {
                txtElement.setText(waitFor.getElement());
            }
            if (waitFor.isHasAttribute()) {
                cbElementAttribute.setSelected(true);
                if (waitFor.isAttributeComparator()) {
                    cbbAttributeComparators.setSelectedIndex(attributeComparatorIndexOfHas);
                } else {
                    cbbAttributeComparators.setSelectedIndex(attributeComparatorIndexOfNotHas);
                }
                txtAttribute.setText(waitFor.getAttribute());
                if (waitFor.isAttributeHasValue()) {
                    cbAttributeValue.setSelected(true);
                    cbbAttributeValueComparators.setSelectedItem(waitFor.getAttributeValueComparator());
                    txtAttributeValue.setText(waitFor.getAttributeValue());
                } else {
                    cbAttributeValue.setSelected(false);
                }
            } else {
                cbElementAttribute.setSelected(false);
            }
            if (!"-".equals(waitFor.getElementComparator())) {
                cbbElementComparators.setSelectedItem(waitFor.getElementComparator());
                for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
                    JRadioButton rd = (JRadioButton) radios.nextElement();
                    if (waitFor.getElementState().equals(rd.getText())) {
                        rd.setSelected(true);
                        if ("text".equals(rd.getText())) {
                            txtText.setText(waitFor.getText());
                        } else if ("image".equals(rd.getText())) {
                            cbbImageComparators.setSelectedItem(waitFor.getImageComparator());
                            txtFile.setText(waitFor.getImageLocation());
                        }
                        break;
                    }
                }

            } else {
                cbbElementComparators.setSelectedItem(comparatorIndexOfNone);
            }
        }

        lblWaitFor.setText(waitFor.toString());
    }

    private void disableImageSection() {
        cbbImageComparators.setSelectedIndex(comparatorIndexOfNone);
        cbbImageComparators.setEnabled(false);
        txtFile.setEnabled(false);
        txtFile.setText("");
    }

    private void enableImageSection() {
        cbbImageComparators.setEnabled(true);
        txtFile.setEnabled(true);
    }

    private void disableTextSection() {
        txtText.setEnabled(false);
        txtText.setText("");
    }

    private void enableTextSection() {
        txtText.setEnabled(true);
    }

    private void enableElementStateSection() {
        for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
            JRadioButton rd = (JRadioButton) radios.nextElement();
            rd.setEnabled(true);
        }
    }

    private void disableElementStateSection() {
        bgElementStates.clearSelection();
        for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
            JRadioButton rd = (JRadioButton) radios.nextElement();
            rd.setEnabled(false);
        }
    }

    private void enableAttributeValueSection() {
        cbbAttributeValueComparators.setEnabled(true);
        txtAttributeValue.setEnabled(true);
    }

    private void disableAttributeValueSection() {
        cbbAttributeValueComparators.setSelectedIndex(comparatorIndexOfNone);
        cbbAttributeValueComparators.setEnabled(false);
        txtAttributeValue.setEnabled(false);
        txtAttributeValue.setText("");
    }

    private void enableAttributeSection() {
        cbbAttributeComparators.setEnabled(true);
        txtAttribute.setEnabled(true);
        cbAttributeValue.setEnabled(true);
    }

    private void disableAttributeSection() {
        cbbAttributeComparators.setSelectedIndex(comparatorIndexOfNone);
        cbbAttributeComparators.setEnabled(false);
        txtAttribute.setEnabled(false);
        cbAttributeValue.setSelected(false);
        cbAttributeValue.setEnabled(false);
    }

    private void enableForElementSection() {
        txtElement.setEnabled(true);
        cbElementAttribute.setEnabled(true);
        cbbElementComparators.setEnabled(true);
    }

    private void disableForElementSection() {
        txtElement.setEnabled(false);
        cbElementAttribute.setSelected(false);
        cbElementAttribute.setEnabled(false);
        cbbElementComparators.setSelectedIndex(comparatorIndexOfNone);
        cbbElementComparators.setEnabled(false);
    }

    private void enableInSecondsSection() {
        txtSeconds.setEnabled(true);
    }

    private void disableInSecondsSection() {
        txtSeconds.setEnabled(false);
    }

    private void save() throws InstantiationException {
        if (validateForm()) {
            if (rdInSeconds.isSelected()) {
                waitFor.setBySeconds(true);
            } else {
                waitFor.setBySeconds(false);
            }
            waitFor.setSeconds(Integer.parseInt(txtSeconds.getText()));
            waitFor.setElement(txtElement.getText());
            if (cbElementAttribute.isSelected()) {
                waitFor.setHasAttribute(true);
                waitFor.setAttribute(txtAttribute.getText());
                if (cbbAttributeComparators.getSelectedIndex() == 1) {
                    waitFor.setAttributeComparator(true);
                } else {
                    waitFor.setAttributeComparator(false);
                }
                if (cbAttributeValue.isSelected()) {
                    waitFor.setAttributeHasValue(true);
                    waitFor.setAttributeValueComparator(cbbAttributeValueComparators.getSelectedItem().toString());
                    waitFor.setAttributeValue(txtAttributeValue.getText());
                } else {
                    waitFor.setAttributeHasValue(false);
                    waitFor.setAttributeValue("");
                }
            } else {
                waitFor.setAttribute("");
                waitFor.setHasAttribute(false);
                waitFor.setAttributeHasValue(false);
                waitFor.setAttributeValue("");
            }

            waitFor.setElementComparator(cbbElementComparators.getSelectedItem().toString());
            if (cbbElementComparators.getSelectedIndex() != comparatorIndexOfNone) {
                for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
                    JRadioButton rd = (JRadioButton) radios.nextElement();
                    if (rd.isSelected()) {
                        waitFor.setElementState(rd.getText());
                        if (rdImage.isSelected()) {
                            waitFor.setImageComparator(cbbImageComparators.getSelectedItem().toString());
                            waitFor.setImageLocation(txtFile.getText());
                        } else if (rdText.isSelected()) {
                            waitFor.setText(txtText.getText());
                        }
                        break;
                    }
                }

            }
        }
        prepareGUI();
    }

    private boolean validateForm() {
        if (rdInSeconds.isSelected()) {
            if ("".equals(txtSeconds.getText())) {
                showErrorMessage("Seconds cannot be blank!");
                return false;
            } else {
                try {
                    if (Integer.parseInt(txtSeconds.getText()) < 0) {
                        showErrorMessage("Seconds should be a positive number!");
                    }
                } catch (Exception e) {
                    showErrorMessage("Seconds should be a number!");
                    return false;
                }
            }
        } else {
            if ("".equals(txtElement.getText())) {
                showErrorMessage("Element should not be blank!");
                return false;
            }
            if (cbElementAttribute.isSelected()) {
                if (cbbAttributeComparators.getSelectedIndex() == comparatorIndexOfNone) {
                    showErrorMessage("Comparator for attribute of element should not be blank!");
                    return false;
                } else {
                    if ("".equals(txtAttribute.getText())) {
                        showErrorMessage("Attribute should not be blank!");
                        return false;
                    }
                    if (cbAttributeValue.isSelected()) {
                        if (cbbAttributeValueComparators.getSelectedIndex() == comparatorIndexOfNone) {
                            showErrorMessage("Comparator for value of attribute should not be blank!");
                            return false;
                        } else {
                            if ("".equals(txtAttributeValue.getText())) {
                                showErrorMessage("Attribute value should not be blank!");
                                return false;
                            }
                        }
                    }
                }
            }
            if (cbbElementComparators.getSelectedIndex() != comparatorIndexOfNone) {
                if (rdText.isSelected()) {
                    if (cbbElementComparators.getSelectedIndex() == 1 || cbbElementComparators.getSelectedIndex() == 2) {
                        showErrorMessage("The element comparator \"is\" or \"is not\" should not be used only when asserting text of element!");
                        return false;
                    }
                    if ("".equals(txtText.getText())) {
                        showErrorMessage("Text should not be blank!");
                        return false;
                    }
                } else if (rdImage.isSelected()) {
                    if (cbbElementComparators.getSelectedIndex() != 3 && cbbElementComparators.getSelectedIndex() != 4) {
                        showErrorMessage("The element comparator should be \"has\" or \"does not have\" when asserting image!");
                        return false;
                    }
                    if ("".equals(txtFile.getText())) {
                        showErrorMessage("File location should not be blank!");
                        return false;
                    }
                } else {
                    for (Enumeration<AbstractButton> radios = bgElementStates.getElements(); radios.hasMoreElements();) {
                        JRadioButton rd = (JRadioButton) radios.nextElement();
                        if (rd.isSelected()) {
                            if (cbbElementComparators.getSelectedIndex() != 1 && cbbElementComparators.getSelectedIndex() != 2) {
                                showErrorMessage("The element comparator \"is\" or \"is not\" should be used when asserting state of element!");
                                return false;
                            }
                            return true;
                        }
                    }
                    showErrorMessage("One of expectation for element should be selected!");
                    return false;
                }
            }
        }
        return true;
    }

    private void disableSeconds() {
        txtSeconds.setEnabled(false);
        txtSeconds.setText("");
    }

    private void enableSeconds() {
        txtSeconds.setEnabled(true);
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

        bgElementStates = new javax.swing.ButtonGroup();
        fcImage = new javax.swing.JFileChooser();
        bgFor = new javax.swing.ButtonGroup();
        lblWaitFor = new javax.swing.JLabel();
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
        rdImage = new javax.swing.JRadioButton();
        cbbImageComparators = new javax.swing.JComboBox();
        txtFile = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        rdInSeconds = new javax.swing.JRadioButton();
        txtSeconds = new javax.swing.JTextField();
        lblSeconds = new javax.swing.JLabel();
        rdForElement = new javax.swing.JRadioButton();

        lblWaitFor.setText("Wait");

        cbbElementComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "is", "is not", "has", "does not have", "includes", "does not include" }));
        cbbElementComparators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbElementComparatorsItemStateChanged(evt);
            }
        });
        cbbElementComparators.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbElementComparatorsPropertyChange(evt);
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
        cbbAttributeValueComparators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAttributeValueComparatorsItemStateChanged(evt);
            }
        });
        cbbAttributeValueComparators.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbAttributeValueComparatorsPropertyChange(evt);
            }
        });

        cbElementAttribute.setText("which");
        cbElementAttribute.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbElementAttributeStateChanged(evt);
            }
        });

        cbbAttributeComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "has", "does not have" }));
        cbbAttributeComparators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAttributeComparatorsItemStateChanged(evt);
            }
        });
        cbbAttributeComparators.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbAttributeComparatorsPropertyChange(evt);
            }
        });

        lblAttribute.setText("attribute");

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        bgElementStates.add(rdImage);
        rdImage.setText("image");
        rdImage.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdImageStateChanged(evt);
            }
        });

        cbbImageComparators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "similar to", "not similar to" }));
        cbbImageComparators.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbImageComparatorsItemStateChanged(evt);
            }
        });
        cbbImageComparators.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbbImageComparatorsPropertyChange(evt);
            }
        });

        txtFile.setToolTipText("sadfasdfasdfasd");
        txtFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtFileMouseEntered(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        bgFor.add(rdInSeconds);
        rdInSeconds.setText("in ");
        rdInSeconds.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdInSecondsStateChanged(evt);
            }
        });

        lblSeconds.setText("seconds");

        bgFor.add(rdForElement);
        rdForElement.setText("for element");
        rdForElement.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdForElementStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbElementComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(38, 38, 38)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btnCancel)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnSave))
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
                                                        .addComponent(txtText, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rdImage)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(cbbImageComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(rdInSeconds)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(rdForElement)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbbAttributeComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblAttribute)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(lblSeconds)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblWaitFor)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(lblWaitFor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdInSeconds)
                    .addComponent(txtSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSeconds))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtElement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdForElement))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdImage)
                    .addComponent(cbbImageComparators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            // TODO add your handling code here:
            save();
        } catch (InstantiationException ex) {
            Logger.getLogger(WaitForGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbElementAttributeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbElementAttributeStateChanged
        // TODO add your handling code here:
        if (cbElementAttribute.isSelected()) {
            enableAttributeSection();
        } else {
            disableAttributeSection();
        }
    }//GEN-LAST:event_cbElementAttributeStateChanged

    private void cbAttributeValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbAttributeValueStateChanged
        // TODO add your handling code here:
        if (cbAttributeValue.isSelected()) {
            enableAttributeValueSection();
        } else {
            disableAttributeValueSection();
        }
    }//GEN-LAST:event_cbAttributeValueStateChanged

    private void rdTextStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdTextStateChanged
        // TODO add your handling code here:
        if (rdText.isSelected()) {
            enableTextSection();
        } else {
            disableTextSection();
        }
    }//GEN-LAST:event_rdTextStateChanged

    private void cbbElementComparatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbElementComparatorsItemStateChanged
        // TODO add your handling code here:
        if (cbbElementComparators.getSelectedIndex() != comparatorIndexOfNone) {
            enableElementStateSection();
        } else {
            disableElementStateSection();
        }
    }//GEN-LAST:event_cbbElementComparatorsItemStateChanged

    private void rdImageStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdImageStateChanged
        // TODO add your handling code here:
        if (rdImage.isSelected()) {
            enableImageSection();
        } else {
            disableImageSection();
        }
    }//GEN-LAST:event_rdImageStateChanged

    private void txtFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFileMouseClicked
        // TODO add your handling code here:
        if (rdImage.isSelected()) {
            fcImage.showOpenDialog(null);
            txtFile.setText(fcImage.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_txtFileMouseClicked

    private void txtFileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFileMouseEntered
        // TODO add your handling code here:
        txtFile.setToolTipText(txtFile.getText());
    }//GEN-LAST:event_txtFileMouseEntered

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        prepareGUI();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void rdInSecondsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdInSecondsStateChanged
        // TODO add your handling code here:
        if (rdInSeconds.isSelected()) {
            enableSeconds();
        } else {
            disableSeconds();
        }
    }//GEN-LAST:event_rdInSecondsStateChanged

    private void rdForElementStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdForElementStateChanged
        // TODO add your handling code here:
        if (rdForElement.isSelected()) {
            enableForElementSection();
        } else {
            disableForElementSection();
        }
    }//GEN-LAST:event_rdForElementStateChanged

    private void cbbImageComparatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbImageComparatorsItemStateChanged
        // TODO add your handling code here:
        if (cbbImageComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtFile.setEnabled(true);
        } else {
            txtFile.setEnabled(false);
        }
    }//GEN-LAST:event_cbbImageComparatorsItemStateChanged

    private void cbbAttributeValueComparatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAttributeValueComparatorsItemStateChanged
        // TODO add your handling code here:
        if (cbbAttributeValueComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtAttributeValue.setEnabled(true);
        } else {
            txtAttributeValue.setEnabled(false);
        }
    }//GEN-LAST:event_cbbAttributeValueComparatorsItemStateChanged

    private void cbbAttributeComparatorsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAttributeComparatorsItemStateChanged
        // TODO add your handling code here:
        if (cbbAttributeComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtAttribute.setEnabled(true);
        } else {
            txtAttribute.setEnabled(false);
        }
    }//GEN-LAST:event_cbbAttributeComparatorsItemStateChanged

    private void cbbElementComparatorsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbElementComparatorsPropertyChange
        // TODO add your handling code here:
        if (cbbElementComparators.getSelectedIndex() != comparatorIndexOfNone) {
            enableElementStateSection();
        } else {
            disableElementStateSection();
        }
    }//GEN-LAST:event_cbbElementComparatorsPropertyChange

    private void cbbImageComparatorsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbImageComparatorsPropertyChange
        // TODO add your handling code here:
        if (cbbImageComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtFile.setEnabled(true);
        } else {
            txtFile.setEnabled(false);
        }
    }//GEN-LAST:event_cbbImageComparatorsPropertyChange

    private void cbbAttributeValueComparatorsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbAttributeValueComparatorsPropertyChange
        // TODO add your handling code here:
        if (cbbAttributeValueComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtAttributeValue.setEnabled(true);
        } else {
            txtAttributeValue.setEnabled(false);
        }
    }//GEN-LAST:event_cbbAttributeValueComparatorsPropertyChange

    private void cbbAttributeComparatorsPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbbAttributeComparatorsPropertyChange
        // TODO add your handling code here:
        if (cbbAttributeComparators.getSelectedIndex() != comparatorIndexOfNone) {
            txtAttribute.setEnabled(true);
        } else {
            txtAttribute.setEnabled(false);
        }
    }//GEN-LAST:event_cbbAttributeComparatorsPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgElementStates;
    private javax.swing.ButtonGroup bgFor;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbAttributeValue;
    private javax.swing.JCheckBox cbElementAttribute;
    private javax.swing.JComboBox cbbAttributeComparators;
    private javax.swing.JComboBox cbbAttributeValueComparators;
    private javax.swing.JComboBox cbbElementComparators;
    private javax.swing.JComboBox cbbImageComparators;
    private javax.swing.JFileChooser fcImage;
    private javax.swing.JLabel lblAttribute;
    private javax.swing.JLabel lblSeconds;
    private javax.swing.JLabel lblWaitFor;
    private javax.swing.JRadioButton rdAbsent;
    private javax.swing.JRadioButton rdDisabled;
    private javax.swing.JRadioButton rdEnabled;
    private javax.swing.JRadioButton rdForElement;
    private javax.swing.JRadioButton rdHidden;
    private javax.swing.JRadioButton rdImage;
    private javax.swing.JRadioButton rdInSeconds;
    private javax.swing.JRadioButton rdPresent;
    private javax.swing.JRadioButton rdText;
    private javax.swing.JRadioButton rdVisible;
    private javax.swing.JTextField txtAttribute;
    private javax.swing.JTextField txtAttributeValue;
    private javax.swing.JTextField txtElement;
    private javax.swing.JTextField txtFile;
    private javax.swing.JTextField txtSeconds;
    private javax.swing.JTextField txtText;
    // End of variables declaration//GEN-END:variables
    private WaitFor waitFor;
    private final int comparatorIndexOfNone = 0;
    private final int attributeComparatorIndexOfHas = 1;
    private final int attributeComparatorIndexOfNotHas = 2;
    private final int imageComparatorIndexOfSimilar = 1;
    private final int imageComparatorIndexOfNotSimilar = 2;
}
