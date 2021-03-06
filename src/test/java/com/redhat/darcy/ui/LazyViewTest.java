package com.redhat.darcy.ui;

import static com.redhat.darcy.ui.elements.Elements.element;
import static org.junit.Assert.assertEquals;

import com.redhat.darcy.ui.elements.Element;
import com.redhat.darcy.ui.elements.LazyElement;

import org.junit.Test;

import java.util.concurrent.Callable;

public class LazyViewTest {
    interface ContextInterface {
        String getTestString();
    }
    
    interface CustomElement extends Element {
        ContextInterface getCastedContext();
    }
    
    @Test
    public void shouldBeAbleToAccessInterfacesOfParentViewContextViaAtContextAnnotation() {
        class ParentContext extends DummyContext implements ContextInterface {
            public String getTestString() {
                return "test";
            }
        }
        
        class TestCustomElement extends AbstractView implements CustomElement {
            @com.redhat.darcy.ui.annotations.Context
            ContextInterface parentContext;

            @Override
            public boolean isDisplayed() {
                return isLoaded();
            }

            @Override
            public ContextInterface getCastedContext() {
                return parentContext;
            }
            
            // At least one load condition is necessary to be a valid view
            protected Callable<Boolean> loadCondition() {
                return () -> true;
            }
        }
        
        ParentContext parentContext = new ParentContext();
        CustomElement customElement = element(CustomElement.class, By.id("test"), 
                new TestCustomElement());
        ((LazyElement) customElement).setContext(parentContext);
        
        
        assertEquals("Custom element's @Context field should have been assigned a proxy to parent "
                + "view's context.", 
                parentContext.getTestString(), 
                customElement.getCastedContext().getTestString());
    }
}
