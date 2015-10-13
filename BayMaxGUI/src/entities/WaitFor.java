/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import interfaces.TestCase;
import java.util.Arrays;

/**
 *
 * @author Hyungin Choi
 */
public class WaitFor implements TestCase {

    private String element;
    private boolean hasAttribute;
    private boolean attributeComparator;
    private String attribute;
    private boolean attributeHasValue;
    private String attributeValueComparator;
    private String attributeValue;
    private String elementComparator;
    private String text;
    private String elementState;
    private String imageComparator;
    private String imageLocation;
    private int seconds;
    private boolean bySeconds;

    private String[] elementStates = {"disabled", "enabled", "present", "absent", "visible", "hidden", "text", "image"};
    private String[] attributeComparatorsArray = {"-", "has", "does not have"};
    private String[] imageComparatorsArray = {"-", "similar to", "not similar to"};
    private String[] attributeValueComparatorsArray = {"-", "is", "is not", "includes", "does not includes"};
    private String[] elementComparatorsArray = {"-", "is", "is not", "has", "does not have", "includes", "does not include"};

    public WaitFor(String element, boolean hasAttribute, boolean attributeComparator, String attribute, boolean attributeHasValue, String attributeValueComparators, String attributeValue, String elementComparators, String text, String elementState, String imageComparator, String imageLocation)
            throws InstantiationException {
        this.element = element;
        this.hasAttribute = hasAttribute;
        this.attributeComparator = attributeComparator;
        this.attribute = attribute;
        this.attributeHasValue = attributeHasValue;
        if (Arrays.asList(attributeValueComparatorsArray).contains(attributeValueComparators)) {
            this.attributeValueComparator = attributeValueComparators;
        } else {
            throw new InstantiationException();
        }
        this.attributeValue = attributeValue;
        if (Arrays.asList(elementComparatorsArray).contains(elementComparators)) {
            this.elementComparator = elementComparators;
        } else {
            throw new InstantiationException();
        }
        this.text = text;
        if (Arrays.asList(elementStates).contains(elementState)) {
            this.elementState = elementState;
        } else {
            throw new InstantiationException();
        }
        this.imageComparator = imageComparator;
        this.imageLocation = imageLocation;
    }

    public WaitFor(int seconds, boolean bySeconds) {
        this.seconds = seconds;
        this.bySeconds = bySeconds;
    }

    public WaitFor() {

    }

    public String toString() {
        String str = "Wait ";
        if (bySeconds) {
            str = str + "in " + seconds + "seconds";
        } else {

            str = str + "for element \"" + element + "\"";

            if (hasAttribute) {
                str = str + " which ";
                if (attributeComparator) {
                    str = str + "has ";
                } else {
                    str = str + "does not have ";
                }
                str = str + "attribute \"" + attribute + "\"";
                if (attributeHasValue) {
                    str = str + " with the value that " + attributeValueComparator + " \"" + attributeValue + "\"";
                }
            }
            if (!"-".equals(elementComparator)) {
                str = str + " " + elementComparator;
                if (!"text".equals(elementState)) {
                    if ("image".equals(elementState)) {
                        str = str + " its image " + imageComparator + " \"" + imageLocation + "\"";
                    } else {
                        str = str + " " + elementState;
                    }
                } else {
                    str = str + " \"" + text + "\"";
                }
            }
        }
        return str;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public boolean isHasAttribute() {
        return hasAttribute;
    }

    public void setHasAttribute(boolean hasAttribute) {
        this.hasAttribute = hasAttribute;
        if (!hasAttribute) {
            setAttribute("");
            this.attributeHasValue = false;
            setAttributeValue("");
        }
    }

    public boolean isAttributeComparator() {
        return attributeComparator;
    }

    public void setAttributeComparator(boolean attributeComparator) {
        this.attributeComparator = attributeComparator;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public boolean isAttributeHasValue() {
        return attributeHasValue;
    }

    public void setAttributeHasValue(boolean attributeHasValue) {
        this.attributeHasValue = attributeHasValue;
        if (!attributeHasValue) {
            setAttributeValue("");
        }
    }

    public String getAttributeValueComparator() {
        return attributeValueComparator;
    }

    public void setAttributeValueComparator(String attributeValueComparator) throws InstantiationException {
        if (Arrays.asList(attributeValueComparatorsArray).contains(attributeValueComparator)) {
            this.attributeValueComparator = attributeValueComparator;
        } else {
            throw new InstantiationException();
        }
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getElementComparator() {
        return elementComparator;
    }

    public void setElementComparator(String elementComparator) throws InstantiationException {
        if (Arrays.asList(elementComparatorsArray).contains(elementComparator)) {
            this.elementComparator = elementComparator;
        } else {
            throw new InstantiationException();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElementState() {
        return elementState;
    }

    public void setElementState(String elementState) throws InstantiationException {
        if (Arrays.asList(elementStates).contains(elementState)) {
            this.elementState = elementState;
            if (!"text".equals(elementState)) {
                setText("");
            }
        } else {
            throw new InstantiationException();
        }
    }

    public String getImageComparator() {
        return imageComparator;
    }

    public void setImageComparator(String imageComparator) throws InstantiationException {
        if (Arrays.asList(imageComparatorsArray).contains(imageComparator)) {
            this.imageComparator = imageComparator;
        } else {
            throw new InstantiationException();
        }
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isBySeconds() {
        return bySeconds;
    }

    public void setBySeconds(boolean bySeconds) {
        this.bySeconds = bySeconds;
    }

}
